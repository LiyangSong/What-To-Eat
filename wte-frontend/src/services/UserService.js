import axios from "axios";

const USER_REST_API_BASE_URL = import.meta.env.VITE_API_URL + "/api/users";

export const registerApiCall = (userRegisterDto) => axios.post(USER_REST_API_BASE_URL + "/register", userRegisterDto);

export const loginApiCall = (userLoginDto) => axios.post(USER_REST_API_BASE_URL + "/login", userLoginDto);

export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

export const saveLoggedInUser = (username) => sessionStorage.setItem("authenticatedUser", username);

export const isUserLoggedIn = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username != null;
}

export const getLoggedInUser = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username;
}

export const logout = () => {
    localStorage.clear();
    sessionStorage.clear();
}
