package com.jb.CouponMaster.Controllers;

import com.jb.CouponMaster.Authorization.UserDetails;
import com.jb.CouponMaster.Beans.Category;
import com.jb.CouponMaster.Beans.Customer;
import com.jb.CouponMaster.Configuration.LoginManager;
import com.jb.CouponMaster.Exceptions.CouponMasterException;
import com.jb.CouponMaster.Repositories.CouponRepository;
import com.jb.CouponMaster.Services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GuestController {
    private final CouponRepository couponRepository;
    private final AdminService adminService;
    private final LoginManager loginManager;

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getAllCategoryCoupons(@PathVariable Category category) {
        return ResponseEntity.ok(couponRepository.findByCategory(category));
    }

    @GetMapping("companies/{companyID}")
    public ResponseEntity<?> getAllCompanyCoupons(@PathVariable int companyID) {
        return ResponseEntity.ok(couponRepository.findByCompanyID(companyID));
    }

    @GetMapping("coupon/{couponID}")
    public ResponseEntity<?> getOneCoupon(@PathVariable int couponID) {
        return ResponseEntity.ok(couponRepository.findById(couponID));
    }

    @CrossOrigin
    @PostMapping("register") //http://localhost:8080/customer/add
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws CouponMasterException {
        return new ResponseEntity<>(adminService.addCustomer(customer) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
}
