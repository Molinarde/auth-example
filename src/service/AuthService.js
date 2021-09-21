import axios from "axios";
import TokenService from "./TokenService";

const AuthService = {
    login(user) {
        return axios.post("/api/v1/auth/signin", {
            username: user.username,
            password: user.password
        }).then(response => {
            if(response.data.token){
                TokenService.setUser(response.data)
            }
            return response.data
        })
    },

    registration(user) {
        return axios.post("/api/v1/auth/signup", {
            username: user.username,
            email: user.email,
            password: user.password
        })
    },

    logout() {
        TokenService.removeUser();
    }
}

export {AuthService}