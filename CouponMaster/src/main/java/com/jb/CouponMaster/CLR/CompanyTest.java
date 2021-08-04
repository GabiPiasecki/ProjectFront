package com.jb.CouponMaster.CLR;

import com.jb.CouponMaster.Beans.Category;
import com.jb.CouponMaster.Beans.Coupon;
import com.jb.CouponMaster.Configuration.LoginManager;
import com.jb.CouponMaster.Exceptions.CouponMasterException;
import com.jb.CouponMaster.Services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Component
//@Order(2)
//@RequiredArgsConstructor
//public class CompanyTest implements CommandLineRunner {
//    private final LoginManager loginManager;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("----------COMPANY TEST----------");
//
//        CompanyService companyUser = (CompanyService) loginManager.Login(LoginManager.ClientType.COMPANY, "comp1@gmail.com", "company1");
//
//        Coupon coupon1 = Coupon.builder()
//                .category(Category.FOOD)
//                .title("Coupon 1")
//                .description("Description 1")
//                .startDate(LocalDate.of(2021, 1, 1))
//                .endDate(LocalDate.of(2021, 7, 1))
//                .amount(100)
//                .price(99.90)
//                .image("Image 1")
//                .build();
//
//        Coupon coupon2 = Coupon.builder()
//                .category(Category.PETS)
//                .title("Coupon 2")
//                .description("Description 2")
//                .startDate(LocalDate.of(2021, 2, 1))
//                .endDate(LocalDate.of(2021, 8, 1))
//                .amount(200)
//                .price(50)
//                .image("Image 2")
//                .build();
//
//        Coupon coupon3 = Coupon.builder()
//                .category(Category.HOME)
//                .title("Coupon 3")
//                .description("Description 3")
//                .startDate(LocalDate.of(2021, 3, 1))
//                .endDate(LocalDate.of(2021, 9, 1))
//                .amount(160)
//                .price(29.70)
//                .image("Image 3")
//                .build();
//
//        Coupon coupon4 = Coupon.builder()
//                .category(Category.VACATION)
//                .title("Coupon 4")
//                .description("Description 4")
//                .startDate(LocalDate.of(2021, 4, 1))
//                .endDate(LocalDate.of(2021, 10, 1))
//                .amount(280)
//                .price(340.20)
//                .image("Image 4")
//                .build();
//
//        try {
//            companyUser.addCoupon(coupon1);
//            companyUser.addCoupon(coupon2);
//            companyUser.addCoupon(coupon3);
//            companyUser.addCoupon(coupon4);
//            companyUser.addCoupon(coupon4);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        //Getting the coupons with their ID from the DB (for updating by ID later)
//        try {
//            coupon1 = companyUser.getOneCoupon(1);
//            coupon2 = companyUser.getOneCoupon(2);
//            coupon3 = companyUser.getOneCoupon(3);
//            coupon4 = companyUser.getOneCoupon(4);
//            coupon4 = companyUser.getOneCoupon(5);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        coupon1.setPrice(55);
//
//        try {
//            companyUser.updateCoupon(coupon1);
//            Coupon coupon5 = Coupon.builder()
//                    .category(Category.FASHION)
//                    .title("Coupon")
//                    .description("Description")
//                    .startDate(LocalDate.of(2021, 4, 1))
//                    .endDate(LocalDate.of(2021, 10, 1))
//                    .amount(280)
//                    .price(340.20)
//                    .image("Image")
//                    .build();
//            companyUser.updateCoupon(coupon5);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//          companyUser.deleteCoupon(4);
//            companyUser.deleteCoupon(5);
//        } catch (CouponMasterException e) {
//           System.out.println(e.getMessage());
//        }
//
//        System.out.println("All company's coupons: " + companyUser.getAllCoupons());
//
//        try {
//            System.out.println("All company's coupons from Food category: " + companyUser.getCategoryCoupons(Category.FOOD));
//            System.out.println("All company's coupons from Beauty category: " + companyUser.getCategoryCoupons(Category.BEAUTY));
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            System.out.println("All company's coupons that cost under 60: " + companyUser.getMaxPriceCoupons(60));
//            System.out.println("All company's coupons that cost under 10: " + companyUser.getMaxPriceCoupons(10));
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Company #1 details: " + companyUser.getCompanyDetails() + "\n");
//    }
//}