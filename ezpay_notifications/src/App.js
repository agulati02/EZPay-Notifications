// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Login } from './components/Login';
import { Home } from './components/Home';
import Layout from './components/Layout'; // Import the Layout component
import PaymentReminder from './components/PaymentReminder';
import Notification from './components/Notification';

function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/home" element={<Home />} />
          <Route path="/reminders" element={<PaymentReminder />} />
          <Route path="/notification" element={<Notification />} />
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
