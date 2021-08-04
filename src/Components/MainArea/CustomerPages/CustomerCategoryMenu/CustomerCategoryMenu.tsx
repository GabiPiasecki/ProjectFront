import { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import jwtAxios from "../../../../Authorization/jwtAxios";
import Coupon from "../../../../Models/Coupon";
import { customerUpdateAction } from "../../../../Redux/CustomerState";
import store from "../../../../Redux/Store";
import "./CustomerCategoryMenu.css";

function CustomerCategoryMenu(): JSX.Element {
    const [coupons, setCoupons] = useState<Coupon[]>([]);
    const fetchCoupons = async () => {
        try {
            const { data : coupons } : { data : Coupon[] } = await jwtAxios.get(`/customer/all`);
            const customerEmail = store.getState().authState.user.email;
            const loggedCustomerArray = store.getState().customerState.customers.filter(function(item){return item.email === customerEmail});
            const loggedCustomer = loggedCustomerArray[0];
            loggedCustomer.coupons = coupons;
            store.dispatch(customerUpdateAction(loggedCustomer));
         setCoupons(coupons);
        } catch(e) {
            console.log(e)
        }
    }
    
    useEffect(() => {
        fetchCoupons();
    }, [])

    return (
        <div className="Menu">
			<nav>
                <h2> Choose a category</h2>
                <NavLink exact to="/customer/category/FOOD" style={{textDecoration: "none", color: "black", fontWeight:600}} > Food</NavLink> <br/><br/>
                <NavLink exact to="/customer/category/VACATION" style={{textDecoration: "none", color: "black", fontWeight:600}} > Vacation</NavLink> <br/><br/>
                <NavLink exact to="/customer/category/BEAUTY" style={{textDecoration: "none", color: "black", fontWeight:600}} > Beauty</NavLink> <br/><br/>
                <NavLink exact to="/customer/category/HOME" style={{textDecoration: "none", color: "black", fontWeight:600}} > Home</NavLink> <br/><br/>
                <NavLink exact to="/customer/category/ELECTRICITY" style={{textDecoration: "none", color: "black", fontWeight:600}} > Electricity</NavLink> <br/><br/>
                <NavLink exact to="/customer/category/FASHION" style={{textDecoration: "none", color: "black", fontWeight:600}} > Fashion</NavLink> <br/><br/>
                <NavLink exact to="/customer/category/SPORT" style={{textDecoration: "none", color: "black", fontWeight:600}} > Sport</NavLink> <br/><br/>
                <NavLink exact to="/customer/category/PETS" style={{textDecoration: "none", color: "black", fontWeight:600}} > Pets</NavLink> <br/><br/>
            </nav>
        </div>
    );
}

export default CustomerCategoryMenu;
