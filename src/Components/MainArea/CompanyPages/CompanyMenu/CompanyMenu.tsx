import "./CompanyMenu.css";
import { NavLink } from "react-router-dom";
import jwtAxios from "../../../../Authorization/jwtAxios";
import Company from "../../../../Models/Company";
import store from "../../../../Redux/Store";
import { companyDownloadAction } from "../../../../Redux/CompanyState";
import { useEffect } from "react";

function CompanyMenu(): JSX.Element {
    const fetchCompanies = async () => {
        try {
            const { data : companies } : { data : Company[] } = await jwtAxios.get(`/administrator/allCompanies`);
            store.dispatch(companyDownloadAction(companies));
        } catch(e) {
            console.log(e)
        }
    }

    useEffect(() => {
        fetchCompanies();
    }, [])
    
    return (
        <div className="Menu">
			<nav>
                <NavLink exact to="/company/all" style={{textDecoration: "none", color: "black", fontWeight:600}}>All Coupons</NavLink> <br/><br/>
                <NavLink exact to="/company/add" style={{textDecoration: "none", color: "black", fontWeight:600}}>Add New Coupon</NavLink> <br/><br/>
                <NavLink exact to="/company/details" style={{textDecoration: "none", color: "black", fontWeight:600}}>Company Details</NavLink> <br/><br/>
                <NavLink exact to="/company/maxPrice" style={{textDecoration: "none", color: "black", fontWeight:600}}>Coupons by Max Price</NavLink> <br/><br/>
                <NavLink exact to="/company/categoryMenu" style={{textDecoration: "none", color: "black", fontWeight:600}}>Coupons by Category</NavLink> <br/>
            </nav>
        </div>
    );
}

export default CompanyMenu;
