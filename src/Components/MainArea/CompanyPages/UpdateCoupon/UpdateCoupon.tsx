import Coupon from "../../../../Models/Coupon";
import "./UpdateCoupon.css";
import { useEffect, useState} from 'react';
import notify from "../../../../Services/Notify";
import store from "../../../../Redux/Store";
import { couponUpdateAction } from "../../../../Redux/CouponState";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import jwtAxios from "../../../../Authorization/jwtAxios";

interface UpdateCouponProps {
  id:string;
}

function UpdateCoupon(props: UpdateCouponProps): JSX.Element {
    const couponId = parseInt(props.id);
    const companyEmail = store.getState().authState.user.email;
    const loggedCompanyArray = store.getState().companyState.companies.filter(function(item){return item.email === companyEmail});
    const loggedCompany = loggedCompanyArray[0];
    const companyCoupons = loggedCompany.coupons;
    const updateCouponArray = companyCoupons.filter(function (item){return item.id === couponId});
    const updateCoupon = updateCouponArray[0];

    const [updatedTitle, setUpdatedTitle] = useState<string>(updateCoupon.title);
    const [updatedDescription, setUpdatedDescription] = useState<string>(updateCoupon.description);
    const [updatedCategory, setUpdatedCategory] = useState<string>(updateCoupon.category);
    const [updatedStartDate, setUpdatedStartDate] = useState<string>(updateCoupon.startDate);
    const [updatedEndDate, setUpdatedEndDate] = useState<string>(updateCoupon.endDate);
    const [updatedAmount, setUpdatedAmount] = useState<number>(updateCoupon.amount);
    const [updatedPrice, setUpdatedPrice] = useState<number>(updateCoupon.price);
    const [updatedImage, setUpdatedImage] = useState<string>(updateCoupon.image);

    const {register, handleSubmit, errors} = useForm<Coupon>();
    const history = useHistory();
    async function send(coupon:Coupon){
       coupon.id = couponId;
       coupon.companyID=updateCoupon.companyID;
       coupon.title=updatedTitle;
       coupon.description = updatedDescription;
       coupon.category=updatedCategory;
       coupon.startDate=updatedStartDate;
       coupon.endDate=updatedEndDate;
       coupon.amount = updatedAmount;
       coupon.price=updatedPrice;
       coupon.image=updatedImage;
       
       try{  
           await jwtAxios.put<Coupon>("http://localhost:8080/company/update", coupon);
           store.dispatch(couponUpdateAction(coupon));
           notify.success("Coupon update successfully!");
           history.push("/company/all");
       } catch {
           notify.error("There was a problem with updating this coupon");
       }
   }

   useEffect(()=>{ 
   }, [])
   
   const ref={register}
   return (
       <div className="UpdateCoupon">
     <h2>Update Coupon</h2>
           <form onSubmit={handleSubmit(send)}>

           <label>Choose a coupon category: </label>
                <select name="category" defaultValue ={updateCoupon.category} onChange ={item => setUpdatedCategory(item.target.value)}   ref={register}>
                    <option value="" disabled>--Please choose a category--</option>
                    <option value="VACATION">Vacation</option>
                    <option value="FOOD" >Food</option>
                    <option value="BEAUTY">Beauty</option>
                    <option value="HOME">Home</option>
                    <option value="ELECTRICITY">Electricity</option>
                    <option value="FASHION">Fashion</option>
                    <option value="SPORT">Sport</option>
                    <option value="PETS">Pets</option>
                </select>
                <br/><br/>


               <input type="text" name="id" value={parseInt(props.id)} ref={register({
               })} disabled/>
               <br/><br/>
               <input type="text" name="title" defaultValue={updateCoupon.title} onChange = {item => setUpdatedTitle(item.target.value)} ref={register({
                required: {value:true , message:"Missing company title!"},
                minLength: {value:2 , message:"Minimum title length is two characters!"}
               })}/>
               <br/><br/>
               <input type="text" name="description" defaultValue={updateCoupon.description} onChange = {item => setUpdatedDescription(item.target.value)} ref={register({              
                  required: {value:true , message:"Missing coupon description!"}
               })}/>
               <span><br/>{errors.description?.message}</span>
               <br/><br/>

               <input type="text" name="startDate" defaultValue={updateCoupon.startDate} onChange = {item => setUpdatedStartDate(item.target.value)} ref={register({              
                  required: {value:true , message:"Missing coupon start date!"}
               })}/>
               <span><br/>{errors.startDate?.message}</span>
               <br/><br/>
               <input type="text" name="endDate" defaultValue={updateCoupon.endDate} onChange = {item => setUpdatedEndDate(item.target.value)} ref={register({              
                  required: {value:true , message:"Missing coupon end date"}
               })}/>
               <span><br/>{errors.endDate?.message}</span>
               <br/><br/>
               <input type="number" name="amount" defaultValue={updateCoupon.amount} onChange = {item =>  setUpdatedAmount(parseInt(item.target.value))} ref={register({              
                  required: {value:true , message:"Missing coupon amount"}
               })}/>
               <span><br/>{errors.amount?.message}</span>
               <br/><br/>
               <input type="number" name="price" defaultValue={updateCoupon.price} onChange = {item => setUpdatedPrice(parseInt(item.target.value))} ref={register({            
                    required: {value:true , message:"Missing coupon price!"}
               })}/>
               <span><br/>{errors.price?.message}</span>
               <br/><br/>
               <input type="text" name="image" defaultValue={updateCoupon.image} onChange = {item => setUpdatedImage(item.target.value)} ref={register({            
                    required: {value:true , message:"Missing coupon image!"}
               })}/>
               <button>Update</button>
           </form>
       </div>
   );
}

export default UpdateCoupon;

