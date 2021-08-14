<template>
  <div class="gallary">
    <el-row>
      <el-col :span="8" v-for="(item, index) in posts" class="cards-indent" :key="index">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="getUrl(item.imgName)"
               class="image">
          <div style="padding: 14px;">
            <el-button type="text" class="button">More details</el-button>

          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import GalleryService from "@/service/GalleryService";

export default {
  name: "CardsGallery",
  data() {
    return {
      posts: [],
    }
  },
  methods: {
    getUrl(url) {
      return `http://localhost:8081/static/${url}`
    }
  },
  created() {
    GalleryService.getAllPost().then(response => {
          this.posts = response.data
          console.log(this.posts)
        }
    )
  }
}
</script>

<style scoped>
.cards-indent {
  padding-right: 10px;
  padding-bottom: 10px;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}
</style>