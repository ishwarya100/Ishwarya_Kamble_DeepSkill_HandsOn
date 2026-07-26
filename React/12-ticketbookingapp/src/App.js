import React, { useState } from "react";
import GuestPage from "./GuestPage";
import UserPage from "./UserPage";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <div className="container">
      <h1>✈️ Airline Ticket Booking System</h1>

      <div className="button-group">
        <button onClick={() => setIsLoggedIn(true)}>Login</button>
        <button onClick={() => setIsLoggedIn(false)}>Logout</button>
      </div>

      <p className="status">
        Status:
        <span className={isLoggedIn ? "online" : "offline"}>
          {isLoggedIn ? " Logged In" : " Guest User"}
        </span>
      </p>

      {isLoggedIn ? <UserPage /> : <GuestPage />}
    </div>
  );
}

export default App;