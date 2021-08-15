import axios from "axios";

const GalleryService = {

    getAllPost(count, start){
        return axios.get(`/api/v1/gallery/?count=${count}&start=${start}`)
    },

    getRecommendationUser(){
        return axios.get("/api/v1/gallery/user/recommendation")
    },
    getPostById(id) {
        console.log(id)
        return axios.get(`/api/v1/gallery/${id}`)
    },
    getImageUrl(imageName) {
        return `http://localhost:8081/static/${imageName}`
    },
}

export default GalleryService