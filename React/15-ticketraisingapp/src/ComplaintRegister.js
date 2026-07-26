import React from "react";

// registers an employee complaint and generates a reference number on submit
class ComplaintRegister extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      employeeName: "",
      complaint: ""
    };
    this.handleNameChange = this.handleNameChange.bind(this);
    this.handleComplaintChange = this.handleComplaintChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  // updates the employee name as the user types
  handleNameChange(event) {
    this.setState({ employeeName: event.target.value });
  }

  // updates the complaint text as the user types
  handleComplaintChange(event) {
    this.setState({ complaint: event.target.value });
  }

  // generates a reference number and shows it in an alert
  handleSubmit(event) {
  event.preventDefault();

  const transactionId = Math.floor(Math.random() * 100);

  alert(
    `Thanks ${this.state.employeeName}\n\n` +
    `Your Complaint was Submitted.\n` +
    `Transaction ID is: ${transactionId}`
  );
}

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <h2>Raise a Complaint</h2>
        <label>Employee Name</label>
        <input type="text" value={this.state.employeeName} onChange={this.handleNameChange} />
        <label>Complaint</label>
        <textarea value={this.state.complaint} onChange={this.handleComplaintChange} />
        <button type="submit">Submit</button>
      </form>
    );
  }
}

export default ComplaintRegister;
