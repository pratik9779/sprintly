import { Outlet } from "react-router-dom"
import Header from "./Header"

const Layout = () => {
    return (
        <div>
            <Header />
            <Outlet />
            <footer className='bg-gray-900 py-4'>
                <div className='container mx-auto px-4 text-center text-gray-200'>
                    <p>Made by Pratik Sawant</p>
                </div>
            </footer>
        </div>
    )
}

export default Layout