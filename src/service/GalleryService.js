import axios from "axios";

const GalleryService = {

    getAllPost(){
        return axios.get("api/v1/gallery")
    },

    getRecommendationUser(){
        return axios.get("api/v1/gallery/user/recommendation")
    }
}

export default GalleryService