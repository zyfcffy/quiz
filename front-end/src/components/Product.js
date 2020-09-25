import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css"

class Product extends Component {

    handleAddProductToOrder=(product)=>{
        
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