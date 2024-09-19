import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../assets/styles/login.css';
import BubblesBackground from './BubblesBackground';
/*Author Name: ANurag Gulati , Date: 14/09/2024 and External Module: BubblesBackground*/
/**
 * The Login component handles user authentication by providing a login form with fields for User ID, password, and terms agreement.
 * It also displays background animations and handles navigation upon successful login.
 * 
 * @component
 * @returns {JSX.Element} The rendered login page component
 */
export const Login = () => {
  const navigate = useNavigate();

  // State hooks for managing user input
  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');
  const [isAgreed, setIsAgreed] = useState(false);

  // Reset login token on session start
  sessionStorage.setItem("loginToken", null);

  /**
   * Handles the login logic, including validation for user ID, password, and agreement checkbox.
   * Sets the user data in localStorage and navigates to the home page if successful.
   */
  const handleLogin = () => {
    // Validate user ID (should start with "U")
    if (!userId || !userId.startsWith("U")) {
      alert('Please enter a valid username.');
      return;
    }

    // Validate password
    if (!password) {
      alert('Please enter your password.');
      return;
    }

    // Ensure the user has agreed to terms and conditions
    if (!isAgreed) {
      alert('Please accept the terms and conditions.');
      return;
    }

    // Save login details and navigate to home
    localStorage.setItem("userId", userId);
    localStorage.setItem("loginToken", "log123");
    navigate('/home');
  };

  return (
    <>
      <BubblesBackground />
      <div className="container login-container">
        <div className="main-title">
          <div className="main-title-div">
            {/* Main title section */}
            <div className="heading-line-1">
              <h1>Your one-stop solution for</h1>
            </div>
            <div className="heading-div">
              <div className="heading">
                <div className="adj-list">
                  <ul>
                    <li><span>SECURE</span> <i className="fi fi-bs-padlock-check"></i></li>
                    <li><span>SMOOTH</span> <i className="fi fi-bs-gears"></i></li>
                    <li><span>QUICK</span> <i className="fi fi-bs-time-fast"></i></li>
                  </ul>
                </div>
              </div>
            </div>
            <div className="heading-line-2">
              <h1>digital payments.</h1>
            </div>
            <div className="desc-div">
              <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla vitae semper nulla, a ultrices tellus. Curabitur interdum finibus ex sit amet rhoncus. Nam ut nibh finibus, egestas libero quis, lacinia.
              </p>
            </div>
          </div>
        </div>
        <div className="login-box">
          <h2 className="login-header">
            <span>EZPay</span> Login
          </h2>

          {/* User ID input field */}
          <div className="form-group mb-3">
            <label className="form-label">
              User ID <span style={{ color: 'red' }}>*</span>
            </label>
            <input
              type="text"
              className="form-control"
              value={userId}
              onChange={(e) => setUserId(e.target.value)}
              placeholder="Enter User ID"
              required
            />
          </div>

          {/* Password input field */}
          <div className="form-group mb-3">
            <label className="form-label">
              Password <span style={{ color: 'red' }}>*</span>
            </label>
            <input
              type="password"
              className="form-control"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter Password"
              required
            />
          </div>

          {/* Terms and conditions checkbox */}
          <div className="form-check mb-3">
            <input
              type="checkbox"
              className="form-check-input"
              checked={isAgreed}
              onChange={(e) => setIsAgreed(e.target.checked)}
              id="agreementCheck"
            />
            <label className="form-check-label" htmlFor="agreementCheck">
              I accept the terms and conditions
            </label>
          </div>

          {/* Login button */}
          <button className="btn login-btn" onClick={handleLogin}>
            Login
          </button>
        </div>
      </div>
    </>
  );
};
