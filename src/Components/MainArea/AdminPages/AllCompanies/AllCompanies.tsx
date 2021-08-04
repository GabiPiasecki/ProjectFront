import "./AllCompanies.css";
import {useEffect, useState} from 'react';
import Company from '../../../../Models/Company'
import CompanyTemplate from "../../../../ModelTemplates/CompanyTemplate/CompanyTemplate";
import store from "../../../../Redux/Store";
import { companyDownloadAction } from "../../../../Redux/CompanyState";
import jwtAxios from "../../../../Authorization/jwtAxios";

function AllCompanies(): JSX.Element {
    const [companies, setCompanies] = useState<Company[]>([]);
    const fetchCompanies = async () => {
        try {
            const { data : companies } : { data : Company[] } = await jwtAxios.get(`/administrator/allCompanies`);
            store.dispatch(companyDownloadAction(companies));
            setCompanies(companies);
        } catch(e) {
            console.log(e)
        }
    }

    useEffect(() => {
        fetchCompanies();
    }, [])

    return (
        <div className="AllCompanies row">
			{companies.map(item => <CompanyTemplate key={item.id} company={item}/>)}
        </div>
    );
}

export default AllCompanies;
