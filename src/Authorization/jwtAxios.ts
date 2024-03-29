import axios from "axios";
import { loginAction } from "../Redux/AuthState";
import store from "../Redux/Store";

const jwtAxios = axios.create();

jwtAxios.interceptors.request.use(request=>{
    request.headers={"Authorization":store.getState().authState.user.token}
    return request;
})

jwtAxios.interceptors.response.use(response => {
    if (response.headers.authorization === undefined) {
        //store.dispatch(tokenUpdateAction(response.config.headers.Authorization));
        const user=  store.getState().authState.user;
        user.token=response.config.headers.Authorization;
        store.dispatch(loginAction(user));
    } else {
        //store.dispatch(tokenUpdateAction(response.headers.authorization));
        // store.dispatch(loginAction(response.headers.authorization));
        const user=  store.getState().authState.user;
        user.token=response.headers.authorization;
        store.dispatch(loginAction(user));
    }
     return response;
})

export default jwtAxios;