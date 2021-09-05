import React, {Component} from 'react';
import {Link} from "react-router-dom";

class Home extends  Component{
    render() {
        return (
            <div>
                <Link className={"primary-button"} to="/products">List of products</Link>
            </div>
        );
    }

}

export default Home;