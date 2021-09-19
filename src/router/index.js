import Vue from "vue"
import VueRouter from "vue-router"
import Gallery from "@/views/Gallary";
import Profile from "@/views/Profile";
import Post from "@/views/Post";
import EditPost from "@/views/EditPost";
import Login from "../views/Login";

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
    },
    {
        path: "/edit/:id",
        name: "Edit",
        component: EditPost
    },
    {
        path: "/login",
        name: "Login",
        component: Login
    }
]

const router = new VueRouter({
    mode: "history",
    routes
})

router.beforeEach((to, from, next) => {
    const publicPages = ["/login"];
    const authRequired = !publicPages.includes(to.path);
    const isAuth = localStorage.getItem("user");

    //trying to access a restricted page + not logged in
    if(authRequired && !isAuth){
        next("/login");
    }else {
        next();
    }
})

export default router
