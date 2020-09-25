import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css"

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
                <table className = "orderTable">
                    <tr>
                        <th>名字</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>单位</th>
                        <th>操作</th>
                    </tr>
                    {this.state.order.map((key,order) => (
                        <tr key={key}>
                            <td className ='row'>{order.name}</td>
                            <td className ='row'>{order.unitPrice}</td>
                            <td className ='row'>{order.amount}</td>
                            <td className ='row'>{order.unit}</td>
                            <td className ='row'>
                                <button type="button" class="btn btn-light">删除</button>
                            </td>
                        </tr>
                    ))}
                </table>
            </div>
        )
    }
}

export default Order;