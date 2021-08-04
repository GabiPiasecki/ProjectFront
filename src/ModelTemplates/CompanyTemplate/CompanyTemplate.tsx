import "./CompanyTemplate.css";
import Company from "../../Models/Company";
import couponImage from "../../Assets/cf-256x256.png";
import notify from "../../Services/Notify";
import store from "../../Redux/Store";
import { companyDeleteAction } from "../../Redux/CompanyState";
import { useHistory } from "react-router-dom";
import jwtAxios from "../../Authorization/jwtAxios";
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button } from 'reactstrap';

interface CompanyTemplateProps {
	company : Company;
}

function CompanyTemplate(props: CompanyTemplateProps): JSX.Element {
    const history = useHistory();
    const deleteCompany = async () => {
        try {
            await jwtAxios.delete(`/administrator/company/delete/${props.company.id}`);
            notify.success("The company was deleted successfully!");
            store.dispatch(companyDeleteAction(props.company))
            history.push("/administrator/allCompanies");
        } catch {
            notify.error("There was a problem with deleting the company!");
        }
    }

    const editCompany = () => {
        history.push("/administrator/company/update/"+props.company.id);
    }

    return (
        <div className="CompanyTemplate">
            <Card style={{flex: 1}}>
                <CardBody>
                    <CardTitle tag="h5">{props.company.name}</CardTitle>
                    <CardSubtitle tag="h6" className="mb-2 text-muted">Company ID: {props.company.id}</CardSubtitle>
                    <CardText>{props.company.email}</CardText>
                    <Button size="sm" color="info"  onClick={editCompany} className="button">Edit Details</Button><br/>
                    <Button size="sm" color="info" onClick={deleteCompany}className="button">Delete</Button>
                </CardBody>  
            </Card>
        </div>
    );
}

export default CompanyTemplate;
