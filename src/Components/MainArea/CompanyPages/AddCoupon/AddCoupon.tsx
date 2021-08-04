import "./AddCoupon.css";
import { useForm } from "react-hook-form";
import notify from "../../../../Services/Notify";
import { useHistory } from "react-router-dom";
import axios from "axios";
import Coupon from "../../../../Models/Coupon";
import store from "../../../../Redux/Store";
import { couponAddAction } from "../../../../Redux/CouponState";
import jwtAxios from "../../../../Authorization/jwtAxios";
import { Button } from "reactstrap";

function AddCoupon(): JSX.Element {
    const {register, handleSubmit, errors} = useForm<Coupon>();
    const history = useHistory();

    async function send(coupon:Coupon){
        try{
          //  const imgResponse = await axios.post<string>("http://localhost:8080/file/upload",coupon.image);
         //coupon.image = imgResponse.data;
            const response = await jwtAxios.post<Coupon>("http://localhost:8080/company/add",coupon);
            notify.success("New coupon was added successfully!");
            store.dispatch(couponAddAction(coupon));
            history.push("/company/all");
        } catch {
            notify.error("There was a problem with adding this coupon");
        }
    }
    
    const ref={register}
    return (
        <div className="AddCoupon Form">
			<h2>Add New Coupon</h2>
            <form onSubmit={handleSubmit(send)}>
                <label>Choose a coupon category: </label><br/>
                <select name="category" ref={register}>
                    <option value="" disabled>--Please choose a category--</option>
                    <option value="FOOD" >Food</option>
                    <option value="VACATION">Vacation</option>
                    <option value="BEAUTY">Beauty</option>
                    <option value="HOME">Home</option>
                    <option value="ELECTRICITY">Electricity</option>
                    <option value="FASHION">Fashion</option>
                    <option value="SPORT">Sport</option>
                    <option value="PETS">Pets</option>
                </select>
                <br/><br/>
                <label>Coupon Title</label><br/>
                <input type="text" name="title" placeholder="Coupon Title" ref={register({
                    required: {value:true , message:"Missing company title!"},
                    minLength: {value:2 , message:"Minimum title length is two characters!"}
                })}/>
                <span><br/>{errors.title?.message}</span>
                <br/>
                <label>Coupon Description</label><br/>
                <input type="text" name="description" placeholder="Coupon Description" ref={register({
                    required: {value:true , message:"Missing coupon description!"}
                })}/>
                <span><br/>{errors.description?.message}</span>
                <br/>
                <label>Coupon Start Date </label><br/>
                <input type="text" name="startDate" placeholder="(YYYY-MM-DD)" ref={register({
                    required: {value:true , message:"Missing coupon start date!"}
                })}/>
                <span><br/>{errors.startDate?.message}</span>
                <br/>
                <label>Coupon End Date</label><br/>
                <input type="text" name="endDate" placeholder="(YYYY-MM-DD)" ref={register({
                    required: {value:true , message:"Missing coupon end date!"}
                })}/>
                <span><br/>{errors.endDate?.message}</span>
                <br/>
                <label>Coupon Price</label><br/>
                <input type="number" name="price" placeholder="Coupon Price" ref={register({
                    required: {value:true , message:"Missing coupon price!"}
                })}/>
                <span><br/>{errors.price?.message}</span>
                <br/>
                <label>Amount</label><br/>
                <input type="number" name="amount" placeholder="Coupon Amount" ref={register({
                    required: {value:true , message:"Missing coupon amount!"}
                })}/>
                <span><br/>{errors.startDate?.message}</span>
                <br/>
                <label>Coupon Image</label><br/>
                <input type="text" name="image" placeholder="Coupon Image" ref={register({
                     required: {value:true , message:"Missing company Image!"}
                    })}/>
                <span><br/>{errors.image?.message}</span>
                <br/>
                <Button size="md" color="info" >Add Coupon</Button>{' '}
            </form>
        </div>
    );
}

export default AddCoupon;
