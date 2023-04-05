<template>
    <div id="loginbox">
        <h2>短信登陆</h2>
        <form class="form" action="#" method="get">
       
            <div class="item">
                <input type="text" placeholder="手机号" id="phone" maxlength="11" v-model="phone" required>
            </div>
            <div class="item">
                <input type="text" placeholder="短信验证码" id="smsCode"  maxlength="11" v-model="smsCode" required>
            </div>
            <div style="margin-top: 15px;margin-left: 1px;">
                <button class="btn btn-primary" @click.prevent="getSmsCode">获取60s短信验证码</button>
            </div>
            <div>
                <button id="btn" @click.prevent="smsLogin">登陆</button>
            </div>
        </form>
    </div>
</template>
  
<script>
  export default {
    name: 'smsLogin',
    data(){
        return {
            phone:"",
            smsCode:""
        }
    },
    components: {
    },
    methods:{
        getSmsCode(){
                    this.$request ({
                        url:`${this.$store.state.serverPath}`+"/auth/smsLogin/smsCode?phone="+this.phone,
                        methods: "get",
                        responseType:"json",
                    }).then(res=>{
                        if(res.data.code==200){
                            alert("叮咚,短信验证码是："+res.data.data)
                        }else{
                            alert("获取短信验证码失败喽")
                        }
                    })
        },
        smsLogin(){
                    this.$request ({
                        url:`${this.$store.state.serverPath}`+"/oauth/token?client_id=XcWebApp&client_secret=XcWebApp&grant_type=sms_code&phone="+this.phone+"&sms_code="+this.smsCode,
                        method: "post",
                        responseType:"json",
                    }).then((res)=>{
                        alert("登陆成功")
                            this.$store.state.token=res.data.access_token;
                    },(err) => {
                        console.log(err);
                        alert("请检查输入！验证码过期时间为俩分钟")
                    })
        }
    }
  }
</script>