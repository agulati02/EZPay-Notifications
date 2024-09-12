// src/Component/Layout.js
import React from 'react';
import { Footer } from './Footer'; // Import the Footer component

const Layout = ({ children }) => {
  return (
    <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      <main style={{ flex: '1' }}>
        {children}
      </main>
      <Footer />
    </div>
  );
};

export default Layout;
