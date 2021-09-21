<template>
  <div>
    <el-menu :default-active="$route.path" mode="horizontal" router>
        <el-menu-item v-if="!currentUser" index="/signin" class="s-right">Sign In</el-menu-item>
        <el-menu-item v-if="!currentUser" index="/signup" class="s-right">Sign Up</el-menu-item>
        <el-menu-item v-if="currentUser" index="/">Gallery</el-menu-item>
        <el-menu-item v-if="currentUser" v-bind:index="getProfile()">Profile</el-menu-item>
        <el-menu-item v-if="currentUser" class="s-right"><a href @click.prevent="logOut">Logout</a></el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import TokenService from "../service/TokenService";
import EventBus from "../common/EventBus"

export default {
  name: "NavMenu",
  data() {
    return {
      isAuth: false,
    }
  },

  methods: {
    logOut() {
      this.$store.dispatch("logout")
      this.$router.push("/login")
    },
    getProfile() {
      let id = TokenService.getUser().id;
      return `/profile/${id}`
    }
  },
  mounted() {
    EventBus.on("logout", () => {
      this.logOut();
    });

  },
  beforeDestroy() {
    EventBus.remove("logout");
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  }
}
</script>

<style scoped>
.el-menu--horizontal > .el-menu-item.s-right {
  float: right;
}

</style>