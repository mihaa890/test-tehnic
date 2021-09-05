import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import ProductList from "./components/ProductList";
import AddProduct from "./components/AddProduct";
import EditProduct from "./components/EditProduct";
import Home from "./Home";


class App extends Component {

  render() {
    return (
        <Router>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/products' exact={true} component={ProductList}/>
            <Route path='/products/get-products' exact={true} component={ProductList}/>
            <Route path='/products/create-new-product/new' exact={true} component={AddProduct}/>
            <Route path='/products/edit-product/:id' exact={true} component={EditProduct}/>
        </Router>
    )
  }
}

export default App;