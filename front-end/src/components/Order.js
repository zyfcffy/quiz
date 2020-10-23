import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css"
import '../styles/Order.css'

class Order extends Component {
    state = {
        order:[]
    }

    componentDidMount(){
        fetch("http://127.0.0.1:8080/orders",{method: "GET"})
        .then((response)=>{
            if(response.ok){
                return response.json();
            }
            return Promise.reject(new Error(response.statusText));
        }).then((data)=>{
            this.setState({
                order:data
             });
        }).catch((error)=>{
            console.log(error);
        })
    }

    handleDeleteOrder=(id)=>{
        fetch("http://127.0.0.1:8080/orders/"+id,{method:'DELETE'})
        .then((response)=>{
            if(response.status===204){
                alert("删除成功")
            }
            return Promise.reject(new Error(response.statusText));
        }).catch((error)=>{
            console.log(error);
        })
    }

    render() {
        return (
            <div className = 'orders'>
                <div className="order-header">
                    <button type="button" className="btn btn-light">删除</button>
                </div>
                <table className = "order-table">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>数量</th>
                        <th>单价</th>
                        <th>单位</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.order.map((key,index) => (
                        <tr key={index}>
                            <td>{key.name}</td>
                            <td>{key.unitPrice}</td>
                            <td>{key.amount}</td>
                            <td>{key.unit}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Order;