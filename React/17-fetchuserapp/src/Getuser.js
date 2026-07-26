import React from "react";

// fetches a random user and displays the title, first name and image
class Getuser extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      title: "",
      firstname: "",
      picture: ""
    };
  }

  // fetches the user details as soon as the component mounts
  async componentDidMount() {
    const response = await fetch("https://api.randomuser.me/");
    const data = await response.json();
    const user = data.results[0];
    this.setState({
      title: user.name.title,
      firstname: user.name.first,
      picture: user.picture.large
    });
  }

  render() {
    return (
      <div>
        <h2>{this.state.title} {this.state.firstname}</h2>
        <img src={this.state.picture} alt="user" />
      </div>
    );
  }
}

export default Getuser;
