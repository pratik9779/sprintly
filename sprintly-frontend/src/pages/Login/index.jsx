import { useEffect } from 'react'
import AuthForm from "../../components/AuthForm"
import { Link, useNavigate } from "react-router-dom"
import useAxios from "../../hooks/useAxios"
import useAuth from "../../hooks/useAuth"

const Login = () => {
    const { response, error, loading, fetchData } = useAxios();
    const { isAuthenticated, setIsAuthenticated, authloading, setAuthLoading } = useAuth();
    const navigate = useNavigate()

    const loginUser = ({ username, email, password }) => {
        fetchData({
            url: "/login",
            method: "POST",
            data: {
                userName: username,
                email: email,
                password: password,
            }
        })
    }

    useEffect(() => {
        if (response) {
            setIsAuthenticated(true);
            navigate("/");
        }
    }, [response, navigate]);

    return (
        <main className="flex min-h-screen flex-col items-center justify-between p-24">
            <section className="w-4/5 max-w-5xl">
                {loading && <p>Loading...</p>}
                <div className="mb-8 flex flex-col gap-2">
                    <h1 className="text-3xl font-semibold">Login</h1>
                    <p className="text-sm text-neutral-500">
                        New here?{" "}
                        <Link to="/register" className="underline underline-offset-4">
                            Create an account
                        </Link>
                    </p>
                </div>
                <AuthForm formtype="Login" loginUser={loginUser} />
                {error && <p className="text-red">{error}</p>}
            </section>
        </main>
    )
}

export default Login