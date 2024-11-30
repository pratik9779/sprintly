import './App.css'
import Header from './components/Header'

function App() {

  return (
    <>
      {/* header */}
      <Header />

      <main className='min-h-screen'>
        {/* main content */}
      </main>

      <footer className='bg-gray-900 py-4'>
        <div className='container mx-auto px-4 text-center text-gray-200'>
          <p>Made by Pratik Sawant</p>
        </div>
      </footer>
    </>
  )
}

export default App
