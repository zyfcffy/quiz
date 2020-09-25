import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css"

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

    render() {
        const {product} = this.props;
        
        return (
            <section className="product">
                <div className="productInfo">
                    <p className="productName">{product.name}</p>
                    <p className="productPrice">单价：{product.unitPrice}{product.unit}</p>
                    <button type="button" class="btn btn-light" onClick={()=>{this.handleAddProductToOrder(product)}}>+</button> 
                </div>
            </section>
        )
    }
}

export default Product;