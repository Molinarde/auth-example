import axios from "axios";
import authHeader from "./AuthHeader";

const API = "/api/v1/"

const UserService = {

    getUserBoard(){
        return axios.get(API + "gallery", { headers: authHeader() })
    },

    getAdminBoard(){
        return axios.get( API + "admin", { headers: authHeader()});
    }
}