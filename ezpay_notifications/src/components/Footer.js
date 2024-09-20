import React from 'react';

/**
 * Footer component that renders a footer section with dynamic copyright year.
 * 
 * @component
 * @returns {JSX.Element} JSX code to display the footer with dynamic year.
 * 
 * @author Jai Singh
 * @date 14/09/2024
 */
export const Footer = () => {
  return (
    <footer style={footerStyle}>
      <div className="container text-center py-3">
        <p style={textStyle}>
          {/* Display current year dynamically */}
          Copyright &copy; {new Date().getFullYear()} Ezpay.com
        </p>
      </div>
    </footer>
  );
};

/**
 * CSS styles applied to the footer element.
 * 
 * @constant
 * @type {Object}
 * @property {string} backgroundColor - The background color of the footer.
 * @property {string} color - The text color in the footer.
 * @property {string} padding - The padding around the footer.
 * @property {string} width - The width of the footer (set to 100%).
 * @property {string} height - The height of the footer (set to 3% of viewport height).
 */
const footerStyle = {
  backgroundColor: 'transparent', 
  color: '#fff', 
  padding: '20px 0', 
  width: '100%', 
  height: '3vh' 
};

/**
 * CSS styles applied to the text inside the footer.
 * 
 * @constant
 * @type {Object}
 * @property {string} margin - The margin applied to the text (set to 0).
 * @property {string} fontSize - The font size of the text.
 * @property {string} fontWeight - The weight of the text font (set to light).
 * @property {string} textAlign - The alignment of the text (centered).
 */
const textStyle = {
  margin: '0',
  fontSize: '1.1rem',
  fontWeight: '300', 
  textAlign: 'center' 
};
