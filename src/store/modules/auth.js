import {AuthService} from "../../service/AuthService";

const user = JSON.parse(localStorage.getItem('user'))
const initialState = user ? {status: {isAuth: true}, user} : {status: {isAuth: false}, user: null};

export const auth = {
    state: initialState,
    mutations: {
        loginSuccess(state, user) {
            state.status.isAuth = true;
            state.user = user;
        },
        logout(state) {
            state.status.isAuth = false;
            state.user = null;
        },
        loginFailure(state) {
            state.status.isAuth = false;
            state.user = null;
        },
        refreshToken(state, accessToken) {
            state.status.isAuth = true;
            state.user = {...state.user, token: accessToken};
        }
    },
    actions: {
        refreshToken({commit}, accessToken) {
            console.log(accessToken)
            commit("refreshToken", accessToken);
        },
        logout({commit}) {
            AuthService.logout();
            commit("logout")
        },
        login({commit}, user) {
            return AuthService.login(user)
                .then(user => {
                    commit("loginSuccess", user);
                    return Promise.resolve(user)
                }, error => {
                    commit("loginFailure");
                    return Promise.reject(error);
                })
        }
    }
}