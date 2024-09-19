// src/Component/Notification.js

// Author Names: Akhil Kholia, Jai Singh, Geethapriya T
// Date: 14/09/2024

import React, { useState, useEffect } from 'react';
import Header from "./Header"; // Custom Header component
import { FaTimes } from 'react-icons/fa'; // External module for displaying icons, specifically for delete icon
import NotificationTitle from './NotificationTitle'; // Custom component for displaying notification title
import '../assets/styles/Notification.css'; // Custom CSS for styling notifications
import BubblesBackground from './BubblesBackground'; // Custom animated bubbles background
import '../assets/styles/common-styles.css'; // Common styles used across the app

/**
 * A React component to display and manage notifications for the user.
 * Notifications are categorized based on the time period (Today, Yesterday, Earlier this week, Older).
 * 
 * External Modules:
 * - react-icons: For icon usage (FaTimes for the delete icon)
 * - Custom components like Header, NotificationTitle, BubblesBackground
 * - Custom styles imported from Notification.css and common-styles.css
 * 
 * @component
 * @returns {JSX.Element} The rendered notification display with functionality to delete and clear notifications.
 */
function Notification() {
    // State to manage notifications in different time categories
    const [notifications, setNotifications] = useState([]);
    const [todayNotif, setTodayNotif] = useState([]);
    const [yesterdayNotif, setYesterdayNotif] = useState([]);
    const [earlierThisWeekNotif, setEarlierThisWeekNotif] = useState([]);
    const [olderNotif, setOlderNotif] = useState([]);
    const [message, setMessage] = useState('');

    // useEffect to fetch notifications when the component mounts
    useEffect(() => {
        fetchNotifications();
    }, []);

    /**
     * Function to sort notifications based on the time period (Today, Yesterday, Earlier this Week, Older).
     * @param {Array} data - Array of notification objects
     */
    const sortDatesPeriodically = (data) => {
        const startOfDay = date => new Date(date.getFullYear(), date.getMonth(), date.getDate());
        const now = new Date();
        const todayStart = startOfDay(now);
        const yesterdayStart = new Date(todayStart);
        yesterdayStart.setDate(todayStart.getDate() - 1); // Calculate yesterday's date
        const mondayStart = new Date(todayStart);
        mondayStart.setDate(todayStart.getDate() - todayStart.getDay() + 1); // Calculate start of the week (Monday)

        // Initialize arrays for different periods
        const todayArray = [];
        const yesterdayArray = [];
        const earlierThisWeekArray = [];
        const olderArray = [];

        // Sort notifications based on their date
        data.forEach(notification => {
            const dateString = extractDateFromContent(notification.notificationContent);
            const date = startOfDay(new Date(dateString));

            if (date.getTime() === todayStart.getTime()) {
                todayArray.push(notification);
            } else if (date.getTime() === yesterdayStart.getTime()) {
                yesterdayArray.push(notification);
            } else if (date >= mondayStart && date < yesterdayStart) {
                earlierThisWeekArray.push(notification);
            } else {
                olderArray.push(notification);
            }
        });

        // Set state for each time period
        setTodayNotif(todayArray);
        setYesterdayNotif(yesterdayArray);
        setEarlierThisWeekNotif(earlierThisWeekArray);
        setOlderNotif(olderArray);
    }

    /**
     * Function to fetch notifications from the backend and categorize them by time period.
     */
    const fetchNotifications = async () => {
        setTodayNotif([]);
        setYesterdayNotif([]);
        setEarlierThisWeekNotif([]);
        setOlderNotif([]);

        try {
            const response = await fetch(`http://localhost:9090/api/notifications/${localStorage.getItem("userId")}`);
            const data = await response.json();

            if (data && data.length > 0) {
                const sortedData = sortNotificationsByDate(data);
                await sortDatesPeriodically(sortedData);
                setNotifications(sortedData);
                setMessage('');
            } else {
                setNotifications([]);
                setMessage('No notifications to show.');
            }
        } catch (error) {
            setMessage('Error fetching notifications, please try again.');
        }
    };

    /**
     * Helper function to sort notifications by date in descending order (latest first).
     * @param {Array} data - Array of notification objects
     * @returns {Array} Sorted array of notification objects
     */
    const sortNotificationsByDate = (data) => {
        return data.sort((a, b) => {
            const dateA = extractDateFromContent(a.notificationContent);
            const dateB = extractDateFromContent(b.notificationContent);
            return new Date(dateB) - new Date(dateA);
        });
    };

    /**
     * Helper function to extract the date from the notification content using regex.
     * @param {string} content - Notification content string
     * @returns {string} Extracted date string in 'yyyy-mm-dd' format
     */
    const extractDateFromContent = (content) => {
        const dateMatch = content.match(/\d{4}-\d{2}-\d{2}/);
        return dateMatch ? dateMatch[0] : '1970-01-01'; // Default to an old date if not found
    };

    /**
     * Function to clear all notifications by calling the API.
     */
    const handleClearNotifications = async () => {
        try {
            const response = await fetch(`http://localhost:9090/api/clear/${localStorage.getItem("userId")}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                setNotifications([]);
                setMessage('Notifications cleared successfully.');
            } else {
                setMessage('Error clearing notifications, please try again.');
            }

            // Clear individual notification categories
            setTodayNotif([]);
            setYesterdayNotif([]);
            setEarlierThisWeekNotif([]);
            setOlderNotif([]);
        } catch (error) {
            setMessage('Error clearing notifications, please try again.');
        }
    };

    /**
     * Function to delete a specific notification by ID.
     * @param {string} notificationId - The ID of the notification to delete
     */
    const handleDeleteNotification = async (notificationId) => {
        try {
            const response = await fetch(`http://localhost:9090/api/deleteNotification/${notificationId}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                setNotifications(notifications.filter(notification => notification.id !== notificationId));
                setMessage('Notification deleted successfully.');
            } else {
                setMessage('Error deleting notification.');
            }
            fetchNotifications();
        } catch (error) {
            setMessage('Error deleting notification.');
        }
    };

    /**
     * Function to format the notification content, adding styles for keywords like 'success' and 'failure'.
     * @param {string} content - The raw notification content string
     * @returns {object} Formatted HTML content
     */
    const formatNotificationContent = (content) => {
        const escapedContent = content.replace(/</g, "&lt;").replace(/>/g, "&gt;");

        const formattedContent = escapedContent
            .replace(/\b(success)\b/gi, '<span style="color: green; font-weight: bold;">$1</span>')
            .replace(/\b(failure)\b/gi, '<span style="color: red; font-weight: bold;">$1</span>')
            .replace(/\n/g, '<br />');

        return { __html: formattedContent };
    };

    return (
        <>
            <BubblesBackground />
            <Header />
            <div className="main-container">
                <NotificationTitle />
                {message && (
                    <div className='no-notifications'>
                        <div className='tick-mark'>
                            <i class="fi fi-rs-check-circle"></i><br />
                        </div>
                        <p className="mt-3 message">
                            <span>{message}</span>
                        </p>
                    </div>
                )}
                {/* Display categorized notifications */}
                {todayNotif.length > 0 && (
                    <div className="mt-3">
                        <div className="today-title-div">
                            <h4>Today</h4>
                        </div>
                        <ul className="list-group">
                            {todayNotif.map((notification) => (
                                <li key={notification.id} className="notification list-group-item d-flex justify-content-between align-items-center">
                                    <span className="rem-text" dangerouslySetInnerHTML={formatNotificationContent(notification.notificationContent)} />
                                    <button
                                        className="delete-notification-button"
                                        onClick={() => handleDeleteNotification(notification.id)}
                                    >
                                        <FaTimes className="text-danger position-absolute" style={{ cursor: 'pointer', top: '10px', right: '10px' }} />
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
                {/* Display Yesterday's notifications */}
                {yesterdayNotif.length > 0 && (
                    <div className="mt-3">
                        <div className="today-title-div">
                            <h4>Yesterday</h4>
                        </div>
                        <ul className="list-group">
                            {yesterdayNotif.map((notification) => (
                                <li key={notification.id} className="notification list-group-item d-flex justify-content-between align-items-center">
                                    <span className="rem-text" dangerouslySetInnerHTML={formatNotificationContent(notification.notificationContent)} />
                                    <button
                                        className="delete-notification-button"
                                        onClick={() => handleDeleteNotification(notification.id)}
                                    >
                                        <FaTimes className="text-danger position-absolute" style={{ cursor: 'pointer', top: '10px', right: '10px' }} />
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
                {/* Display earlier this week's notifications */}
                {earlierThisWeekNotif.length > 0 && (
                    <div className="mt-3">
                        <div className="today-title-div">
                            <h4>Earlier this week</h4>
                        </div>
                        <ul className="list-group">
                            {earlierThisWeekNotif.map((notification) => (
                                <li key={notification.id} className="notification list-group-item d-flex justify-content-between align-items-center">
                                    <span className="rem-text" dangerouslySetInnerHTML={formatNotificationContent(notification.notificationContent)} />
                                    <button
                                        className="delete-notification-button"
                                        onClick={() => handleDeleteNotification(notification.id)}
                                    >
                                        <FaTimes className="text-danger position-absolute" style={{ cursor: 'pointer', top: '10px', right: '10px' }} />
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
                {/* Display older notifications */}
                {olderNotif.length > 0 && (
                    <div className="mt-3">
                        <div className="today-title-div">
                            <h4>Older</h4>
                        </div>
                        <ul className="list-group">
                            {olderNotif.map((notification) => (
                                <li key={notification.id} className="notification list-group-item d-flex justify-content-between align-items-center">
                                    <span className="rem-text" dangerouslySetInnerHTML={formatNotificationContent(notification.notificationContent)} />
                                    <button
                                        className="delete-notification-button"
                                        onClick={() => handleDeleteNotification(notification.id)}
                                    >
                                        <FaTimes className="text-danger position-absolute" style={{ cursor: 'pointer', top: '10px', right: '10px' }} />
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
                {/* Clear all notifications button */}
                <div className="d-flex justify-content-center">
                    <button className="btn btn-danger" onClick={handleClearNotifications}>Clear All</button>
                </div>
            </div>
        </>
    );
}

export default Notification;
