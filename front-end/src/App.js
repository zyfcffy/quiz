import React,{Component} from 'react';
import './App.css';
import {BrowserRouter, NavLink,Switch,Route} from 'react-router-dom';
import Home from './components/Home';
import Order from './components/Order';
import AddProduct from './components/AddProduct';


class App extends Component {
  render() {
    return (
      <BrowserRouter>
      <header className ='header'>
        <nav className = 'bar'>
          <NavLink to='/' activeStyle={{backgroundColor:'blue'}}>商城</NavLink>
          <NavLink to='/order' activeStyle={{backgroundColor:'blue'}}>订单</NavLink>
          <NavLink to='/addProduct' activeStyle={{backgroundColor:'blue'}}>+添加商品</NavLink>
        </nav>
      </header>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route exact path="/order" component={Order} />
        <Route exact path="/addProduct" component={AddProduct} />
      </Switch>
      </BrowserRouter>
    )
  }
}

export default App;
