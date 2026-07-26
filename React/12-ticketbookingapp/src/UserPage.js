import React from "react";

function UserPage() {
  return (
    <div>
      <h2>Book Your Ticket</h2>

      <div className="flight-card">
        <h3>✈️ Flight AI-101</h3>
        <p>Route: Hyderabad → Delhi</p>
        <p>Departure: 10:00 AM</p>
        <button className="book-btn">Book Now</button>
      </div>

      <div className="flight-card">
        <h3>✈️ Flight AI-202</h3>
        <p>Route: Hyderabad → Mumbai</p>
        <p>Departure: 1:00 PM</p>
        <button className="book-btn">Book Now</button>
      </div>
    </div>
  );
}

export default UserPage;