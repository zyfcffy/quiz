import React, { Component} from 'react';
import Product from './Product';

class Home extends Component {
    state = { 
        products: [],
    }

    componentDidMount(){
        fetch("http://127.0.0.1:8080/products",{method: "GET"})
        .then((response) => {
            if(response.ok){
                return response.json();
            }
            return Promise.reject(new Error(response.statusText));
        }).then((data)=>{
            this.setState({ 
                products:data
             });
        }).catch((error)=>{
            console.log(error);
        })
    }

    render() {
        return (
            <section className ='home'>
                {this.state.products.map((product,index)=>(
                    <Product key={index} className="product" product={product} />             
                ))}
            </section>
        )
    }
}

export default Home;