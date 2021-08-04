import "./AdminMenu.css";
import { NavLink } from "react-router-dom";

function AdminMenu(): JSX.Element {
    return (
        <div className="Menu">
			<nav>
                <NavLink exact to="/administrator/allCompanies" style={{textDecoration: "none", color: "black", fontWeight:600}}>All Companies</NavLink> <br/><br/>
                <NavLink exact to="/administrator/allCustomers" style={{textDecoration: "none", color: "black", fontWeight:600}}>All Customers</NavLink> <br/><br/>
                <NavLink exact to="/administrator/company/add" style={{textDecoration: "none", color: "black", fontWeight:600}}>Add New Company</NavLink> <br/>
            </nav>
        </div>
    );
}

export default AdminMenu;
