<template>
  <div class="profile">


    <el-row>
      <el-col :span="24">
        <el-avatar :src="avatarUrl"></el-avatar>
        <span>Name: {{ username }} </span>
        <span>Email: {{ email }}</span>
      </el-col>
      <el-col :span="11" v-for="(item, index) in posts" v-bind:key="index" class='posts'>
        <el-card shadow="always">
          <el-image :src="getImgUrl(item.imgName)"/>

          <el-tag>Tag 1</el-tag>
          <el-tag type="success">Tag 2</el-tag>
          <el-tag type="info">Tag 3</el-tag>
          <el-tag type="warning">Tag 4</el-tag>
          <el-tag type="danger">Tag 5</el-tag>

          <el-link icon="el-icon-edit" class="edit" :href="getPostLink(item.id)">Edit</el-link>
          <div class="bottom">

            <span>{{ item.content }}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!--    <div v-for="item in posts" v-bind:key="item.imgName" class='posts'>
          <span>{{ item.content }}</span>
          <img :src="getUrl(item.imgName)">
        </div>
        <textarea name="content" v-model="content"></textarea>
        <input type="file" v-on:change="handleFileUpload($event)" name="img">
        <input type="submit" v-on:click="createPost">-->
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
      avatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      content: '',
      img: '',

      posts: []
    }
  },
  methods: {
    handleFileUpload(e) {
      this.img = e.target.files[0]
    },
    createPost() {
      ProfileService.createPost(this.authorId, this.content, this.img)
    },
    getPostLink(postId) {
      return `/edit/${postId}`;
    },
    getImgUrl(url) {
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

<style scoped>
.el-col {
  margin: 10px;
}

.el-tag {
  margin: 10px;
}
.edit{
  float: right;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
}
</style>