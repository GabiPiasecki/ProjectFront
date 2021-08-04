import "./Login.css";
import { useHistory } from "react-router-dom";
import { useForm } from "react-hook-form";
import axios from "axios";
import CredentialsModel from "../../../Models/CredentialsModel";
import UserModel from "../../../Models/UserModel";
import notify from "../../../Services/Notify";
import { loginAction } from "../../../Redux/AuthState";
import store from "../../../Redux/Store";
import { Button } from 'reactstrap';

function Login(): JSX.Element {
    const history = useHistory();
    const {register,handleSubmit, errors} = useForm<CredentialsModel>();
    async function send(credential: CredentialsModel){
        try{
            const { data : response } : { data : string } = await axios.post(`http://localhost:8080/${credential.userType.toLowerCase()}/login`, credential);
            const userModel = new UserModel();
            userModel.token=response;
            userModel.email=credential.email;     
            userModel.userType=credential.userType;       
            store.dispatch(loginAction(userModel));
            notify.success("you have been successfully signed in!!!")
            history.push(`/${credential.userType.toLowerCase()}`);
        } catch{
            notify.error("There was a problem!");
        }
    }

    const ref={register}
    return (
        <div className="Login Form">
            <h2> Login </h2><br/>
            <form onSubmit={handleSubmit(send)}>
                <label>Email </label> <br/>
                <input type="email" name="email" placeholder="example@example.com" ref={register({
                    required: {value:true , message:"Missing email!"}
                })}/>
                <span><br/>{errors.email?.message}</span>
                <br/>
                <label>Password </label> <br/>
                <input type="password" name="password" placeholder="********" ref={register({
                     required: {value:true , message:"Missing password!"}
                    })}/>
                <span><br/>{errors.password?.message}</span>
                <br/>
                <label>Choose your client type: </label><br/>
                <select name="userType" ref={register}>
                    <option value="CUSTOMER">Customer</option>
                    <option value="COMPANY">Company</option>
                    <option value="ADMINISTRATOR">Administrator</option>
                </select>
                <br/><br/>
                <Button color="info" className="button">Login</Button>{' '}
            </form>
        </div>
    );
}

export default Login;