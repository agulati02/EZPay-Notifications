import { useNavigate } from 'react-router-dom';
import '../assets/styles/Header.css';

const Header = () => {
    const navigate = useNavigate();
    return (
        <>
            <div className="navbar">
                <div className="navbar-left">
                    <a href="#" class="nav-link" onClick={(e) => { navigate('/home') }} title="Home">
                        <span style={{'fontSize':'2.5rem', 'marginRight':'0', 'fontStyle':'italic'}}>EZPay</span>
                    </a>
                    <a href="#" class="nav-link" onClick={(e) => { navigate('/home') }} title="Home">
                        <i class="fi fi-ss-house-chimney"></i>
                    </a>
                    <a href="#" class="nav-link" onClick={(e) => {
                        localStorage.setItem("userId", null);
                        localStorage.removeItem("loginToken");
                        navigate('/');
                    }} title="Log out">
                        <i class="fi fi-bs-sign-out-alt"></i>
                    </a>
                </div>
                <div className="navbar-right">
                    <a href="#" class="nav-link" onClick={(e) => { navigate('/reminders') }} title="Reminders">
                        <i class="fi fi-br-alarm-clock"></i>
                        {/* Reminder */}
                    </a>
                    <a href="#" class="nav-link" onClick={(e)=> {navigate('/notification')}} title="Notifications">
                        <i class="fi fi-ss-bell-notification-social-media"></i>
                    </a>
                    <a href="#" class="nav-link">
                        <i class="fi fi-rs-circle-user" title="Profile"></i>
                    </a>
                </div>
            </div>
        </>
    )
};

export default Header;