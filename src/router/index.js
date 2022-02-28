import { createRouter, createWebHistory } from 'vue-router'
import StudentHome from '../views/StudentHome.vue'
import TeacherHome from '../views/TeacherHome.vue'
import Signin from '../views/Signin.vue'
import Signup from "../views/Signup.vue"
import TeacherModule from '../components/Module/Teacher/TeacherModule'
import TeacherHomePage from '../components/Home/TeacherHomePage'
import StudentHomePage from '../components/Home/StudentHomePage'
import StudentTeamPage from '../components/team/StudentTeamPage.vue'
import StudentModule from '../components/Module/Student/StudentModule'

const routes = [
  {
    path: '/student/:id',
    name: 'StudentHome',
    component: StudentHome,
    children: [
      {
        path: '',
        name: 'StudentHomePage',
        component: StudentHomePage
      },
      {
        path: 'module',
        name: 'StudentModule',
        component: StudentModule
      },
      {
        path: 'team',
        name: 'StudentTeam',
        component:StudentTeamPage
      }
    ]

  },
  {
    path: '/teacher/:id',
    name: 'TeacherHome',
    component: TeacherHome,
    children: [
      {
        path: '',
        name: 'TeacherHomePage',
        component: TeacherHomePage
      },
      {
        path: 'module',
        name: 'TeacherModule',
        component: TeacherModule
      }
    ]
  },
  {
    path: '/signin',
    name: 'Signin',
    component: Signin
  },
  {
    path:'/signup',
    name: 'Signup',
    component:Signup
  },
  {
    path: '/',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
