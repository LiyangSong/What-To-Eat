import { useState, useEffect } from 'react';
import {getAllDishes, getDishById} from "../services/DishService.js";
import {useNavigate} from "react-router-dom";

function DishesComponent() {
    const navigator = useNavigate();
    const [dishes, setDishes] = useState([]);
    const listDishes = () => {
        getAllDishes()
            .then(response => response.data)
            .catch(error => console.error(error))
    }

    return (
        <div className="container">
            <br />
            <table className="table table-bordered table-striped">
                <thead>
                TODO
                </thead>
                <tbody>
                TODO
                </tbody>
            </table>
            <br />
            <div className="row">
                <button
                    onClick={()=>navigator("/dish-add")}>
                    Add Dish
                </button>
                <button
                    onClick={()=>navigator("/dish-create")}
                >
                    Create Dish
                </button>
            </div>
        </div>
    )
}

export default DishesComponent;