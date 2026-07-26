import React from "react";
import BookDetails from "./BookDetails";
import BlogDetails from "./BlogDetails";
import CourseDetails from "./CourseDetails";

function App() {
  return (
    <div className="container">
      <CourseDetails enrolled={true} />
      <BookDetails available={true} />
      <BlogDetails published={true} />
    </div>
  );
}

export default App;