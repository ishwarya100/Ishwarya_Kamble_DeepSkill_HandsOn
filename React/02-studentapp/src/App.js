import React from "react";
import Home from "./Components/Home";
import About from "./Components/About";
import Contact from "./Components/Contact";

// App component invokes the Home, About and Contact components
function App() {
  return (
    <div>
      <Home />
      <About />
      <Contact />
    </div>
  );
}

export default App;
