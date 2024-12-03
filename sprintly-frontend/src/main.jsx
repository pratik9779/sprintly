import { RouterProvider, createBrowserRouter } from "react-router-dom";
import { createRoot } from 'react-dom/client'
import './index.css'
import { ThemeProvider } from "@/components/theme-provider"
import { ModeToggle } from "@/components/mode-toggle"
import App from "./App";

const router = createBrowserRouter([
  {
    path: '/*',
    element: (
      <App />
    ),
  },
])

createRoot(document.getElementById('root')).render(
  <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
    <ModeToggle />
    <RouterProvider router={router} />
  </ThemeProvider>
)
