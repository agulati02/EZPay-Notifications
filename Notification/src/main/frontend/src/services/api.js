import axios from 'axios';

const API_URL = 'http://localhost:8080/api'; // Replace with your actual backend URL

export const getNotifications = () => axios.get(`${API_URL}/notifications`);
export const getPaymentReminders = () => axios.get(`${API_URL}/payment-reminders`);
export const getTransactions = () => axios.get(`${API_URL}/transactions`);
export const getUsers = () => axios.get(`${API_URL}/users`);