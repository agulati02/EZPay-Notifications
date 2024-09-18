// src/App.js

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Login } from './components/Login';
import { Home } from './components/Home';
import Layout from './components/Layout'; 
import PaymentReminder from './components/PaymentReminder';
import Notification from './components/Notification';

/**
 * App component that sets up the main routing for the application using React Router.
 * It wraps the routes within a Layout component and uses a Router to navigate between pages.
 * 
 * @component
 * @returns {JSX.Element} The main application component with defined routes
 */
function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          {/* Route to Login page */}
          <Route path="/" element={<Login />} />

          {/* Route to Home page */}
          <Route path="/home" element={<Home />} />

          {/* Route to Payment Reminders page */}
          <Route path="/reminders" element={<PaymentReminder />} />

          {/* Route to Notifications page */}
          <Route path="/notification" element={<Notification />} />
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
