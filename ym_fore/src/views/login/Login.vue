<template>
  <div class="login">
    <div style="position: absolute; top 50%;left: 51%; font-size: 20rpx;">
      <a style="" @click.prevent="updateLoginFlag('login')">密码登陆</a>
      <a style="" @click.stop="updateLoginFlag('vxLogin')">微信登陆</a>
      <a style="" @click.prevent="updateLoginFlag('smsLogin')">短信登陆</a>
      <a style="" @click.prevent="updateLoginFlag('registry')">注册</a>
    </div>

    <pwdLogin v-if="loginComponentFlag === 'login' ? true : false" />
    <vxLogin v-else-if="loginComponentFlag === 'vxLogin' ? true : false" />
    <smsLogin
      v-else-if="loginComponentFlag === 'smsLogin' ? true : false"
    ></smsLogin>
    <registry v-else></registry>
  </div>
</template>

<script>
// 登陆组件
import registry from "../../components/login/Registry";
import vxLogin from "../../components/login/VxLogin";
import smsLogin from "../../components/login/SmsLogin";
import pwdLogin from "../../components/login/PwdLogin";
export default {
  name: "login",
  data() {
    return {
      loginComponentFlag: "login",
    };
  },
  methods: {
    updateLoginFlag(str) {
      if (str === "login") {
        this.loginComponentFlag = "login";
      } else if (str === "vxLogin") {
        this.loginComponentFlag = "vxLogin";
      } else if (str === "smsLogin") {
        this.loginComponentFlag = "smsLogin";
      } else {
        this.loginComponentFlag = "registry";
      }
    },
  },
  components: {
    // 登陆组件
    registry,
    vxLogin,
    smsLogin,
    pwdLogin,
  },
};
</script>
<style>
a:hover {
  color: none;
}
a {
  color: yellow;
  text-decoration-line: none;
  margin-left: 10px;
}
body {
  background: url("../../assets/img/LoginBack.png") repeat;
  background-size: 100% auto;
  /*设置背景图片*/
}

#loginbox {
  width: 30%;
  height: 500px;
  margin: 0 auto;
  margin-top: 12%;
  margin-left: 33%;
  text-align: center;
  background: #00000060;
  padding: 20px 50px;
}

#loginbox h2 {
  color: white;
}

#loginbox .form {
  margin-top: 50px;
}

#loginbox .form .item {
  margin-top: 15px;
  color: white;
}

#loginbox .form .item input {
  width: 180px;
  font-size: 18px;
  border: none;
  border-bottom: 2px solid white;
  padding: 5px 10px;
  background: #ffffff00;
  color: white;
  outline-width: 0px;
  /*去除输入框的外边框*/
}

#loginbox button {
  margin-top: 20px;
  width: 180px;
  height: 30px;
  font-size: 20px;
  border: 0;
  border-radius: 15px;
  background-color: #85ffbd;
  background-image: linear-gradient(45deg, #85ffbd 0%, #fffb7d 100%);
}
</style>
