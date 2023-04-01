import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // 本机网关服务地址
    serverPath:"http://localhost:9001",
    captchaTimeStampCache:" "
  },
  mutations: {
  changeCaptchaTimeStampCache(state, val){
       state.captchaTimeStampCache=val;
  }
  },
  actions: {

  }
})
