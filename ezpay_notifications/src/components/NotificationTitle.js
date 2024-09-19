// src/Component/NotificationTitle.js

// Author Names: Akhil Kholia, Jai Singh, Geethapriya T
// Date: 14/09/2024

import '../assets/styles/NotificationTitle.css'; // Import custom CSS for styling the notification title

/**
 * A simple functional component that displays the title for the notifications section.
 * 
 * External Modules:
 * - NotificationTitle.css: Custom styling for the notification title.
 * 
 * @component
 * @returns {JSX.Element} The rendered notification title.
 */
const NotificationTitle = () => {
    return (
        <>
            <div className="notification-title">
                <h3>Notifications</h3>
            </div>
        </>
    )
};

export default NotificationTitle;
