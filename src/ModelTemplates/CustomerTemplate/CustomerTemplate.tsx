import "./CustomerTemplate.css";
import couponImage from "../../Assets/cf-256x256.png";
import Customer from "../../Models/Customer";
import notify from "../../Services/Notify";
import store from "../../Redux/Store";
import { customerDeleteAction } from "../../Redux/CustomerState";
import { useHistory } from "react-router-dom";
import jwtAxios from "../../Authorization/jwtAxios";
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button } from 'reactstrap';

interface CustomerTemplateProps {
	customer : Customer;
}

function CustomerTemplate(props:CustomerTemplateProps): JSX.Element {
    const history = useHistory();

    const deleteCustomer = async () => {
        try {
            await jwtAxios.delete(`/administrator/customer/delete/${props.customer.id}`);
            notify.success("The customer was deleted successfully!");
            store.dispatch(customerDeleteAction(props.customer))
            history.push("/administrator/allCustomers");
        } catch {
            notify.error("There was a problem with deleting the customer!");
        }
    }

    const editCustomer = () => {
        history.push("/administrator/customer/update/"+props.customer.id);
    }

    return (
        <div className="CustomerTemplate">
            <Card style={{flex: 1}}>
                <CardBody>
                    <CardTitle tag="h5">{props.customer.firstName} {props.customer.lastName}</CardTitle>
                    <CardSubtitle tag="h6" className="mb-2 text-muted">Customer ID: {props.customer.id}</CardSubtitle>
                    <CardText>{props.customer.email}</CardText>
                    <Button size="sm" color="info" className="button" onClick={editCustomer}  >Edit Details</Button><br/>
                    <Button size="sm" color="info" className="button" onClick={deleteCustomer}>   Delete   </Button>
                </CardBody>
            </Card>
        </div>
    );
}

export default CustomerTemplate;
