<template>
  <el-container>
    <el-main>
      <CardsGallery/>
    </el-main>
    <el-aside>
      <Recommendation/>
    </el-aside>
  </el-container>
</template>

<script>
// @ is an alias to /src
import CardsGallery from "@/components/CardsGallery";
import Recommendation from "@/components/Recommendation";

export default {
  name: 'Gallery',
  data() {
    return {}
  },
  components: {
    CardsGallery,
    Recommendation
  },

}
</script>

<style scoped>
.el-aside {
  padding-top: 20px;
}
</style>
