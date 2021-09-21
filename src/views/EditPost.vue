<template>
  <div>

  <el-row>
    <el-col :span="24">
      <el-card>
        <h1>Edit post</h1>
        <el-image :src="getImg()"></el-image>
        <el-input type="textarea" v-model="content"></el-input>
        <input type="file" v-on:change="handleFileUpload($event)" name="img">
        <br/>
        <el-button v-on:click="updatePost()" type="primary">Save</el-button>
      </el-card>
    </el-col>
  </el-row>
  </div>
</template>

<script>
import GalleryService from "@/service/GalleryService";
import {ProfileService} from "@/service/ProfileService";

export default {
  name: "EditPost",
  data() {
    return {
      imgName: '',
      img: '',
      content: '',
      authorId: ''
    }
  },
  methods:{
    handleFileUpload(e) {
      this.img = e.target.files[0]
      console.log(this.img)
    },
    updatePost(){
      let post = {
        id: this.id,
        content: this.content,
        authorId: this.authorId,
      }
      console.log(post)
      ProfileService.updatePost(post, this.img)
    },
    getImg() {
      return GalleryService.getImageUrl(this.imgName);
    }
  },
  created(){
    const params = this.$route.params.idPost;

    GalleryService.getPostById(params).then(response =>{
      this.id = response.data.id;
      this.imgName = response.data.imgName;
      this.content = response.data.content;
      this.authorId = response.data.authorId;
    })

  }
}
</script>

<style scoped>

</style>