import React, { useEffect } from 'react';
import '../assets/styles/home.css';
import '../assets/styles/common-styles.css';
import Header from './Header';
import { useNavigate } from 'react-router-dom';
import BubblesBackground from './BubblesBackground';
import { authors } from './AppConstants';
import AuthorCard from './AuthorCard';
/*Author Name: Anurag Gulati & Jai Singh and Date: 14/09/2024*/
/*External Module: Author Card, Bubbles Baackground and Header is being Used*/
/**
 * The Home component serves as the main page of the EZPay application.
 * It includes a header, a background animation of bubbles, a welcome message, and a list of authors.
 * 
 * @component
 * @returns {JSX.Element} The rendered Home component
 */
export const Home = () => {

  const navigate = useNavigate();

  /**
   * useEffect hook to check if the user is logged in by verifying the presence of the "loginToken" in localStorage.
   * If the token is missing, the user is redirected to the login page.
   */
  useEffect(() => {
    let loginExists = localStorage.getItem("loginToken");
    console.log(loginExists);
    if (loginExists === null) {
      navigate('/');
    }
  }, [navigate]);

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
          {authors.map((author, index) => (
            <AuthorCard key={index} author={author} />
          ))}
        </div>
      </div>
    </>
  );
};
