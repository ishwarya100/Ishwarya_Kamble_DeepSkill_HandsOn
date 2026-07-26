import React from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Home from "./Home";
import TrainersList from "./TrainersList";
import TrainerDetails from "./TrainerDetails";
import TrainersMock from "./TrainersMock";

// App component defines the navigation links and the routes of the app
function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Home</Link> | <Link to="/trainers">Trainers</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/trainers" element={<TrainersList trainers={TrainersMock} />} />
        <Route path="/trainers/:id" element={<TrainerDetails />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
