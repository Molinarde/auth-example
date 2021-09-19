<template>
  <div>
    <el-menu :default-active="$route.path" mode="horizontal" router>
      <el-menu-item index="/">Gallery</el-menu-item>
      <el-menu-item index="/profile" v-if="isAuth">Profile</el-menu-item>
      <el-menu-item index="/signin" class="s-right">Sign In</el-menu-item>
      <el-menu-item index="/signup" class="s-right">Sign Up</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "NavMenu",
  data(){
    return{
      isAuth: true
    }
  }
}
</script>

<style scoped>
.el-menu--horizontal > .el-menu-item.s-right{
  float: right;
}

</style>