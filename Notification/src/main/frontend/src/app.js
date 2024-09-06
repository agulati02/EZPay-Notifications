import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Notification from '../components/Notification';
import PaymentReminder from './components/PaymentReminder';
import Transaction from './components/Transaction';
import User from './components/User';

function App() {
    return (
        <Router>
            <div>
                <h1>EZPay Management System</h1>
                <Routes>
                    <Route path="/notifications" element={<Notification />} />
                    <Route path="/payment-reminders" element={<PaymentReminder />} />
                    <Route path="/transactions" element={<Transaction />} />
                    <Route path="/users" element={<User />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
