package com.jb.CouponMaster.Configuration;

import com.jb.CouponMaster.Authorization.JWTUtil;
import com.jb.CouponMaster.Exceptions.CouponMasterException;
import com.jb.CouponMaster.Services.AdminService;
import com.jb.CouponMaster.Services.ClientService;
import com.jb.CouponMaster.Services.CompanyService;
import com.jb.CouponMaster.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoginManager {
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    public enum ClientType {
        ADMINISTRATOR, COMPANY, CUSTOMER
    }
    /**
     * A login method , checks if the details are correct. If not, returns an exception message
     * @param email checks if the email is correct
     * @param password checks if the password is correct
     * @param clientType checks what client type is trying to log in
     * @return instance of clientService
     * @throws CouponMasterException custom message according to the reason of failure
     */
    public ClientService login(ClientType clientType, String email, String password) throws CouponMasterException {
        ClientService result = null;
        switch (clientType) {
            case ADMINISTRATOR:
                result = (adminService.login(email, password))? adminService:null;
                break;
            case COMPANY:
                result = (companyService.login(email, password))? companyService:null;
                break;
            case CUSTOMER:
                result = (customerService.login(email, password))? customerService:null;
                break;
            default:
                throw new CouponMasterException("Login failed!");
        }
        if (result == null) {
            throw new CouponMasterException("There is no such user!");
        }
        return result;
    }
}
