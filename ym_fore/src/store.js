import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // 本机网关服务地址
    serverPath:"http://localhost:9001",
    // 验证码时间戳缓存
    captchaTimeStampCache:" ",
    registryCaptchaTimeStampCache:" "
  },
  mutations: {
  changeCaptchaTimeStampCache(state, val){
       state.captchaTimeStampCache=val;
  },
  changeRegistryCaptchaTimeStampCache(state, val){
    state.registryCaptchaTimeStampCache=val;
  }
  },
  actions: {

  }
})
