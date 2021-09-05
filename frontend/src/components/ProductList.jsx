import React, {Component} from 'react';
import {Button, ButtonGroup, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import "../style/ProductList.css"

class ProductList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            products: []
        };
        this.remove = this.remove.bind(this);
        this.handleReserved = this.handleReserved.bind(this);
    }

    componentDidMount() {
        fetch("/api/products/get-products")
            .then(response => response.json())
            .then(data => this.setState({products: data}))
    }

    async remove(id) {
        await fetch(`/api/products/delete-product/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }

        }).then(() => {
            let editProducts = [...this.state.products].filter(i => i.id !== id);
            this.setState({products: editProducts})
        });
    }

    async handleReserved(id) {
        const product = this.state.products.find(i => i.id === id);
        await fetch('/api/products/reserve-product',{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body : JSON.stringify(product)

        })  .then(response => response.json())
            .then((product)=>{
            console.log(product);
            let updatedProducts = [...this.state.products].map(i => {
                return i.id === product.id ? product : i;
            })
            this.setState({products: updatedProducts})
        })
    }

    render() {

        const {products, isLoading} = this.state;

        if (isLoading)
            return <p> Loading... </p>
        return (
            <div className={"product-list-container"}>
                <div className={"header"}>
                    <h3>List of products</h3>
                    <Button className={"add-button"} tag={Link} to="/products/create-new-product/new">Add a new
                        product</Button></div>
                <Table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {products && products.length && products.map(product => <tr key={product.id}>
                        <td style={{whiteSpace: 'nowrap'}}>{product.name}</td>
                        <td>{product.price + '€'}</td>
                        <td>{product.quantity + 'pcs'}</td>
                        <td>
                            <span className={`stock ${product.reserved ? "out": "in"}`} >
                                {product.reserved ? "Out of stock" : "In stock"}
                            </span>
                        </td>
                        <td>
                            <ButtonGroup className={"actions-container"}>
                                <Link className={"edit-btn"} to={"/products/edit-product/" + product.id}>Edit</Link>
                                <Button className="product-cart-button" onClick={() => this.handleReserved(product.id)}> Reserved</Button>
                                <Button className={"delete-btn"} onClick={() => this.remove(product.id)}>Delete</Button>

                            </ButtonGroup>
                        </td>
                    </tr>)}
                    </tbody>
                </Table>

            </div>
        )

    }

}


export default ProductList;