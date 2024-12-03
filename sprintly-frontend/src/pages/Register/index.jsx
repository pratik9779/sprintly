import { useEffect } from 'react'
import AuthForm from "../../components/AuthForm"
import { Link, useNavigate } from "react-router-dom"
import useAxios from "../../hooks/useAxios"

const Register = () => {
    const { response, error, loading, fetchData } = useAxios();
    const navigate = useNavigate()

    const registerUser = ({ username, email, password }) => {
        fetchData({
            url: "/register",
            method: "POST",
            data: {
                userName: username,
                email: email,
                password: password,
                role: "employee"
            }
        })
    }

    useEffect(() => {
        console.log(response)
        if (response) {
            navigate("/");
        }
    }, [response, navigate]);

    return (
        <main className="flex min-h-screen flex-col items-center justify-between p-24">
            <section className="w-4/5 max-w-5xl">
                {loading && <p>Loading...</p>}
                <div className="mb-8 flex flex-col gap-2">
                    <h1 className="text-3xl font-semibold">Register</h1>
                    <p className="text-sm text-neutral-500">
                        Already have an account?{" "}
                        <Link to="/login" className="underline underline-offset-4">
                            Login
                        </Link>
                    </p>
                </div>
                <AuthForm formtype="Register" registerUser={registerUser} />
                {error && <p className="text-red">{error}</p>}
            </section>
        </main>
    )
}

export default Register