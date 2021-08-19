<template>
  <div>
    <el-row >
      <el-col :span="12">
        <el-card>
          <el-image class="img" :src="getImageUrl()" />
          <div class="content">
            <span>{{content}}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import GalleryService from "@/service/GalleryService";

export default {
  name: "Post",
  data(){
    return{
      imgName: '',
      authorId: '',
      content: ''
    }
  },
  methods:{
    getImageUrl(){
      return GalleryService.getImageUrl(this.imgName);
    }
  },

  created() {
    let id = this.$route.params.id;
    GalleryService.getPostById(id).then(response =>{
      this.imgName = response.data.imgName;
      this.authorId = response.data.authorId;
      this.content = response.data.content;

    });
  }
}
</script>

<style scoped>
.content{
  padding: 14px;
}


</style>