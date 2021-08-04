import "./CouponForCustomer.css";
import couponImage from "../../Assets/cf-256x256.png";
import Coupon from "../../Models/Coupon";
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button, Row, Col } from 'reactstrap';
import coupon from "../../Assets/cf-256x256.png";

interface CouponForCustomerProps {
	coupon : Coupon;
}


function CouponForCustomer(props: CouponForCustomerProps): JSX.Element {

    return (
        <div className="CouponForCustomer">
            <Card className="column" style={{flex: 1}}>
                <CardImg top width="100%" src={coupon} alt="Card image cap" />
                <CardBody>
                    <CardTitle tag="h5">{props.coupon.title}</CardTitle>
                    <CardSubtitle tag="h6" className="mb-2 text-muted">{props.coupon.price}</CardSubtitle>
                    <CardText>{props.coupon.description}</CardText>
                </CardBody>
            </Card>
        </div>
    );
}

export default CouponForCustomer;