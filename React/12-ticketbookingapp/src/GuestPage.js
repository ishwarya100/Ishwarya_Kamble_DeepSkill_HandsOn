import React from "react";

function GuestPage() {
  return (
    <div>
      <h2>Available Flights</h2>

      <div className="flight-card">
        <h3>✈️ Flight AI-101</h3>
        <p>Route: Hyderabad → Delhi</p>
        <p>Departure: 10:00 AM</p>
        <p className="available">Available</p>
      </div>

      <div className="flight-card">
        <h3>✈️ Flight AI-202</h3>
        <p>Route: Hyderabad → Mumbai</p>
        <p>Departure: 1:00 PM</p>
        <p className="available">Available</p>
      </div>

      <p className="message">
        Please login to book your tickets.
      </p>
    </div>
  );
}

export default GuestPage;