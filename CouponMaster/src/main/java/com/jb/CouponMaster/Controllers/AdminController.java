package com.jb.CouponMaster.Controllers;

import com.jb.CouponMaster.Configuration.LoginManager.ClientType;
import com.jb.CouponMaster.Authorization.JWTUtil;
import com.jb.CouponMaster.Authorization.UserDetails;
import com.jb.CouponMaster.Beans.Company;
import com.jb.CouponMaster.Beans.Customer;
import com.jb.CouponMaster.Exceptions.CouponMasterException;
import com.jb.CouponMaster.Services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("administrator")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final JWTUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) {
        try {
            if (adminService.login(userDetails.getEmail(), userDetails.getPassword())) {
                String myToken = jwtUtil.generateToken(new UserDetails(userDetails.getEmail(), ClientType.ADMINISTRATOR));
                return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (CouponMasterException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("company/add")
    public ResponseEntity<?> addCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            String newToken = jwtUtil.generateToken(new UserDetails(
                    jwtUtil.extractUserEmail(token), ClientType.ADMINISTRATOR));
            System.out.println(newToken);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(adminService.addCompany(company));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("company/update") //http://localhost:8080/company/update
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.ADMINISTRATOR)))
                    .body(adminService.updateCompany(company));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("company/delete/{companyID}") //http://localhost:8080/company/delete/1
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyID) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.ADMINISTRATOR)))
                    .body(adminService.deleteCompany(companyID));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("company/{companyID}") //http://localhost:8080/company/1
    public ResponseEntity<?> getOneCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyID) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.ADMINISTRATOR)))
                    .body(adminService.getOneCompany(companyID));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("allCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            String newToken = jwtUtil.generateToken(new UserDetails(
                    jwtUtil.extractUserEmail(token), ClientType.ADMINISTRATOR));
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(adminService.getAllCompanies());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

/*
CUSTOMER USER WILL BE DOING THE REGISTRATION HIMSELF
    @PostMapping("customer/add") //http://localhost:8080/customer/add
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws CouponMasterException {
        return new ResponseEntity<>(adminService.addCustomer(customer) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
 */

    @PutMapping("customer/update") //http://localhost:8080/customer/update
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.ADMINISTRATOR)))
                    .body(adminService.updateCustomer(customer));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("customer/delete/{customerID}") //http://localhost:8080/customer/delete/1
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerID) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.ADMINISTRATOR)))
                    .body(adminService.deleteCustomer(customerID));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("customer/{customerID}") //http://localhost:8080/customer/1
    public ResponseEntity<?> getOneCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerID) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.ADMINISTRATOR)))
                    .body(adminService.getOneCustomer(customerID));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("allCustomers") //http://localhost:8080/customer/all
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.ADMINISTRATOR)))
                    .body(adminService.getAllCustomers());
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}