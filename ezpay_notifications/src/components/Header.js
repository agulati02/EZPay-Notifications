import { useNavigate } from 'react-router-dom';
import '../assets/styles/Header.css';
/*Author Name: Anurag Gulati and Date: 14/09/2024
external module: header.css has been used for styling purpose*/
/**
 * A React component that renders the navigation bar with links for home, logout, reminders, notifications, and profile.
 * 
 * @component
 * @returns {JSX.Element} The rendered navigation header
 */
const Header = () => {
    const navigate = useNavigate();

    return (
        <>
            <div className="navbar">
                <div className="navbar-left">
                    {/* Home link with logo */}
                    <a href="#" className="nav-link" onClick={(e) => { navigate('/home') }} title="Home">
                        <span style={{ fontSize: '2.5rem', marginRight: '0', fontStyle: 'italic' }}>EZPay</span>
                    </a>
                    {/* Home icon link */}
                    <a href="#" className="nav-link" onClick={(e) => { navigate('/home') }} title="Home">
                        <i className="fi fi-ss-house-chimney"></i>
                    </a>
                    {/* Logout link */}
                    <a href="#" className="nav-link" onClick={(e) => {
                        localStorage.setItem("userId", null);
                        localStorage.removeItem("loginToken");
                        navigate('/');
                    }} title="Log out">
                        <i className="fi fi-bs-sign-out-alt"></i>
                    </a>
                </div>
                <div className="navbar-right">
                    {/* Reminders link */}
                    <a href="#" className="nav-link" onClick={(e) => { navigate('/reminders') }} title="Reminders">
                        <i className="fi fi-br-alarm-clock"></i>
                    </a>
                    {/* Notifications link */}
                    <a href="#" className="nav-link" onClick={(e) => { navigate('/notification') }} title="Notifications">
                        <i className="fi fi-ss-bell-notification-social-media"></i>
                    </a>
                    {/* Profile link */}
                    <a href="#" className="nav-link">
                        <i className="fi fi-rs-circle-user" title="Profile"></i>
                    </a>
                </div>
            </div>
        </>
    );
};

export default Header;
