import "./CustomerCategoryCoupons.css";
import {useParams} from "react-router-dom";
import store from "../../../../Redux/Store";
import CouponForCustomer from "../../../../ModelTemplates/CouponForCustomer/CouponForCustomer";

function CustomerCategoryCoupons(): JSX.Element {
    // @ts-ignore -> useParams returns an object, we need a string
    const {category} = useParams();
    const customerEmail = store.getState().authState.user.email;
    const loggedCustomerArray = store.getState().customerState.customers.filter(function(item){return item.email === customerEmail});
    const loggedCustomer = loggedCustomerArray[0];
    const coupons = loggedCustomer.coupons;
    const categoryCoupons = coupons.filter(function(item) {return item.category === category});

    return (
        <div className="CustomerCategoryCoupons">
            {categoryCoupons.map(item => <CouponForCustomer key={item.id} coupon={item}/>)}
        </div>
    );
}

export default CustomerCategoryCoupons;