import axios from "axios";

const ProfileService = {

    getProfile(id) {
        return axios.get(`/api/v1/profile/${id}`)
    },
    updatePost(post, imgFile) {

        let bodyFormData = new FormData()
        bodyFormData.append("id", post.id)
        bodyFormData.append("content", post.content)
        bodyFormData.append("authorId", post.authorId)

        if (imgFile != null)
            bodyFormData.append("file", imgFile)

        return axios({
            method: "put",
            url: `/api/v1/profile/update/post`,
            data: bodyFormData,
            headers: {'Content-Type': "multipart/form-data"}
        })
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