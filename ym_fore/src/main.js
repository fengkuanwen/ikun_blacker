import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from "axios"
import VueCookies from "vue-cookies";
Vue.use(VueCookies);
Vue.prototype.$request = axios


Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
