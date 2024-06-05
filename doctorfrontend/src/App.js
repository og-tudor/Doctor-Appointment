import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ClientPage from './components/ClientPage';
import BackPage from './components/BackPage';
import Appbar from './components/Appbar';

function App() {
  return (
    <Router>
      <div>
        <Appbar />
        <Routes>
          <Route path="/" element={<ClientPage />} />
          <Route path="/back" element={<BackPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
