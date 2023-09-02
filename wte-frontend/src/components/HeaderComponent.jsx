import grocery from "../../public/grocery.svg"

function HeaderComponent(){
    return (
        <header className="p-3 mb-3 border-bottom">
            <div className="container-fluid">
                <div className="d-flex flex-wrap align-items-center justify-content-lg-start">
                    <a
                        href="/"
                        className="d-flex algin-items-center mb-2 mb-lg-0"
                    >
                        <img
                            src={grocery}
                            width="40px"
                            height="40px"
                            alt="Grocery SVG" ></img>

                    </a>
                    <ul className="nav mx-4 col-12 justify-content-center me-lg-auto col-lg-auto">
                        <li>
                            <a href="/dishes" className="nav-link px-2 link-secondary">
                                Dish
                            </a>
                        </li>
                        <li>
                            <a href="/dishes" className="nav-link px-2 link-secondary">
                                Ingredient
                            </a>
                        </li>
                        <li>
                            <a href="/dishes" className="nav-link px-2 link-secondary">
                                Nutrient
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </header>
    )
}

export default HeaderComponent;