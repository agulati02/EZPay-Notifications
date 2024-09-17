import React, { useEffect } from 'react';
import '../assets/styles/home.css';
import '../assets/styles/common-styles.css'
import Header from './Header';
import { useNavigate } from 'react-router-dom';
import BubblesBackground from './BubblesBackground';
import { authors } from './AppConstants';
import AuthorCard from './AuthorCard';

export const Home = () => {

  const navigate = useNavigate();

  useEffect(() => {
    let loginExists = localStorage.getItem("loginToken");
    console.log(loginExists);
    if (loginExists === null) {
      navigate('/');
    }
  }, []);

  return (
    <>
      <BubblesBackground />
      <Header />
      <div className="main-container">
        {/* Welcome Heading */}
        <div className="welcome-section">
          <h1 className="welcome-heading">WELCOME TO <span>EZPay</span></h1>
        </div>

        {/* Authors Heading */}
        <div className="authors-heading">
          <h2>Authors</h2>
        </div>

        {/* Author Sections */}
        <div className="author-sections">
        {
          authors.map((author, index) => {
            return (
              <AuthorCard author={author} />
            )
          })
        }
        </div>
      </div>
    </>
  );
};
