import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button,Form, FormGroup, Input, Label } from 'reactstrap';
import "../style/Product.css"

class EditProduct extends  Component{
    emptyItem = {
        name: '',
        price: '',
        quantity: '',
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});}


    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
        let product_Id = this.props.match.params.id

        await fetch(`/api/products/edit-product/`+ product_Id, {
            method: "PUT",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(item)

        });
        this.props.history.push('/products');

    }
    render() {
        const {item} = this.state;

        const title = <h2>{ 'Edit product' }</h2>;

        return <div>
            {title}
            <Form onSubmit={this.handleSubmit}>
                <FormGroup className={"form-group"}>
                    <Label> Name</Label>
                    <Input   className={"input-container"} placeholder="name" type="text" name="name" id="name" value={item.name}
                             onChange={this.handleChange} autoComplete="name"/>
                </FormGroup>
                <FormGroup  >
                    <Label > Price</Label>
                    <Input   className={"input-container"} placeholder="price" type="text" name="price" id="price" value={item.price}
                             onChange={this.handleChange} autoComplete="price"/>
                </FormGroup>
                <FormGroup >
                    <Label > Quantity</Label>
                    <Input   className={"input-container"} placeholder="quantity" type="text" name="quantity" id="name" value={item.quantity}
                             onChange={this.handleChange} autoComplete="quantity"/>
                </FormGroup>

                <FormGroup className={"actions-container-buttons"}>
                    <Button  className={"save-btn"} type="submit" onClick={this.handleSubmit} >Save</Button>
                    <Link className={"cancel-btn"} color="secondary" tag={Link} to="/products">Cancel</Link>
                </FormGroup>
            </Form>
        </div>
    }
}

export default EditProduct;