import { useNavigate } from 'react-router-dom';
import '../assets/styles/Header.css';

/**
 * A React component that renders the navigation bar with links for home, logout, reminders, notifications, and profile.
 * 
 * @component
 * @returns {JSX.Element} The rendered navigation header
 * 
 * @author Anurag Gulati
 * @date 14/09/2024
 */
const Header = () => {
    const navigate = useNavigate();

    return (
        <>
            <div className="navbar">
                <div className="navbar-left">
                    {/* Home link with logo */}
                    <a href="#" className="nav-link" onClick={(e) => { navigate('/home') }} title="Home">
                        <span style={{ fontStyle: 'italic' }}>EZPay</span>
                    </a>
                </div>
                <div className="navbar-right">
                    {/* Home icon link */}
                    <a href="#" className="nav-link" onClick={(e) => { navigate('/home') }} title="Home">
                        <i className="fi fi-ss-house-chimney"></i> <span>Home</span>
                    </a>
                    {/* Reminders link */}
                    <a href="#" className="nav-link" onClick={(e) => { navigate('/reminders') }} title="Reminders">
                        <i className="fi fi-br-alarm-clock"></i> <span>Reminders</span>
                    </a>
                    {/* Notifications link */}
                    <a href="#" className="nav-link" onClick={(e) => { navigate('/notification') }} title="Notifications">
                        <i className="fi fi-ss-bell-notification-social-media"></i> <span>Notifications</span>
                    </a>
                    {/* Profile link */}
                    <a href="#" className="nav-link">
                        <i className="fi fi-rs-circle-user" title="Profile"></i> <span>Profile</span>
                    </a>
                    {/* Logout link */}
                    <a href="#" className="nav-link" onClick={(e) => {
                        sessionStorage.removeItem("userId");
                        sessionStorage.removeItem("loginToken");
                        navigate('/');
                    }} title="Log out">
                        <i className="fi fi-bs-sign-out-alt"></i> <span>Logout</span>
                    </a>
                </div>
            </div>
        </>
    );
};

export default Header;
