import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import CreteUrl from './CreateUrl.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <CreteUrl />
  </StrictMode>,
)
