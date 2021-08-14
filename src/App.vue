<template>
  <div id="app">
    <div v-if="isAuth">
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
    <div v-else>
      <Login/>
    </div>
  </div>
</template>

<script>
import NavMenu from "@/components/NavMenu";
import Login from "@/views/Login";

export default {
  name: 'app',
  components: {Login, NavMenu},
  data() {
    return {
      isAuth: true,
    }
  },
  methods: {}
}
</script>

