<template>
  <div>

    <img :src="getImageUrl()" alt="">
    <span>{{content}}</span>
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

      console.log(response.data)
    });
  }
}
</script>

<style scoped>

</style>