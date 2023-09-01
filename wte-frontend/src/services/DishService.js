import axiosInstance from "./axiosInstance.js";

const DISH_BASE_API_URL = import.meta.env.VITE_API_URL + "/dishes";

export const createDish = (dishDetailsCreateDto) => axiosInstance.post(DISH_BASE_API_URL + "/create", dishDetailsCreateDto);

export const getDishById = (dishId) => axiosInstance.get(DISH_BASE_API_URL + `/get/id=${dishId}`);

export const getDishesByName = (dishName) => axiosInstance.get(DISH_BASE_API_URL + `/get/name=${dishName}`);

export const getAllDishes = () => axiosInstance.get(DISH_BASE_API_URL + "getAll");

export const updateDish = (dishId, dishDetailsCreateDto) => axiosInstance.put(DISH_BASE_API_URL + `/update/id=${dishId}`, dishDetailsCreateDto);

export const deleteDish = (dishId) => axiosInstance.delete(DISH_BASE_API_URL + `/delete/id=${dishId}`);

export const addDish = (dishId) => axiosInstance.post(DISH_BASE_API_URL + `/add/id=${dishId}`);

export const removeDish = (dishId) => axiosInstance.delete(DISH_BASE_API_URL + `/remove/id=${dishId}`);