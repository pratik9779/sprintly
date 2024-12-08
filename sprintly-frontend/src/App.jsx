import './App.css'
import { Suspense, lazy } from 'react'
import { Route, Routes } from "react-router-dom";
import { pageRoutes } from "./routes/routes";
import Layout from './components/Layout';
import ProtectedRoute from './components/ProtectedRoute';
const Dashboard = lazy(() => import('./pages/Dashboard'));
const Login = lazy(() => import('./pages/Login'));
const Register = lazy(() => import('./pages/Register'));
const ErrorPage = lazy(() => import('./pages/error'));

function App() {
  const fallbackLoader = (
    <div className='flex justify-center items-center h-screen'>
      {/* <Loader /> */}
      Loading...
    </div>
  )

  const SuspenseComponent = (element) => {
    return <Suspense fallback={fallbackLoader}>{element}</Suspense>
  }

  const authRoutes = [
    { path: pageRoutes.LOGIN, component: <Login /> },
    { path: pageRoutes.REGISTER, component: <Register /> },
  ]

  const routes = [
    { path: pageRoutes.DASHBOARD, component: <Dashboard /> },
  ]

  return <Routes>
    {authRoutes.map(({ path, component }) => (
      <Route key={path} path={path} element={SuspenseComponent(component)} />
    ))}

    <Route element={<Layout />}>
      {routes.map(({ path, component }) => (
        <Route key={path} path={path} element={<ProtectedRoute>{SuspenseComponent(component)}</ProtectedRoute>} />
      ))}
    </Route>

    <Route path='/*' element={SuspenseComponent(<ErrorPage />)} />
  </Routes>;
}

export default App
