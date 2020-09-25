import React,{Component} from 'react';
import './App.css';
import {BrowserRouter, NavLink,Switch,Route} from 'react-router-dom';
import Home from './components/Home';

class App extends Component {
  render() {
    return (
      <BrowserRouter>
      <header className ='header'>
        <nav className = 'bar'>
          <NavLink to='/' activeStyle={{backgroundColor:'blue'}}>商城</NavLink>
        </nav>
      </header>
      <Switch>
        <Route exact path="/" component={Home} />
      </Switch>
      </BrowserRouter>
    )
  }
}

export default App;
