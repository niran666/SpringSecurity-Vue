// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import VueRouter from 'vue-router'
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios' ;
import Vuex from 'vuex' ;
import qs from 'qs'
import VueCookies from 'vue-cookies'
axios.defaults.withCredentials=true;
Vue.use(VueCookies)
Vue.config.productionTip = false
Vue.prototype.$http = axios ;
Vue.use(ElementUI);
Vue.use(VueRouter);

axios.interceptors.request.use((config) => {
  if(config.method  === 'post'){
    config.data = qs.stringify(config.data);
  }
  return config;
},(error) =>{
  return Promise.reject(error);
});

axios.interceptors.response.use( (response) => {
    console.log(response);
    if(response.data.msg=="非法访问"||response.data.msg==="登陆过期")
    {
    //   router.replace({
    //     path: 'login',
    //     query: {redirect: router.currentRoute.fullPath}
    // })
       router.push('/login');
    }
    return response;
  },
  error => {
    if (error.response) {
      return Promise.reject(error.response.data) //返回接口返回的错误信息
    }
  }
);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
