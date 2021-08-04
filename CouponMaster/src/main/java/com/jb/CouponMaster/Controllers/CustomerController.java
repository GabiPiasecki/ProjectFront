package com.jb.CouponMaster.Controllers;

import com.jb.CouponMaster.Authorization.JWTUtil;
import com.jb.CouponMaster.Authorization.UserDetails;
import com.jb.CouponMaster.Beans.Category;
import com.jb.CouponMaster.Beans.Coupon;
import com.jb.CouponMaster.Configuration.LoginManager;
import com.jb.CouponMaster.Exceptions.CouponMasterException;
import com.jb.CouponMaster.Repositories.CustomerRepository;
import com.jb.CouponMaster.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final JWTUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) {
        try {
            if (customerService.login(userDetails.getEmail(), userDetails.getPassword())) {
                String myToken = jwtUtil.generateToken(new UserDetails(userDetails.getEmail(), LoginManager.ClientType.CUSTOMER));
                return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (CouponMasterException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//
//    private boolean checkToken(String token) {
//        Customer customer = customerRepository.findByEmail(jwtUtil.extractUserEmail(token));
//        UserDetails userDetails = new UserDetails(customer.getEmail(), LoginManager.ClientType.CUSTOMER);
//        return jwtUtil.validateToken(token, userDetails);
//    }

    @PostMapping("purchase")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), LoginManager.ClientType.CUSTOMER)))
                    .body(customerService.purchaseCoupon(coupon));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization") String token) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), LoginManager.ClientType.CUSTOMER)))
                    .body(customerService.getAllCoupons());
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("category/{category}")
    public ResponseEntity<?> getCategoryCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), LoginManager.ClientType.CUSTOMER)))
                    .body(customerService.getCategoryCoupons(category));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("maxprice/{maxPrice}")
    public ResponseEntity<?> getMaxPriceCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable double maxPrice) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), LoginManager.ClientType.CUSTOMER)))
                    .body(customerService.getMaxPriceCoupons(maxPrice));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("details")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Authorization") String token) {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), LoginManager.ClientType.CUSTOMER)))
                    .body(customerService.getCustomerDetails());
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}