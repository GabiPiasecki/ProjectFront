import "./CouponForPurchase.css";
import Coupon from "../../Models/Coupon";
import jwtAxios from "../../Authorization/jwtAxios";
import notify from "../../Services/Notify";
import store from "../../Redux/Store";
import { couponUpdateAction } from "../../Redux/CouponState";
import { customerUpdateAction } from "../../Redux/CustomerState";
import { Component } from "react";
import { Redirect } from "react-router-dom";
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button } from 'reactstrap';
import coupon from "../../Assets/comp.jpg";

interface CouponForPurchaseState {
    userLogged : boolean;
    route: string;
}
 
interface CouponForPurchaseProps {
	coupon : Coupon;
    currentURL : string;
}

class CouponForPurchase extends Component<CouponForPurchaseProps, CouponForPurchaseState> {
    public constructor(props: CouponForPurchaseProps) {
        super(props);
        this.state = {
            userLogged: store.getState().authState.isLogged,
            route: this.props.currentURL
        };
        store.subscribe(() => {
            this.setState({
                userLogged: store.getState().authState.isLogged
            });
        })
    }

    public purchaseCoupon = async (coupon:Coupon) => {
        try{
            const response = await jwtAxios.post<Coupon>("http://localhost:8080/customer/purchase",coupon);
            coupon.amount=coupon.amount-1;
            store.dispatch(couponUpdateAction(coupon));
            const customerEmail = store.getState().authState.user.email;
            const loggedCustomerArray=store.getState().customerState.customers.filter(function(item){return item.email === customerEmail});
            const loggedCustomer = loggedCustomerArray[0];
            loggedCustomer.coupons.push(coupon);
            store.dispatch(customerUpdateAction(loggedCustomer));
            this.setState({
                route: "/customer/all"
            })
            notify.success("Coupon purchase was successful!");
        } catch {
            notify.error("There was a problem with purchase!");
        }
    }

    private loginRoute = () => {
        this.setState({
            route: "/login"
        })
    }

    public render(): JSX.Element {
        if (this.state.userLogged){
            return (
                <div className="CouponForPurchase">
                    <Card className="column" style={{flex: 1}}>
                        <CardImg top width="100%" src={coupon} alt="Card image cap" className="CardImg" />
                        <CardBody>
                            <CardTitle tag="h5">{this.props.coupon.title}</CardTitle>
                            <CardSubtitle tag="h6" className="mb-2 text-muted">{this.props.coupon.price}₪</CardSubtitle>
                            <CardText>{this.props.coupon.description}</CardText>
                            <Button onClick={() => this.purchaseCoupon(this.props.coupon)}>Add to cart
                                <Redirect to={this.state.route} />
                            </Button>
                        </CardBody>
                    </Card>
                </div>
            );
        } else{
            return (
                <div className="CouponForPurchase">
                    <Card className="column" style={{flex: 1}}>
                        <CardImg top width="100%" src={coupon} alt="Card image cap" />
                        <CardBody>
                            <CardTitle tag="h5">{this.props.coupon.title}</CardTitle>
                            <CardSubtitle tag="h6" className="mb-2 text-muted">{this.props.coupon.price}₪</CardSubtitle>
                            <CardText>{this.props.coupon.description}</CardText>
                            <Button onClick={() => this.loginRoute()}>Add to cart
                                <Redirect to={this.state.route} />
                            </Button>
                        </CardBody>
                    </Card>
                </div>
            );
         }
    }
}
 
export default CouponForPurchase;



// function CouponForPurchase(props: CouponForPurchaseProps): JSX.Element {
//      const history=useHistory();
//      const purchaseCoupon =async (coupon:Coupon) => {
//         try{
//             const response = await jwtAxios.post<Coupon>("http://localhost:8080/customer/purchase",coupon);
//             coupon.amount=coupon.amount-1;
//             store.dispatch(couponUpdateAction(coupon));
//             const customerEmail = store.getState().authState.user.email;
//             const loggedCustomerArray=store.getState().customerState.customers.filter(function(item){return item.email==customerEmail});
//             const loggedCustomer = loggedCustomerArray[0];
//             loggedCustomer.coupons.push(coupon);
//             store.dispatch(customerUpdateAction(loggedCustomer));
//             history.push("/customer/all");
//             notify.success("purchase was successful!");
//         } catch {
//             notify.error("There was a problem with purchase!");
//         }
//     }

//     return (
//         <div className="CouponForPurchase">
// 			<div className="couponCard column">
//                 <img src={couponImage} alt="Coupon Image" />
//                 <h1>{props.coupon.title}</h1>
//                 <p className="couponPrice">{props.coupon.price}</p>
//                 <p>{props.coupon.description} </p>
//                 <button onClick={() => purchaseCoupon(props.coupon)}>Add to cart</button>
//                 </div>
//         </div>
//     );
// }

// export default CouponForPurchase;