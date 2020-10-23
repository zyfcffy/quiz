import React, {Component} from 'react';
import Product from './Product';
import '../styles/Home.css'
import ShoppingCart from '../image/shoppingCart.png'

class Home extends Component {
    state = {
        products: [],
    }

    componentDidMount() {
        fetch("http://127.0.0.1:8080/products", {method: "GET"})
            .then((response) => {
                if (response.ok) {
                    return response.json();
                }
                return Promise.reject(new Error(response.statusText));
            }).then((data) => {
            console.log(data);
            this.setState({
                products: data
            });
        }).catch((error) => {
            console.log(error);
        })
    }

    render() {
        return (
            <div>
                <div className='home'>
                    {this.state.products.map((product, index) => (
                        <Product key={index} className="product" product={product}/>
                    ))}
                </div>
                <div className="shopping-cart">
                    <img className="shopping-cart-img" src={ShoppingCart} alt="Shopping Cart" />
                </div>
            </div>
        )
    }
}

export default Home;