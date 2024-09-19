import React from 'react';
/*Author Name: Jai Singh & Date: 14/09/2024*/
// Footer component
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

// Footer styling
const footerStyle = {
  backgroundColor: 'transparent', // Set transparent background for the footer
  color: '#fff', // Set text color to white
  padding: '20px 0', // Add padding to the footer
  width: '100%', // Footer spans the full width
  height: '3vh' // Set the footer height to 3% of the viewport height
};

// Text styling
const textStyle = {
  margin: '0', // Remove default margin from the text
  fontSize: '1.1rem', // Set the font size
  fontWeight: '300', // Set font weight to light
  textAlign: 'center' // Center the text
};
