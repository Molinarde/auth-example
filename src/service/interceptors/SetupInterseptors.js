import axiosInstance from './Api'
import TokenService from "../TokenService";

const setup = (store) => {
    axiosInstance.interceptors.request.use(
        (config) => {
            const token = TokenService.getAccessToken()
            if (token) {
                config.headers["Authorization"] = "Bearer " + token;
            }

            return config;
        },
        (err) => {
            Promise.reject(err);
        }
    );

    axiosInstance.interceptors.response.use(
        (res) => {
            return res;
        },
        async (err) => {
            const originalConfig = err.config;
            if (originalConfig.url !== "/signin" && err.response) {
                console.log(err.response.status)
                if (err.response.status === 401 && !originalConfig._retry) {
                    originalConfig._retry = true;

                    try {
                        const rs = await axiosInstance.post("/auth/refreshtoken", {
                            refreshToken: TokenService.getLocalRefreshToken(),
                        });

                        const {accessToken} = rs.data;
                        store.dispatch("refreshToken", accessToken);
                        TokenService.updateLocalToken(accessToken);

                        return axiosInstance(originalConfig);
                    } catch (_error) {
                        return Promise.reject(_error);
                    }
                }
            }
            return Promise.reject(err);
        }
    );
}

export default setup;