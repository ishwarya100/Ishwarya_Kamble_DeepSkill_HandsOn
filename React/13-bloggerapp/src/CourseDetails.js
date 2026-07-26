import React from "react";

function CourseDetails(props) {
  return (
    <div className="column">
      <h2>Course Details</h2>

      {props.enrolled && (
        <>
          <h3>Angular</h3>
          <p>4/5/2021</p>

          <h3>React</h3>
          <p>6/3/2021</p>
        </>
      )}

      {!props.enrolled && <p>No Courses Available</p>}
    </div>
  );
}

export default CourseDetails;