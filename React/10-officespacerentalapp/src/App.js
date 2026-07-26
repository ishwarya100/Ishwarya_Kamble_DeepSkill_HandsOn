import React from "react";

// heading element created using JSX
const heading = <h1>Office Space Rentals</h1>;

// single office object with its details
const office = {
  name: "Skyline Business Center",
  rent: 75000,
  address: "MG Road, Bengaluru"
};

// list of office objects used to render multiple listings
const officeList = [
  {
    name: "Skyline Business Center",
    rent: 75000,
    address: "MG Road, Bengaluru"
  },
  {
    name: "Riverside Offices",
    rent: 45000,
    address: "Banjara Hills, Hyderabad"
  },
  {
    name: "Tech Park Suites",
    rent: 62000,
    address: "Hitech City, Hyderabad"
  }
];

// App component renders the heading, the image, the featured office and the office list
function App() {
  return (
    <div>
      {heading}

      {/* Image from the public folder */}
      <img
        src="/image.jpg"
        alt="Office Space"
        width="300"
      />

      <h2>Featured Office</h2>
      <p>Name: {office.name}</p>
      <p>Address: {office.address}</p>
      <p style={{ color: office.rent < 60000 ? "red" : "green" }}>
        Rent: {office.rent}
      </p>

      <h2>All Offices</h2>

      {officeList.map((item, index) => (
        <div key={index}>
          <p>Name: {item.name}</p>
          <p>Address: {item.address}</p>
          <p style={{ color: item.rent < 60000 ? "red" : "green" }}>
            Rent: {item.rent}
          </p>
          <hr />
        </div>
      ))}
    </div>
  );
}

export default App;