<template>
  <div class="profile">
    <div class="info">
      <h4>{{username}}</h4>
      <h4>{{email}}</h4>
    </div>
    <div v-for="item in posts" v-bind:key="item.imgName" class='posts' >
      <span>{{item.content}}</span>
      <img :src="getUrl(item.imgName)">
    </div>
      <textarea name="content" v-model="content"></textarea>
      <input type="file" v-on:change="handleFileUpload($event)" name="img">
      <input type="submit" v-on:click="createPost">
  </div>
</template>

<script>
import {ProfileService} from "@/service/ProfileService";

export default {
  name: "Profile",
  data() {
    return {
      username: '',
      email: '',
      authorId: '',

      content: '',
      img: '',

      posts: []
    }
  },
  methods: {
    handleFileUpload(e){
      this.img = e.target.files[0]
    },
    createPost(){
      ProfileService.createPost(this.authorId, this.content, this.img)
    },
    getUrl(url){
      return `http://localhost:8081/static/${url}`
    }
  },
  created() {
    let id = this.$route.params.id;
    ProfileService.getProfile(id).then(response => {
      this.authorId = response.data.user.id
      this.username = response.data.user.username
      this.email = response.data.user.email
      this.posts = response.data.post
      console.log(this.posts)
    })
  }
}
</script>