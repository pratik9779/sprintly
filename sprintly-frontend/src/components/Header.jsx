import logo from '../assets/logo.png'
import { Button } from './ui/button'
import { useNavigate } from "react-router-dom"

const Header = () => {
    const navigate = useNavigate()

    const logout = () => {
        localStorage.removeItem("token");
        navigate("/login");
    }

    return (
        <div>
            {/* <img src={logo} alt="logo" /> */}
            HEADER

            <Button onClick={logout} >Logout</Button>
        </div>
    )
}

export default Header