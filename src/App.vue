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

export default {
  name: 'app',
  components: {NavMenu},
  data() {
    return {
    }
  },
  computed:{
  }
}
</script>

