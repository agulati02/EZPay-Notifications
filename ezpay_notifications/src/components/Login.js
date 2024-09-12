import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../assets/styles/login.css'; // Import the CSS file

export const Login = () => {
  const navigate = useNavigate();

  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');
  const [isAgreed, setIsAgreed] = useState(false);

  sessionStorage.setItem("loginToken", null);

  const handleLogin = () => {
    if (!userId || !userId.startsWith("U")) {
      alert('Please enter a valid username.');
      return;
    }

    if (!password) {
      alert('Please enter your password.');
      return;
    }

    if (!isAgreed) {
      alert('Please accept the terms and conditions.');
      return;
    }

    localStorage.setItem("userId", userId);
    localStorage.setItem("loginToken", "log123");
    navigate('/home');
  };

  return (
    <div className="container login-container">
      <div className="main-title">
        <div className="heading-div">
          <h1 className="heading">
            <span>EZ</span>Pay
          </h1>
        </div>
        <div className="subheading-div">
          <h2 className="subheading">SECURE AND EFFICIENT DIGITAL PAYMENT SOLUTION</h2>
        </div>
        <div className="desc-div">
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla vitae semper nulla, a ultrices tellus. Curabitur interdum finibus ex sit amet rhoncus. Nam ut nibh finibus, egestas libero quis, lacinia.
          </p>
        </div>
      </div>
      <div className="login-box">
        <h2 className="login-header">Login Page</h2>

        <div className="form-group mb-3">
          <label className="form-label">
            User ID <span style={{'color':'red'}}>*</span>
          </label>
          <input
            type="text"
            className="form-control"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            placeholder="Enter Username"
            required
          />
        </div>

        <div className="form-group mb-3">
          <label className="form-label">
            Password <span style={{'color':'red'}}>*</span>
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

        <button className="btn login-btn" onClick={handleLogin}>
          Login
        </button>
      </div>
    </div>
  );
};
