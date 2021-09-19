import axios from "axios";
import authHeader from "./AuthHeader";

const GalleryService = {

    getAllPost(count, start){
        console.log(authHeader())
        return axios.get(`/api/v1/gallery/?count=${count}&start=${start}`, { headers: authHeader()})
    },

    getRecommendationUser(){
        return axios.get("/api/v1/gallery/user/recommendation", { headers: authHeader()})
    },
    getPostById(id) {
        return axios.get(`/api/v1/gallery/${id}`, { headers:authHeader()})
    },
    getImageUrl(imageName) {
        return `http://localhost:8081/static/${imageName}`
    },

}

export default GalleryService