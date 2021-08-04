import Company from "../../../../Models/Company";
import "./CompanyDetails.css";
import { useEffect, useState} from 'react';
import CompanyTemplate from "../../../../ModelTemplates/CompanyTemplate/CompanyTemplate";
import jwtAxios from "../../../../Authorization/jwtAxios";

function CompanyDetails(): JSX.Element {
    const [company, setCompany] = useState<Company | undefined>();
    const fetchCompany = async () => {
        try {
            const { data : company } : { data : Company } = await jwtAxios.get(`/company/details`);
            setCompany(company);
        } catch(e) {
            console.log(e)
        }
    }

    useEffect(() => {
        fetchCompany();
    },)

    return !company? null : (
        <div className="CompanyDetails ">
            {/* <CompanyTemplate company={company}/> */}
            <h2 className='customerTitle'>{company.name}'s Details:</h2>
            <h3 className='customerDetails'  >  
            Name: {company.name} <br/>
                Email: {company.email} <br/>
            </h3>
        </div>
    );
}

export default CompanyDetails;
