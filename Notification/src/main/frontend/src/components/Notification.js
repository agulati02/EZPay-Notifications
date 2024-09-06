import React, { useEffect, useState } from 'react';
import { getNotifications } from '../services/api';

const Notification = () => {
    const [notifications, setNotifications] = useState([]);

    useEffect(() => {
        getNotifications()
            .then(response => setNotifications(response.data))
            .catch(error => console.error('Error fetching notifications:', error));
    }, []);

    return (
        <div>
            <h2>Notifications</h2>
            <ul>
                {notifications.map(notification => (
                    <li key={notification.id}>
                        {notification.notificationContent} (Transaction ID: {notification.transactionId})
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Notification;
