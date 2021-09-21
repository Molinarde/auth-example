<template>
  <div id="app">
    <div>
      <el-container>
        <el-header>
          <NavMenu/>
        </el-header>

        <el-container>
          <el-main>
            <router-view/>
          </el-main>
        </el-container>

        <el-footer>

        </el-footer>
      </el-container>
    </div>
  </div>
</template>

<script>
import NavMenu from "@/components/NavMenu";
import EventBus from "./common/EventBus"

export default {
  name: 'app',
  components: {NavMenu},
  data() {
    return {}
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    },
  },
  mounted() {
    EventBus.on("logout", () => {
      this.logout()
    })
  }
}
</script>

