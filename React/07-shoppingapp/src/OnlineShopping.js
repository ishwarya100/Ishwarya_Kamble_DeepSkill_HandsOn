import React from "react";
import Cart from "./Cart";

// OnlineShopping class component holds an array of cart items and renders them
class OnlineShopping extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [
        { itemname: "Shoes", price: 1999 },
        { itemname: "T-Shirt", price: 799 },
        { itemname: "Watch", price: 2999 },
        { itemname: "Backpack", price: 1499 },
        { itemname: "Sunglasses", price: 599 }
      ]
    };
  }

  render() {
    return (
      <div>
        <h2>Shopping Cart</h2>
        {this.state.items.map((item, index) => (
          <Cart key={index} itemname={item.itemname} price={item.price} />
        ))}
      </div>
    );
  }
}

export default OnlineShopping;
