import "./CustomerCoupons.css";
import {useEffect, useState} from 'react';
import Coupon from "../../../../Models/Coupon";
import CouponForCustomer from "../../../../ModelTemplates/CouponForCustomer/CouponForCustomer";
import jwtAxios from "../../../../Authorization/jwtAxios";

function CustomerCoupons(): JSX.Element {
    const [coupons, setCoupons] = useState<Coupon[]>([]);
    const fetchCoupons = async () => {
        try {
            const { data : coupons } : { data : Coupon[] } = await jwtAxios.get(`/customer/all`);
            setCoupons(coupons);
        } catch(e) {
            console.log(e)
        }
    }
    
    useEffect(() => {
        fetchCoupons();
    }, [])

    return (
        <div className="CustomerCoupons">
			<h2 className='customerTitle'>Customer logged in coupons:</h2>
            {coupons.map(item => <CouponForCustomer key={item.id} coupon={item}/>)}
        </div>
    );
}

export default CustomerCoupons;
