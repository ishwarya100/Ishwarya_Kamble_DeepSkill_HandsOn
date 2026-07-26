import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

// React 17 render entry point, required for the Enzyme adapter used in this app
ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);
