import { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import jwtAxios from "../../../../Authorization/jwtAxios";
import Coupon from "../../../../Models/Coupon";
import { companyUpdateAction } from "../../../../Redux/CompanyState";
import store from "../../../../Redux/Store";
import "./CompanyCategoryMenu.css";

function CompanyCategoryMenu(): JSX.Element {
    const [coupons, setCoupons] = useState<Coupon[]>([]);
    const fetchCoupons = async () => {
        try {
            const { data : coupons } : { data : Coupon[] } = await jwtAxios.get(`/company/all`);
            const companyEmail = store.getState().authState.user.email;
            const loggedCompanyArray = store.getState().companyState.companies.filter(function(item){return item.email === companyEmail});
            const loggedCompany = loggedCompanyArray[0];
            loggedCompany.coupons = coupons;
            store.dispatch(companyUpdateAction(loggedCompany));
            setCoupons(coupons);
        } catch(e) {
            console.log(e)
        }
    }
    
    useEffect(() => {
        fetchCoupons();
    }, []);

    return (
        <div className="Menu">
			<nav>
                <h2> Choose a category</h2><br/>
                <NavLink exact to="/company/category/FOOD" style={{textDecoration: "none", color: "black", fontWeight:600}} > Food</NavLink> <br/><br/>
                <NavLink exact to="/company/category/VACATION" style={{textDecoration: "none", color: "black", fontWeight:600}} > Vacation</NavLink> <br/><br/>
                <NavLink exact to="/company/category/BEAUTY" style={{textDecoration: "none", color: "black", fontWeight:600}} > Beauty</NavLink> <br/><br/>
                <NavLink exact to="/company/category/HOME" style={{textDecoration: "none", color: "black", fontWeight:600}} > Home</NavLink> <br/><br/>
                <NavLink exact to="/company/category/ELECTRICITY" style={{textDecoration: "none", color: "black", fontWeight:600}} > Electricity</NavLink> <br/><br/>
                <NavLink exact to="/company/category/FASHION" style={{textDecoration: "none", color: "black", fontWeight:600}} > Fashion</NavLink> <br/><br/>
                <NavLink exact to="/company/category/SPORT" style={{textDecoration: "none", color: "black", fontWeight:600}} > Sport</NavLink> <br/><br/>
                <NavLink exact to="/company/category/PETS" style={{textDecoration: "none", color: "black", fontWeight:600}} > Pets</NavLink> <br/><br/>
            </nav>
        </div>
    );
}

export default CompanyCategoryMenu;
