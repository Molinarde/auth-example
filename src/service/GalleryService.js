import api from "./interceptors/Api.js";

const GalleryService = {

    getAllPost(count, start){
        return api.get(`/gallery/?count=${count}&start=${start}`)
    },

    getRecommendationUser(){
        return api.get("/gallery/user/recommendation")
    },
    getPostById(id) {
        return api.get(`/gallery/${id}`)
    },
    getImageUrl(imageName) {
        return `http://localhost:8081/static/${imageName}`
    },

}

export default GalleryService