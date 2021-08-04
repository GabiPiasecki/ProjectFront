import Customer from "../../../../Models/Customer";
import "./SelfUpdateCustomer.css";
import { useEffect, useState} from 'react';
import notify from "../../../../Services/Notify";
import store from "../../../../Redux/Store";
import { customerUpdateAction } from "../../../../Redux/CustomerState";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import jwtAxios from "../../../../Authorization/jwtAxios";
import { Button } from "reactstrap";

function SelfUpdateCustomer(props: {}): JSX.Element {
    const customerEmail=store.getState().authState.user.email;
    const updateCustomerArray = store.getState().customerState.customers.filter(function (item){return item.email === customerEmail});
    const updateCustomer = updateCustomerArray[0];

    const [updatedEmail, setUpdatedEmail] = useState<string>(updateCustomer.email);
    const [updatedPassword, setUpdatedPassword] = useState<string>(updateCustomer.password);
    const [updatedFirstName, setUpdatedFirstName] = useState<string>(updateCustomer.firstName);
    const [updatedLastName, setUpdatedLastName] = useState<string>(updateCustomer.lastName);   
    
    const {register, handleSubmit, errors} = useForm<Customer>();
    const history = useHistory();
    async function send(customer:Customer){
       customer.id = updateCustomer.id;
       customer.firstName=updatedFirstName;
       customer.lastName=updatedLastName;
       customer.email=updatedEmail;
       customer.password=updatedPassword;
       customer.coupons=updateCustomer.coupons;
       try{  
           await jwtAxios.put<Customer>("http://localhost:8080/administrator/customer/update",customer);
           store.dispatch(customerUpdateAction(customer));
           notify.success("Customer update successfully!");
           history.push("/customer/details");
       } catch {
           notify.error("There was a problem with updating the details!");
       }
   }

   useEffect(()=>{ 
   }, [])
   
   const ref={register}
   return (
       <div className="SelfUpdateCustomer">
     <h2>Update Customer</h2>
           <form onSubmit={handleSubmit(send)}><br/>
                <label>Customer id</label><br/>
               <input type="text" name="id" value={updateCustomer.id} ref={register({
               })} disabled/>
               <br/><br/>
               <label>First Name</label><br/>
               <input type="text" name="firstName" defaultValue={updateCustomer.firstName} onChange={e => setUpdatedFirstName(e.target.value)} ref={register({
                required: {value:true , message:"Missing customer first name!"}
               })} /><br/><br/>
               <label>Last Name</label><br/>
               <input type="text" name="lastName" defaultValue={updateCustomer.lastName} onChange={e => setUpdatedLastName(e.target.value)} ref={register({
                required: {value:true , message:"Missing customer last name!"}
               })} />
               <br/><br/>
               <label>Email</label><br/>
               <input type="email" name="Email@email.com" defaultValue={updateCustomer.email} onChange={e =>setUpdatedEmail(e.target.value)} ref={register({
               required: {value:true , message:"Missing customer email!"}
               })}/>
               <span><br/>{errors.email?.message}</span>
               <br/>
               <label>password</label><br/>
               <input type="password" name="*******" defaultValue={updateCustomer.password} onChange={e =>setUpdatedPassword(e.target.value)} ref={register({
               required: {value:true , message:"Missing customer password!"}
               })}/>
               <span><br/>{errors.password?.message}</span>
               <br/>
               <Button size="md" color="info" className="button">Update</Button>
           </form>
       </div>
   );
}

export default SelfUpdateCustomer;
