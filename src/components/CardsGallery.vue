<template>
  <div>
    <el-row>
      <el-col :span="8" v-for="(item, index) in posts" class="cards-indent" :key="index">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="getImageUrl(item.imgName)"
               class="image">
          <div style="padding: 14px;">
            <el-link :underline="false" :href="getPostUrl(item.id)">More details</el-link>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-button :disabled="disable" v-on:click="load">Load more</el-button>
  </div>
</template>

<script>
import GalleryService from "@/service/GalleryService";

export default {
  name: "CardsGallery",
  data() {
    return {
      posts: [],
      count: 10,
      start: 0,
      totalSize: 0,
      loading: false
    }
  },
  methods: {
    getImageUrl(imageName) {

      return GalleryService.getImageUrl(imageName)
    },
    load() {
      GalleryService.getAllPost(this.count, this.start).then(response => {
            this.posts = this.posts.concat(response.data.postList)
            this.start += this.count
            console.log(this.posts)
          }
      )
    },
    getPostUrl(postId) {
      return `/profile/post/${postId}`
    }
  },
  computed:{
    disable(){
      return this.start >= this.totalSize;
    }
  },
  created() {
    GalleryService.getAllPost(this.count, this.start).then(response => {
          this.posts = response.data.postList
          this.totalSize = response.data.listTotalSize
          this.start += 10
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

.image {
  width: 100%;
  display: block;
}


</style>