import api from "./interceptors/Api";
import authHeader from "./AuthHeader";

const ProfileService = {

    getProfile(id) {
        return api.get(`/profile/${id}`, { headers: authHeader()})
    },
    updatePost(post, imgFile) {

        let bodyFormData = new FormData()
        bodyFormData.append("id", post.id)
        bodyFormData.append("content", post.content)
        bodyFormData.append("authorId", post.authorId)

        if (imgFile != null)
            bodyFormData.append("file", imgFile)

        return api({
            method: "put",
            url: `/profile/update/post`,
            data: bodyFormData,
            headers: {'Content-Type': "multipart/form-data"}
        })
    },

    createPost(authorId, content, file) {
        let bodyFormData = new FormData()
        bodyFormData.append("authorId", authorId)
        bodyFormData.append("content", content)
        bodyFormData.append("file", file)
        return api({
            method: "post",
            url: `/profile/add/post`,
            data: bodyFormData,
            headers:{'Content-Type': "multipart/form-data"}
        })
    }
}


export {ProfileService}