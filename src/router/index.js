import Vue from "vue"
import VueRouter from "vue-router"
import Gallery from "@/views/Gallary";
import Profile from "@/views/Profile";
import Post from "@/views/Post";

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Gallery",
    component: Gallery
  },
  {
    path: "/profile/:id",
    name: "Profile",
    component: Profile
  },
  {
    path: "/post/:id",
    name: "Post",
    component: Post
  }
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router
