//package com.jb.CouponMaster.CLR;
//
//import com.jb.CouponMaster.Beans.Category;
//import com.jb.CouponMaster.Beans.Coupon;
//import com.jb.CouponMaster.Configuration.LoginManager;
//import com.jb.CouponMaster.Exceptions.CouponMasterException;
//import com.jb.CouponMaster.Services.CustomerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Component
//@Order(3)
//@RequiredArgsConstructor
//public class CustomerTest implements CommandLineRunner {
//    private final LoginManager loginManager;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("----------CUSTOMER TEST----------");
//
//        CustomerService customerUser = (CustomerService) loginManager.Login(LoginManager.ClientType.CUSTOMER,"cust1@gmail.com", "customer1");
//
//        Coupon coupon1 = Coupon.builder()
//                .category(Category.PETS)
//                .title("Coupon 1")
//                .description("Description 1")
//                .startDate(LocalDate.of(2021, 2, 1))
//                .endDate(LocalDate.of(2021, 8, 1))
//                .amount(200)
//                .price(50)
//                .image("Image 1")
//                .build();
//
//        Coupon coupon2 = Coupon.builder()
//                .category(Category.HOME)
//                .title("Coupon 2")
//                .description("Description 2")
//                .startDate(LocalDate.of(2021, 3, 1))
//                .endDate(LocalDate.of(2021, 9, 1))
//                .amount(160)
//                .price(29.70)
//                .image("Image 2")
//                .build();
//
//        try {
//            customerUser.purchaseCoupon(coupon1);
//            customerUser.purchaseCoupon(coupon2);
//            customerUser.purchaseCoupon(coupon2);
//        } catch (
//                CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("All customer's coupons: " + customerUser.getAllCoupons());
//
//        try {
//            System.out.println("All customer's coupons from Home category: " + customerUser.getCategoryCoupons(Category.HOME));
//            System.out.println("All customer's coupons from Sport category: " + customerUser.getCategoryCoupons(Category.SPORT));
//        } catch (
//                CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            System.out.println("All customer's coupons that cost under 40: " + customerUser.getMaxPriceCoupons(40));
//            System.out.println("All customer's coupons that cost under 10: " + customerUser.getMaxPriceCoupons(10));
//        } catch (
//                CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Customer's details: " + customerUser.getCustomerDetails());
//
//
//    }
//}