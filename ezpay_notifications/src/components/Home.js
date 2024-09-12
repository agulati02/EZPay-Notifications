import React, { useEffect } from 'react';
import '../assets/styles/home.css'; // Import the CSS file for styling
import Header from './Header';
import { useNavigate } from 'react-router-dom';

export const Home = () => {

  const navigate = useNavigate();

  useEffect(() => {
    let loginExists = localStorage.getItem("loginToken");
    console.log(loginExists);
    if(loginExists === null) {
      navigate('/');
    }
  }, []);

  return (
    <>
    <Header />
    <div className="home-container">
      {/* Welcome Heading */}
      <div className="welcome-section">
        <h1 className="welcome-heading">Welcome to EZPay</h1>
      </div>

      {/* Authors Heading */}
      <div className="authors-heading">
        <h2>Authors</h2>
      </div>

      {/* Author Sections */}
      <div className="author-sections">
        <div className="author-section">
          <h3>Akhil Kholia</h3>
          <p>
            Software Engineer <br />
            <b>Email:</b> akhilkholia9@gmail.com
          </p>
        </div>
        <div className="author-section">
          <h3>Anurag Gulati</h3>
          <p>
            Software Engineer <br />
            <b>Email:</b> anuraggulati1902@gmail.com
          </p>
        </div>
        <div className="author-section">
          <h3>Doneela Das</h3>
          <p>
            Software Engineer <br />
            <b>Email:</b> doneeladas@gmail.com
          </p>
        </div>
        <div className="author-section">
          <h3>Geethapriya Thandavamurthi</h3>
          <p>
          Software Engineer <br />
            <b>Email:</b> geethathands@gmail.com
          </p>
        </div>
        <div className="author-section">
          <h3>Jai Singh</h3>
          <p>
          Software Engineer <br />
            <b>Email:</b> sjai48578@gmail.com
          </p>
        </div>
      </div>
    </div>
    </>
  );
};
