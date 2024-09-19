// src/Component/Layout.js

// Author Names: Jai Singh & Doneela Das
// Date: 14/09/2024
// External Module: Footer Component has been used
import React from 'react';
import { Footer } from './Footer'; // Import the Footer component

/**
 * Layout component to structure the main content and footer.
 * 
 * This component is designed to serve as the overall layout of the page,
 * ensuring that the main content (passed as children) is followed by the footer.
 * The layout is styled to stretch the main content and keep the footer at the bottom.
 * 
 * @component
 * @param {object} props - The component's props.
 * @param {JSX.Element} props.children - The main content to display within the layout.
 * @returns {JSX.Element} The rendered layout with the main content and footer.
 */
const Layout = ({ children }) => {
  return (
    <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      {/* Main content area which grows to fill the available space */}
      <main style={{ flex: '1' }}>
        {children}
      </main>
      
      {/* Footer component displayed at the bottom */}
      <Footer />
    </div>
  );
};

export default Layout;
