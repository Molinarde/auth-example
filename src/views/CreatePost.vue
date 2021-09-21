<template>
  <div>

    <el-input type="textarea" v-model="content" placeholder="Description"></el-input>
    <input type="file" v-on:change="handleFileUpload($event)" name="img"/>
    <el-button v-on:click="savePost()"  type="primary">Add</el-button>
  </div>
</template>

<script>
import {ProfileService} from "../service/ProfileService";
import TokenService from "../service/TokenService";

export default {
  name: "CreatePost",
  data() {
    return {
      content: '',
      img: ''
    }
  },
  methods: {
    handleFileUpload(e) {
      this.img = e.target.files[0]
    },
    savePost() {
      let id = TokenService.getUser().id;
      ProfileService.createPost(id, this.content, this.img);
    }
  }
}
</script>

<style scoped>

</style>