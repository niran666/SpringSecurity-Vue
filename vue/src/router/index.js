import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
import Login from "../views/Login"
import Main from "../views/Main"
import Hello from "../views/Hello"
export default new Router({
  routes: [
    {
      path:'/login',
      name:'Login',
      component:Login
    },
    {
      path:'/main',
      name:'Main',
      component:Main
    },
    {
      path:'/hello',
      name:'Hello',
      component:Hello
    }
  ]
})
