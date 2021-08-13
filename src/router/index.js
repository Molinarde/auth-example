import Vue from 'vue'
import VueRouter from 'vue-router'
import Gallery from "@/views/Gallary";
import Profile from "@/views/Profile";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Gallery',
    component: Gallery
  },
  {
    path: '/profile/:id',
    name: 'Profile',
    component: Profile
  }
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router
