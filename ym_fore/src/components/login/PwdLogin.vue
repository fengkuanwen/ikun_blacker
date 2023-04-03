<template>
    <div id="loginbox">
        <h2>密码登陆</h2>
        <form class="form" action="#" method="get">
            <div class="item">
                <input type="text" placeholder="用户名" id="username" v-model="username" maxlength="11" required>
            </div>
            <div class="item">
                <input type="password" placeholder="密码" id="password" v-model="password" required>
            </div>
            <div class="item">
                <input type="text" placeholder="验证码" id="captcha" v-model="captcha" required style="width:90px">
                <img :src="captchaSrc" @click="changeCaptcha" alt="验证码" width="90px" height="45px;" style="padding-bottom: 5px"/>
            </div>
            
            <div>
                <button id="btn" @click.prevent="userLogin">登陆</button>
            </div>
        </form>
    </div>
</template>
  
<script>
  export default {
    name: 'pwdLogin',
    data(){
        return{
            captchaSrc : "",
            username:"",
            password:"",
            captcha:""
        }
    },
    components: {
    },
    methods:{
        // http://localhost:9001/oauth/token?client_id=XcWebApp&client_secret=XcWebApp&grant_type=password&username=fkw&password=22
        changeCaptcha(){
            var timeStamp = new Date().getTime(); 
            this.$store.commit("changeCaptchaTimeStampCache",timeStamp)
            this.captchaSrc = this.$store.state.serverPath+"/auth/captcha/login?timeStamp="+timeStamp;
        },
        // 用户获取Token的方法丫
        userLogin(){
                    const that = this;
                    this.$request ({
                        url:`${this.$store.state.serverPath}`+"/oauth/token?client_id=XcWebApp&client_secret=XcWebApp&grant_type=password&username="+that.username+"&password="+that.password+"&captcha="+that.captcha+"&timeStamp="+this.$store.state.captchaTimeStampCache,
                        method: "post",
                        responseType:"json",
                    }).then((res)=>{
                            alert("登陆成功")
                            this.$store.state.token=res.data.access_token;
                     
                        
                    },(err) => {
                        console.log(err);
                        alert("请检查输入！验证码过期时间为俩分钟")
                    })
        
        },
        initMethod(){
            // 进入界面加载验证码
            this.changeCaptcha();
        },
    },
    mounted() {
        this.initMethod();
    },
  }
</script>