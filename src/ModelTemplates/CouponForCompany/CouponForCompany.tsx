import "./CouponForCompany.css";
import couponImage from "../../Assets/cf-256x256.png";
import Coupon from "../../Models/Coupon";
import { useHistory } from "react-router-dom";
import notify from "../../Services/Notify";
import store from "../../Redux/Store";
import { couponDeleteAction } from "../../Redux/CouponState";
import jwtAxios from "../../Authorization/jwtAxios";
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button } from 'reactstrap';
import coupon from "../../Assets/comp.jpg";

interface CouponForCompanyProps {
	coupon : Coupon;
}

function CouponForCompany(props: CouponForCompanyProps): JSX.Element {
    const history = useHistory();
    const deleteCoupon = async () => {
        try {
            await jwtAxios.delete(`/company/delete/${props.coupon.id}`);
            notify.success("The coupon was deleted successfully!");
            store.dispatch(couponDeleteAction(props.coupon));
            history.push("/company/all")
        } catch {
            notify.error("There was a problem with deleting the coupon!");
        }
    }

    const editCoupon = () => {
        history.push("/company/update/"+props.coupon.id);
    }

    return (
        <div className="CouponForCompany">
            <Card className="column" style={{flex: 1}}>
                <CardImg top width="100%" src={coupon} alt="Card image cap" className="CardImg" />
                <CardBody>
                    <CardTitle tag="h5">{props.coupon.title}</CardTitle>
                    <CardSubtitle tag="h6" className="mb-2 text-muted">{props.coupon.price}₪</CardSubtitle>
                    <CardText>{props.coupon.description}</CardText>
                    <Button size="sm" onClick={() => editCoupon()} className="button" >Edit Details</Button><br/>
                    <Button size="sm"  onClick={() => deleteCoupon()}className="button">Delete</Button>
                </CardBody>
            </Card>
        </div>
    );
}

export default CouponForCompany;
