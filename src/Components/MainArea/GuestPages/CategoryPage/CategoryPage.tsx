import "./CategoryPage.css";
import {useParams} from "react-router-dom";
import axios from 'axios';
import {useEffect, useState} from 'react';
import Coupon from '../../../../Models/Coupon'
import CouponForPurchase from "../../../../ModelTemplates/CouponForPurchase/CouponForPurchase";
import { Row, Col,Container } from "reactstrap";

function CategoryPage(): JSX.Element {
    const [coupons, setCoupons] = useState<Coupon[]>([]);
    // @ts-ignore -> useParams returns an object
    const {categoryName} = useParams();
    const fetchCoupons = async () => {
        try {
            const { data : coupons } : { data : Coupon[] } = await axios.get(`/category/${categoryName}`); //axios returns data, we called it 'coupons', the type is Coupon[]
            setCoupons(coupons);
        } catch(e) {
            console.log(e)
        }
    }

    useEffect(() => {
        fetchCoupons();
    }, [categoryName])

    return (
        <div className="CategoryPage" >
            <h2 className='categoryTitle'>{categoryName}</h2>
                {coupons.map(item => <CouponForPurchase key={item.id} coupon={item} currentURL={`/category/${categoryName}`}/>)}
        </div>
    );
}

export default CategoryPage;
