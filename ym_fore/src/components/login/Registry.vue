<template>
    <div id="loginbox">
        <h2>欢迎注册</h2>
        <form class="form" action="#" method="get">
            <div class="item">
                <input type="text" placeholder="昵称" id="username" maxlength="4" v-model="registryData.username" required>
            </div>
            <div class="item">
                <input type="text" placeholder="手机号" id="phone" maxlength="11" v-model="registryData.phone" required>
            </div>
            
            <div class="item">
                <img :src="captchaSrc" @click="changeCaptcha" alt="验证码" width="90px" height="45px;" style="padding-bottom: 5px"/>

                <input type="text" placeholder="请先输入" id="captcha" v-model="inputCaptcha" required style="width:90px">
            </div>
            <div class="item">
                <input type="text" placeholder="短信验证码" id="smsCode" maxlength="11" v-model="registryData.smsCode" required>
            </div>
            <div class="item">
                <input type="password" placeholder="密码" id="password" maxlength="11" v-model="registryData.password" required>
            </div>

            <div>
                <button id="btn" @click.prevent="registry">注册</button>
            </div>

        </form>
    </div>
</template>
  
<script>
  export default {
    name: 'registry',
    data(){
        return{
            // 验证码url
            captchaSrc : "",
            // 真实验证码
            captcha:"",
            // 输入验证码
            inputCaptcha:"",

            registryData:{
                username:"",
                phone:"",
                password:"",
                smsCode:"",
                smsUUID:""
            }
        }
    },
    components: {
    },
    methods:{ 
        changeCaptcha(){
            var timeStamp = new Date().getTime(); 
            this.captchaSrc = this.$store.state.serverPath+"/auth/captcha/registry?timeStamp="+timeStamp;
            setTimeout(()=>{
                this.captcha = this.$cookies.get("registryCaptchaCode");
            },250)
        },
        initMethod(){
            // 进入界面加载验证码
            this.changeCaptcha();
        },
        // 注册按钮方法
        registry(){
                    // const that = this;
                    var username = JSON.stringify(this.registryData.username);
                    var phone = JSON.stringify(this.registryData.phone);
                    var password = JSON.stringify(this.registryData.password);
                    var smsCode = JSON.stringify(this.registryData.smsCode);
                    var uuid = JSON.stringify(this.registryData.smsUUID);
                    this.$request ({
                        url:`${this.$store.state.serverPath}`+"/auth/registry",
                        method: "post",
                        data:{
                            username,phone,password,smsCode,uuid
                        },
                        responseType:"json",
                    }).then((res)=>{
                        alert(res.data.data)
                    })
        }
    },
    mounted() {
        this.initMethod();
    },
    watch: {
        // 监听用户输入验证码，如果输入正确直接请求地址
        'inputCaptcha': {
            handler(newVal) {
                if(newVal === this.captcha){
                    const that = this;
                    this.$request ({
                        url:`${this.$store.state.serverPath}`+"/auth/smsCode",
                        methods: "get",
                        responseType:"json",
                    }).then(res=>{
                        if(res.data.code==200){
                            that.registryData.smsUUID = res.data.data.uuid;
                            alert("叮咚,短信验证码是："+res.data.data.smsCode)
                        }else{
                            alert("获取短信验证码失败喽")
                        }
                    })
                }
            }
        }
    }
  }
</script>