import React from "react";
import Post from "./Post";

// class based component that fetches and displays a list of posts
class Posts extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: []
    };
  }

  // fetches posts from the API and stores them in state
  loadPosts() {
    fetch("https://jsonplaceholder.typicode.com/posts")
      .then((response) => response.json())
      .then((data) => {
        const postList = data.map((item) => new Post(item.id, item.title, item.body));
        this.setState({ posts: postList });
      })
      .catch((error) => {
        throw error;
      });
  }

  // load the posts as soon as the component mounts
  componentDidMount() {
    this.loadPosts();
  }

  // catch any rendering errors and alert the user
  componentDidCatch(error, info) {
    alert("Something went wrong while displaying the posts: " + error.message);
  }

  render() {
    return (
      <div>
        {this.state.posts.map((post) => (
          <div key={post.id}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
