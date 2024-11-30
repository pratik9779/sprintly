import { useEffect, useState } from 'react'
import axios from "axios"

const useAxios = () => {
    const [response, setResponse] = useState(null)
    const [error, setError] = useState(null)
    const [loading, setLoading] = useState(null)

    const axiosInstance = axios.create({
        baseURL: "http://localhost:8080/api/"
    })

    axiosInstance.interceptors.request.use((config) => {
        return config;
    }, (error) => {
        return Promise.reject(error)
    })

    axiosInstance.interceptors.response.use((response) => {
        return response;
    }, (error) => {
        return Promise.reject(error)
    })

    let controller = new AbortController()

    useEffect(() => {
        return () => {
            controller?.abort()
        }
    }, [])

    const fetchData = async ({ url, method, data = {}, params = {} }) => {
        setLoading(true)

        setError(null);
        setResponse(null);

        controller.abort()
        controller = new AbortController()

        try {
            const result = await axiosInstance({
                url,
                method,
                data,
                params,
                signal: controller.signal
            })
            setError(null)
            setResponse(result.data)

        } catch (err) {
            if (axios.isCancel(error)) {
                console.warn("Request is been cancelled", error.message)
            } else {
                setResponse(null);
                setError(err.response?.data?.message || "An error occurred");
            }

        } finally {
            setLoading(false)
        }
    };

    return { response, error, loading, fetchData }
}

export default useAxios