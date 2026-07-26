import React from "react";

function BlogDetails(props) {
  return (
    <div className="column">
      <h2>Blog Details</h2>

      {props.published ? (
        <>
          <h3>React Learning</h3>
          <h4>Stephen Biz</h4>
          <p>Welcome to learning React!</p>

          <h3>Installation</h3>
          <h4>Schewzdenier</h4>
          <p>You can install React from npm.</p>
        </>
      ) : (
        <p>No Blogs Published</p>
      )}
    </div>
  );
}

export default BlogDetails;