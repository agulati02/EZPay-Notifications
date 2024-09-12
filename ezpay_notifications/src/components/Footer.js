import React from 'react';

export const Footer = () => {
  return (
    <footer style={footerStyle}>
      <div className="container text-center py-3">
        <p style={textStyle}>
          Copyright &copy; {new Date().getFullYear()} Ezpay.com
        </p>
      </div>
    </footer>
  );
};

const footerStyle = {
  'background-color': 'transparent',
  color: '#fff',
  padding: '20px 0',
  // boxShadow: '0px -2px 10px rgba(0, 0, 0, 0.5)',
  width: '100%',
  height: '3vh'
  // marginTop: 'auto', // Pushes the footer to the bottom when content is minimal
};

const textStyle = {
  margin: '0',
  fontSize: '1.1rem',
  fontWeight: '300',
  textAlign: 'center',
};
