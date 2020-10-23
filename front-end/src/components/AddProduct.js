import React, {Component} from 'react';
import "bootstrap/dist/css/bootstrap.css"
import '../styles/AddProduct.css'

class AddProduct extends Component {
    state = {
        product: {
            name: '',
            unitPrice: 0,
            unit: '',
            image: '',
        }
    }

    handleEventChange = (field, event) => {
        this.setState({
            [field]: event.target.value
        })
    }

    handleSubmit = () => {
        let product = {
            name: this.state.product.name,
            unitPrice: this.state.product.unitPrice,
            unit: this.state.product.unit,
            image: this.state.product.image
        }

        fetch("http://127.0.0.1:8080/product", {
            method: "POST",
            body: JSON.stringify(product),
        }).then((response) => {
            if (response.status === 201) {
                alert("添加成功");
                return;
            }
            return Promise.reject(new Error(response.statusText));
        }).catch((error) => {
            console.log(error);
        })
    }


    render() {
        return (
            <form className="productForm">
                <h1>添加商品</h1>
                <div className="form-group">
                    <p>*<label htmlFor="name">名称</label></p>
                    <input className='form-control' id='name' type='text' value={this.state.product.name}
                           placeholder='名称' onChange={(e) => this.handleEventChange("name", e)}></input>
                    <p>*<label htmlFor="price">价格</label></p>
                    <input className='form-control' id='unitPrice' type='text' value={this.state.product.unitPrice}
                           placeholder='价格' onChange={(e) => this.handleEventChange("unitPrice", e)}></input>
                    <p>*<label htmlFor="unit">单位</label></p>
                    <input className='form-control' id='unit' type='text' value={this.state.product.unit}
                           placeholder='单位' onChange={(e) => this.handleEventChange("unit", e)}></input>
                    <p>*<label htmlFor="img">图片</label></p>
                    <input className='form-control' id='image' type='text' value={this.state.product.image}
                           placeholder='url' onChange={(e) => this.handleEventChange("image", e)}></input>
                    <input type="submit" className="btn btn-primary" value="提交"
                           onClick={this.handleSubmit}
                           disabled={this.state.product.name === '' || !(this.state.product.unitPrice > 0) || this.state.product.unit === "" || this.state.product.image === ""}></input>
                </div>
            </form>
        )
    }
}

export default AddProduct;