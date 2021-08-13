import axios from "axios";

const ProfileService = {

    getProfile(id) {
        return axios.get(`/api/v1/profile/${id}`)
    },
    createPost(authorId, content, file) {
        let bodyFormData = new FormData()
        bodyFormData.append("authorId", authorId)
        bodyFormData.append("content", content)
        bodyFormData.append("file", file)
        return axios({
            method: "post",
            url: `/api/v1/profile/add/post`,
            data: bodyFormData,
            headers: {'Content-Type': "multipart/form-data"}
        })
    }
}


export {ProfileService}