package com.jb.CouponMaster.Controllers;

import com.jb.CouponMaster.Authorization.JWTUtil;
import com.jb.CouponMaster.Authorization.UserDetails;
import com.jb.CouponMaster.Beans.Category;
import com.jb.CouponMaster.Beans.Coupon;
import com.jb.CouponMaster.Configuration.LoginManager.ClientType;
import com.jb.CouponMaster.Exceptions.CouponMasterException;
import com.jb.CouponMaster.Services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final JWTUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) {
        try {
            if (companyService.login(userDetails.getEmail(), userDetails.getPassword())) {
                String myToken = jwtUtil.generateToken(new UserDetails(userDetails.getEmail(), ClientType.COMPANY));
                return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (CouponMasterException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//
//    private boolean checkToken(String token) {
//        Company company = companyRepository.findByEmail(jwtUtil.extractUserEmail(token));
//        UserDetails userDetails = new UserDetails(company.getEmail(), ClientType.COMPANY);
//        return jwtUtil.validateToken(token, userDetails);
//    }

    @PostMapping("add")
    public ResponseEntity<?> addCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.COMPANY)))
                    .body(companyService.addCoupon(coupon));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.COMPANY)))
                    .body(companyService.updateCoupon(coupon));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.COMPANY)))
                    .body(companyService.deleteCoupon(id));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.COMPANY)))
                    .body(companyService.getOneCoupon(id));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization") String token) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.COMPANY)))
                    .body(companyService.getAllCoupons());
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("category/{category}")
    public ResponseEntity<?> getCategoryCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.COMPANY)))
                    .body(companyService.getCategoryCoupons(category));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("maxprice/{maxPrice}")
    public ResponseEntity<?> getMaxPriceCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable double maxPrice) throws CouponMasterException {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.COMPANY)))
                    .body(companyService.getMaxPriceCoupons(maxPrice));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("details")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Authorization") String token) {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(new UserDetails(jwtUtil.extractUserEmail(token), ClientType.COMPANY)))
                    .body(companyService.getCompanyDetails());
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}