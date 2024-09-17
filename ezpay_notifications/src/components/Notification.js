import React, { useState, useEffect } from 'react';
import Header from "./Header";
import { FaTimes } from 'react-icons/fa'; // Import the delete icon
import NotificationTitle from './NotificationTitle';
import '../assets/styles/Notification.css';
import BubblesBackground from './BubblesBackground';
import '../assets/styles/common-styles.css';

function Notification() {

    const [notifications, setNotifications] = useState([]);
    const [todayNotif, setTodayNotif] = useState([]);
    const [yesterdayNotif, setYesterdayNotif] = useState([]);
    const [earlierThisWeekNotif, setEarlierThisWeekNotif] = useState([]);
    const [olderNotif, setOlderNotif] = useState([]);
    const [message, setMessage] = useState('');

    useEffect(() => {
        fetchNotifications(); // Fetch notifications only if userId exists
    }, []); 

    // useEffect(() => {
    //     fetchNotifications(); // Fetch notifications only if userId exists
    // }, [todayNotif, yesterdayNotif, earlierThisWeekNotif, olderNotif]);

    const sortDatesPeriodically = (data) => {
        const startOfDay = date => new Date(date.getFullYear(), date.getMonth(), date.getDate());
        const now = new Date();
        const todayStart = startOfDay(now);
    
        // Get yesterday's date
        const yesterdayStart = new Date(todayStart);
        yesterdayStart.setDate(todayStart.getDate() - 1);
    
        // Get Monday's date of the current week
        const mondayStart = new Date(todayStart);
        mondayStart.setDate(todayStart.getDate() - todayStart.getDay() + 1);
    
        // Create new arrays to hold categorized notifications
        const todayArray = [];
        const yesterdayArray = [];
        const earlierThisWeekArray = [];
        const olderArray = [];
    
        // Function to categorize the transaction dates
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
    
        // Update state with new arrays
        setTodayNotif(todayArray);
        setYesterdayNotif(yesterdayArray);
        setEarlierThisWeekNotif(earlierThisWeekArray);
        setOlderNotif(olderArray);
    }
    

    const fetchNotifications = async () => {
        setTodayNotif([]);
        setYesterdayNotif([]);
        setEarlierThisWeekNotif([]);
        setOlderNotif([]);

        try {
            const response = await fetch(`http://localhost:9090/api/notifications/${localStorage.getItem("userId")}`);

            const data = await response.json();

            if (data && data.length > 0) {
                // Sort notifications by date before setting them
                const sortedData = sortNotificationsByDate(data);
                await sortDatesPeriodically(sortedData);
                // console.log(yesterdayNotif); 
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

    const sortNotificationsByDate = (data) => {
        // Extract date from the notification content using regex
        return data.sort((a, b) => {
            const dateA = extractDateFromContent(a.notificationContent);
            const dateB = extractDateFromContent(b.notificationContent);
            
            return new Date(dateB) - new Date(dateA); // Sort by latest date
        });
    };

    const extractDateFromContent = (content) => {
        const dateMatch = content.match(/\d{4}-\d{2}-\d{2}/); // Match the date in yyyy-mm-dd format
        return dateMatch ? dateMatch[0] : '1970-01-01'; // Default to an old date if no date is found
    };

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

            setTodayNotif([]);
            setYesterdayNotif([]);
            setEarlierThisWeekNotif([]);
            setOlderNotif([]);
        } catch (error) {
            setMessage('Error clearing notifications, please try again.');
        }
    };

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
                            <span>No notifications to display. You are all caught up!</span>
                        </p>
                    </div>
                )}
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
                                        <FaTimes
                                            className="text-danger position-absolute"
                                            style={{ cursor: 'pointer', top: '10px', right: '10px' }}
                                        />
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
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
                                        <FaTimes
                                            className="text-danger position-absolute"
                                            style={{ cursor: 'pointer', top: '10px', right: '10px' }}
                                        />
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
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
                                        <FaTimes
                                            className="text-danger position-absolute"
                                            style={{ cursor: 'pointer', top: '10px', right: '10px' }}
                                        />
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
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
                                        <FaTimes
                                            className="text-danger position-absolute"
                                            style={{ cursor: 'pointer', top: '10px', right: '10px' }}
                                        />
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
                {notifications.length > 0 && (
                    <div className="mt-3">
                        <div className='clear-notif-btn-div'>
                            <button onClick={handleClearNotifications} className="btn btn-danger mt-3">
                                Clear Notifications
                            </button>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
}

export default Notification;
