import React from "react";

// Cart class component holds a single item name and price
class Cart extends React.Component {
  render() {
    return (
      <div>
        <p>Item: {this.props.itemname}</p>
        <p>Price: {this.props.price}</p>
      </div>
    );
  }
}

export default Cart;
