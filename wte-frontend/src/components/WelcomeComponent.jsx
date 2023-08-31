import { useNavigate } from "react-router-dom";

function WelcomeComponent(){
    const navigator = useNavigate();
    return (
        <div>
            <h2>WELCOME!</h2>
            <button onClick={() => navigator("/login")}>Login</button>
            <button onClick={() => navigator("/register")}>Register</button>
        </div>

    );
}

export default WelcomeComponent;