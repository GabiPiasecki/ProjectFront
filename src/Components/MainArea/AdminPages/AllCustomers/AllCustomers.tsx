import "./AllCustomers.css";
import {useEffect, useState} from 'react';
import Customer from '../../../../Models/Customer'
import CustomerTemplate from "../../../../ModelTemplates/CustomerTemplate/CustomerTemplate";
import store from "../../../../Redux/Store";
import { customerDownloadAction } from "../../../../Redux/CustomerState";
import jwtAxios from "../../../../Authorization/jwtAxios";

function AllCustomers(): JSX.Element {
    const [customers, setCustomers] = useState<Customer[]>([]);
    const fetchCustomers = async () => {
        try {
            const { data : customers } : { data : Customer[] } = await jwtAxios.get(`/administrator/allCustomers`);
            store.dispatch(customerDownloadAction(customers));
            setCustomers(customers);
        } catch(e) {
            console.log(e)
        }
    }

    useEffect(() => {
        fetchCustomers();
    }, [])
    
    return (
        <div className="AllCustomers row">
			{customers.map(item => <CustomerTemplate key={item.id} customer={item}/>)}
        </div>
    );
}

export default AllCustomers;