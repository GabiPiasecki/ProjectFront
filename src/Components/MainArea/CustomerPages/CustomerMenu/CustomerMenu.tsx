import "./CustomerMenu.css";
import { NavLink } from "react-router-dom";
import { useEffect } from "react";
import Customer from "../../../../Models/Customer";
import store from "../../../../Redux/Store";
import jwtAxios from "../../../../Authorization/jwtAxios";
import { customerDownloadAction } from "../../../../Redux/CustomerState";

function CustomerMenu(): JSX.Element {
    const fetchCustomers = async () => {
        try {
            const { data : customers } : { data : Customer[] } = await jwtAxios.get(`/administrator/allCustomers`);
            store.dispatch(customerDownloadAction(customers));
        } catch(e) {
            console.log(e)
        }
    }

    useEffect(() => {
        fetchCustomers();
    }, [])

    return (
        <div className="CustomerMenu">
			<nav>
                <NavLink exact to="/customer/details" style={{textDecoration: "none", color: "black", fontWeight:600}}>Customer Details</NavLink> <br/><br/>
                <NavLink exact to="/customer/all" style={{textDecoration: "none", color: "black", fontWeight:600}}>All Purchased Coupons</NavLink> <br/><br/>
                <NavLink exact to="/customer/maxPrice" style={{textDecoration: "none", color: "black", fontWeight:600}}>Purchased Coupons by Max Price</NavLink> <br/><br/>
                <NavLink exact to="/customer/categoryMenu" style={{textDecoration: "none", color: "black", fontWeight:600}}>Purchased coupons by Category</NavLink> <br/><br/>
                <NavLink exact to="/customer/update" style={{textDecoration: "none", color: "black", fontWeight:600}}>Update Your Account Details</NavLink> <br/>
            </nav>
        </div>
    );
}

export default CustomerMenu;
