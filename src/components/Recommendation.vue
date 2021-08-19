<template>
  <div class="recommendation">
    <el-card>
      <div slot="header" class="clearfix">
        <span>Recommendation</span>
        <el-button style="float: right; padding: 3px 0" type="text">Operation button</el-button>
      </div>
      <div v-for="(item, index) in recommended" :key="index" class="text item">
        <div class="list-recom">
          <el-avatar :src="item.imgURL"></el-avatar>
          <el-link :underline="false" :href="getUserLink(item.userId)">{{item.username}}</el-link>
        </div>
        <el-divider direction="horizontal"></el-divider>

      </div>
    </el-card>
  </div>
</template>

<script>
import GalleryService from "@/service/GalleryService";

export default {
  name: "Recommendation",
  data(){
    return{
      recommended: [],
    }
  },
  methods: {
    getUserLink(userId){
      return `/profile/${userId}`
    }
  },
  created() {
    GalleryService.getRecommendationUser().then(response =>{
      this.recommended = response.data
    })
  }

}
</script>

<style scoped>
.list-recom{
  display: flex;
}
.list-recom h3{
  font-size: 15px;
}
.list-recom{
  vertical-align: middle;

  width: 100%;
  height: 50px;
}
.el-card{
  height: auto;
}
</style>