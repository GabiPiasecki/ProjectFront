import "./UpdateCustomer.css";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import jwtAxios from "../../../../Authorization/jwtAxios";
import Customer from "../../../../Models/Customer";
import { customerUpdateAction } from "../../../../Redux/CustomerState";
import store from "../../../../Redux/Store";
import notify from "../../../../Services/Notify";

interface UpdateCustomerProps {
    id:string;
}
  
function UpdateCustomer(props: UpdateCustomerProps): JSX.Element {
    const customerId = parseInt(props.id);
    const reduxCustomer = store.getState().customerState.customers.filter(function (item){return item.id==customerId});
    const updateCustomer = reduxCustomer[0];
  
    const [updatedEmail, setUpdatedEmail] = useState<string>(updateCustomer.email);
    const [updatedPassword, setUpdatedPassword] = useState<string>(updateCustomer.password);
    const [updatedFirstName, setUpdatedFirstName] = useState<string>(updateCustomer.firstName);
    const [updatedLastName, setUpdatedLastName] = useState<string>(updateCustomer.lastName);   
  
    const {register, handleSubmit, errors} = useForm<Customer>();
    const history = useHistory();
    async function send(customer:Customer){
         customer.id = customerId;
         customer.firstName=updatedFirstName;
         customer.lastName=updatedLastName;
         customer.email=updatedEmail;
         customer.password=updatedPassword;
         customer.coupons=updateCustomer.coupons;
         try{  
             console.log(customer);
             await jwtAxios.put<Customer>("http://localhost:8080/administrator/customer/update",customer);
             store.dispatch(customerUpdateAction(customer));
             notify.success("Customer update successfully!");
             history.push("/administrator/allCustomers");
         } catch {
             notify.error("There was a problem with updating this customer");
         }
     }
  
     useEffect(()=>{ 
     }, [])
     
     const ref={register}
     return (
         <div className="UpdateCustomer Form">
       <h2>Update Customer</h2>
             <form onSubmit={handleSubmit(send)}>
                 <input type="text" name="id" value={parseInt(props.id)} ref={register({
                 })} disabled/>
                 <br/><br/>
                 <input type="text" name="firstName" defaultValue={updateCustomer.firstName} onChange={e => setUpdatedFirstName(e.target.value)} ref={register({
                  required: {value:true , message:"Missing customer first name!"}
                 })} /><br/><br/>
                 <input type="text" name="lastName" defaultValue={updateCustomer.lastName} onChange={e => setUpdatedLastName(e.target.value)} ref={register({
                  required: {value:true , message:"Missing customer last name!"}
                 })} />
                 <br/><br/>
                 <input type="email" name="email" defaultValue={updateCustomer.email} onChange={e =>setUpdatedEmail(e.target.value)} ref={register({
                 required: {value:true , message:"Missing customer email!"}
                 })}/>
                 <span><br/>{errors.email?.message}</span>
                 <br/><br/>
                 <input type="password" name="password" defaultValue={updateCustomer.password} onChange={e =>setUpdatedPassword(e.target.value)} ref={register({
                 required: {value:true , message:"Missing customer password!"}
                 })}/>
                 <span><br/>{errors.password?.message}</span>
                 <br/><br/>
                 <button>Update</button>
             </form>
         </div>
     );
  }
  
  export default UpdateCustomer;