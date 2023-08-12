import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HeaderComponent from "./components/HeaderComponent.jsx";
import FooterComponent from "./components/FooterComponent.jsx";
import WelcomeComponent from "./components/WelcomeComponent.jsx";
import LoginComponent from "./components/LoginComponent.jsx";
import RegisterComponent from "./components/RegisterComponent.jsx";
import DishesComponent from "./components/DishesComponent.jsx";
import IngredientsComponent from "./components/IngredientsComponent.jsx";
import DishPlanComponent from "./components/DishPlanComponent.jsx";
import AdminComponent from "./components/AdminComponent.jsx";
import PageNotFoundComponent from "./components/PageNotFoundComponent.jsx";

function App() {
    return (
        <BrowserRouter>
            <HeaderComponent />
            <Routes>
                {/* Public Routes */}
                <Route path = "/" element = {<WelcomeComponent />} />
                <Route path = "/login" element = {<LoginComponent />} />
                <Route path = "/register" element = {<RegisterComponent />} />

                {/* Authenticated User Routes */}
                <Route path = "/dishes" element = {<DishesComponent />} />
                <Route path = "/Ingredients" element = {<IngredientsComponent />} />
                <Route path = "/dish-plan" element = {<DishPlanComponent />} />

                {/* Admin Routes */}
                <Route path = "/admin" element = {<AdminComponent />} />

                {/* 404 Page */}
                <Route component={<PageNotFoundComponent />} />
            </Routes>
            <FooterComponent />
        </BrowserRouter>
    )
}

export default App;
