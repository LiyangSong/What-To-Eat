import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HeaderComponent from "./components/HeaderComponent.jsx";
import FooterComponent from "./components/FooterComponent.jsx";
import WelcomeComponent from "./components/WelcomeComponent.jsx";
import LoginComponent from "./components/LoginComponent.jsx";
import RegisterComponent from "./components/RegisterComponent.jsx";
import DishesComponent from "./components/DishesComponent.jsx";
import IngredientsComponent from "./components/IngredientsComponent.jsx";
import DishCreateComponent from "./components/DishCreateComponent.jsx";
import AdminComponent from "./components/AdminComponent.jsx";
import PageNotFoundComponent from "./components/PageNotFoundComponent.jsx";
import DishDetailsComponent from "./components/DishDetailsComponent.jsx";
import DishAddComponent from "./components/DishAddComponent.jsx";

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
                <Route path = "/ingredients" element = {<IngredientsComponent />} />
                <Route path = "/dish-add" element = {<DishAddComponent />} />
                <Route path = "/dish-create" element = {<DishCreateComponent />} />
                <Route path = "/dish-details" element = {<DishDetailsComponent />} />

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
