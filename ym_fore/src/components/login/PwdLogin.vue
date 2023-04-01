<template>
    <div id="loginbox">
        <h2>密码登陆</h2>
        <form class="form" action="#" method="get">
            <div class="item">
                <input type="text" placeholder="手机号" id="username" maxlength="11" required>
            </div>
            <div class="item">
                <input type="password" placeholder="密码" id="password" required>
            </div>
            <div class="item">
                <input type="text" placeholder="验证码" id="captcha" required style="width:90px">
                <img :src="captchaSrc" @click="changeCaptcha" alt="验证码" width="90px" height="45px;" style="padding-bottom: 5px"/>
            </div>
            
            <div>
                <button id="btn">登陆</button>
            </div>
        </form>
    </div>
</template>
  
<script>
  export default {
    name: 'pwdLogin',
    data(){
        return{
            captchaSrc : ""
        }
    },
    components: {
    },
    methods:{
        changeCaptcha(){
            var timeStamp = new Date().getTime(); 
            this.$store.commit("changeCaptchaTimeStampCache",timeStamp)
            this.captchaSrc = this.$store.state.serverPath+"/auth/captcha/login?timeStamp="+timeStamp;
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