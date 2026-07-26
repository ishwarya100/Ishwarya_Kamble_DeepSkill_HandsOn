import React from "react";

// registration form that validates name, email and password on submit
class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "",
      email: "",
      password: "",
      errors: {}
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  // updates the field value as the user types
  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  // validates the fields and submits the form
  handleSubmit(event) {
  event.preventDefault();

  if (this.state.name.length < 5) {
    alert("Full Name must be 5 characters long!");
    return;
  }

  if (!this.state.email.includes("@") || !this.state.email.includes(".")) {
    alert("Please enter a valid Email Address!");
    return;
  }

  if (this.state.password.length < 8) {
    alert("Password must contain at least 8 characters!");
    return;
  }

  alert("Registration Successful!");
}

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <h2>Register</h2>
        <label>Name</label>
        <input type="text" name="name" value={this.state.name} onChange={this.handleChange} />
        <p>{this.state.errors.name}</p>

        <label>Email</label>
        <input type="text" name="email" value={this.state.email} onChange={this.handleChange} />
        <p>{this.state.errors.email}</p>

        <label>Password</label>
        <input type="password" name="password" value={this.state.password} onChange={this.handleChange} />
        <p>{this.state.errors.password}</p>

        <button type="submit">Register</button>
      </form>
    );
  }
}

export default Register;
