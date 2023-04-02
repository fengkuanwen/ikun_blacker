import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from "axios"
import VueCookies from "vue-cookies";
Vue.use(VueCookies);
Vue.prototype.$request = axios
// 增加请求头
axios.interceptors.request.use(
  // config => {
  //   config.headers['Authorization'] = `bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsieHVlY2hlbmctcGx1cyJdLCJ1c2VyX25hbWUiOiJma3ciLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNjgwMzc0MzY0LCJhdXRob3JpdGllcyI6WyJhZG1pbiIsInN1cGVyQWRtaW4iXSwianRpIjoiNjQxYmM5YWItNTZjMS00Nzk2LWFjMWMtZjQ2OWFhZDM4MDI3IiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.R8MuI8kZ-2IxM7kbvQdl46NsX3IF1nGC0NNW4lDREMI`;
  //       return config;
  //   },
  //   error => {
  //       return Promise.reject(error);
  //   }
);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
