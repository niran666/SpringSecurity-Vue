import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
import Login from "../views/Login"
import Main from "../views/Main"
import Hello from "../views/Hello"
import Menu from "../views/Menu"
import User_mag from "../views/user_mag"
import Admin_menu from "../views/admin_menu"
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
    },
    {
      path:"/menu",
      name:"/Menu",
      component:Menu,
      children: [{
        path: 'hello',
        component: Hello
      },{
        path: 'login',
        component: Login
      }
    ]
    },
    {
      path:"/admin_menu",
      name:"Admin_menu",
      component:Admin_menu,
      children:[
        {
          path:'user_mag',
          component:User_mag,
        },{
          path: 'hello',
          component: Hello
        }
      ]
    }
  ]
})
