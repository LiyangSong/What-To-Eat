import { useState, useEffect } from 'react';
import { getDishById } from "../services/DishService.js";

function DishesComponent() {
    const [dishDetailsReturnDto, setDishDetailsReturnDto] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        (async () => {
            try {
                const dishId = 1;
                const response = await getDishById(dishId);
                setDishDetailsReturnDto(response.data);
            } catch (error) {
                setError(error);
            }
        })();
    }, []);

    return (
        <div>
            <h1>Test Get Dish By ID</h1>
            {error && <p>Error: {JSON.stringify(error)}</p>}
            {dishDetailsReturnDto ? (
                <div>
                    <h4>Dish Details</h4>
                    <p>ID: {dishDetailsReturnDto.dishId.toString()}</p>
                    <p>Name: {dishDetailsReturnDto.dishName.toString()}</p>
                    <p>Ingredient Amounts:</p>
                    <p>
                        {dishDetailsReturnDto.ingredientAmountMaps.map(map =>
                            <div key={map.ingredientId.toString()}>
                                <p>Ingredient Name: {map.ingredientName.toString()}</p>
                                <p>Ingredient Amount: {map.ingredientAmount.toString()}</p>
                            </div>
                        )}
                    </p>
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
}

export default DishesComponent;