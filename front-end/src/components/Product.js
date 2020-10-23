import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css"
import '../styles/Product.css'

class Product extends Component {

    handleAddProductToOrder=(product)=>{
        const order = {
            name:product.name,
            amount:1,
            unitPrice:product.unitPrice,
            unit:product.unit,
        }

        fetch("http://127.0.0.1:8080/order",{
            method: "POST",
            body: JSON.stringify(order),
        }).then((response)=>{
        if(response.status===201){
            alert("添加成功");
            return;
        }
        return Promise.reject(new Error(response.statusText));
        }).catch((error)=>{
            console.log(error);
        })
    }

    handleAddProductToShoppingCart= async (product)=>{
        const shoppingCart = {
            name:product.name,
            amount:1,
            unitPrice:product.unitPrice,
            unit:product.unit,
        }
        await fetch("http://127.0.0.1:8080/shoppingCart",{
            method:"POST",
            body: JSON.stringify(shoppingCart),
        });
        alert("添加成功");
    }

    render() {
        const {product} = this.props;
        
        return (
            <div className="product">
                <div className="product-image">
                    <img className="product-image-info" src={product.image} />
                </div>
                <div className="product-info">
                    <p className="product-name">{product.name}</p>
                    <p className="product-price">单价：{product.unitPrice}{product.unit}</p>
                </div>
                <div className="product-add">
                    <button type="button" className="btn btn-light" onClick={()=>{this.handleAddProductToShoppingCart(product)}}>+</button>
                </div>
            </div>
        )
    }
}

export default Product;