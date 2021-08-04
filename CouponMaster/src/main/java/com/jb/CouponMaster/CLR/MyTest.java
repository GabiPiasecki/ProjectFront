//Original single CLR that was divided into the three current ones

package com.jb.CouponMaster.CLR;

import com.jb.CouponMaster.Beans.Category;
import com.jb.CouponMaster.Beans.Company;
import com.jb.CouponMaster.Beans.Coupon;
import com.jb.CouponMaster.Beans.Customer;
import com.jb.CouponMaster.Configuration.LoginManager;
import com.jb.CouponMaster.Services.AdminService;
import com.jb.CouponMaster.Services.CompanyService;
import com.jb.CouponMaster.Services.CustomerService;
import com.jb.CouponMaster.Utilities.MyConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class MyTest implements CommandLineRunner {
    private final LoginManager loginManager;

    public void run(String... args) throws Exception {
        //Building all the bean objects
        Coupon coupon1 = Coupon.builder()
                .category(Category.FOOD)
                .title("Cheeseburger")
                .description("Tasty Cheeseburger")
                .startDate(LocalDate.of(2021, 1, 1))
                .endDate(LocalDate.of(2021, 12, 1))
                .amount(1000)
                .price(35.90)
                .image("Burger's Image")
                .build();

        Coupon coupon2 = Coupon.builder()
                .category(Category.PETS)
                .title("Dog Bed")
                .description("Big dog comfy bed")
                .startDate(LocalDate.of(2021, 2, 1))
                .endDate(LocalDate.of(2021, 11, 1))
                .amount(2000)
                .price(79)
                .image("Dog bed's Image")
                .build();

        Coupon coupon3 = Coupon.builder()
                .category(Category.HOME)
                .title("Double couch")
                .description("Luxurious living room double couch")
                .startDate(LocalDate.of(2021, 3, 1))
                .endDate(LocalDate.of(2021, 10, 1))
                .amount(160)
                .price(1900.70)
                .image("Couch's Image")
                .build();

        Coupon coupon4 = Coupon.builder()
                .category(Category.VACATION)
                .title("Greece Weekend")
                .description("Three days in a greek hotel")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(280)
                .price(1590.80)
                .image("Greek Hotel Image")
                .build();

        Coupon coupon5 = Coupon.builder()
                .category(Category.SPORT)
                .title("Treadmill")
                .description("Professional house treadmill")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 10, 30))
                .amount(200)
                .price(1300)
                .image("Treadmill's Image")
                .build();

        Coupon coupon6 = Coupon.builder()
                .category(Category.BEAUTY)
                .title("Makeup brushes")
                .description("A set of eight professional makeup brushes")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 11, 30))
                .amount(2000)
                .price(129.50)
                .image("Brushes' Image")
                .build();

        Coupon coupon7 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("Toaster")
                .description("Kitchen toaster")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(2000)
                .price(119.20)
                .image("Toaster's Image")
                .build();

        Coupon coupon8 = Coupon.builder()
                .category(Category.FASHION)
                .title("Coat")
                .description("Fancy winter coat")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(2000)
                .price(149.90)
                .image("Coat's Image")
                .build();

        Coupon coupon9 = Coupon.builder()
                .category(Category.FASHION)
                .title("Jeans")
                .description("Blue Jeans")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(2390)
                .price(199.20)
                .image("Jeans' Image")
                .build();

        Coupon coupon10 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("Microwave")
                .description("Kitchen microwave")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(2300)
                .price(319.20)
                .image("Microwave's Image")
                .build();

        Coupon coupon11 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("Television")
                .description("63 inch Television")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 11, 1))
                .amount(2100)
                .price(1200.80)
                .image("Television's Image")
                .build();

        Coupon coupon12 = Coupon.builder()
                .category(Category.BEAUTY)
                .title("Face Mask")
                .description("Nourishing face mask")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 11, 15))
                .amount(3100)
                .price(25.10)
                .image("Face mask's Image")
                .build();

        Coupon coupon13 = Coupon.builder()
                .category(Category.SPORT)
                .title("Running shoes")
                .description("Professional running shoes")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(1000)
                .price(300)
                .image("Running shoes' Image")
                .build();

        Coupon coupon14 = Coupon.builder()
                .category(Category.VACATION)
                .title("Two days at Eilat")
                .description("Two days in a hostel at Eilat")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(3000)
                .price(600.80)
                .image("Eilat Hostel's Image")
                .build();

        Coupon coupon15 = Coupon.builder()
                .category(Category.HOME)
                .title("Dining Table")
                .description("Fancy dining table")
                .startDate(LocalDate.of(2021, 3, 1))
                .endDate(LocalDate.of(2021, 12, 1))
                .amount(2000)
                .price(910.70)
                .image("Table's Image")
                .build();

        Coupon coupon16 = Coupon.builder()
                .category(Category.PETS)
                .title("Cat Bed")
                .description("Medium size cat comfy bed")
                .startDate(LocalDate.of(2021, 2, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(3000)
                .price(69.30)
                .image("Cat bed's Image")
                .build();

        Coupon coupon17 = Coupon.builder()
                .category(Category.PETS)
                .title("Cat Brush")
                .description("Long haired cats' brush")
                .startDate(LocalDate.of(2021, 2, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(1500)
                .price(38.30)
                .image("brush's Image")
                .build();

        Coupon coupon18 = Coupon.builder()
                .category(Category.FOOD)
                .title("Pizza")
                .description("Yummy Pizza")
                .startDate(LocalDate.of(2021, 1, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(3000)
                .price(50.90)
                .image("Pizza's Image")
                .build();

        Coupon coupon19 = Coupon.builder()
                .category(Category.FOOD)
                .title("Sushi")
                .description("Original Asian Sushi")
                .startDate(LocalDate.of(2021, 1, 1))
                .endDate(LocalDate.of(2021, 12, 12))
                .amount(1900)
                .price(45.90)
                .image("Shushi's Image")
                .build();

        Coupon coupon20 = Coupon.builder()
                .category(Category.FOOD)
                .title("Burrito")
                .description("Original Mexican Burrito")
                .startDate(LocalDate.of(2021, 8, 1))
                .endDate(LocalDate.of(2021, 12, 30))
                .amount(3900)
                .price(32.80)
                .image("Burrito's Image")
                .build();

        Coupon coupon21 = Coupon.builder()
                .category(Category.BEAUTY)
                .title("Lip Balm")
                .description("Moisturizing Lip Balm")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 10, 15))
                .amount(2140)
                .price(14.90)
                .image("Lip Balm's Image")
                .build();

                Company company1 = Company.builder()
                .name("Company 1")
                .email("comp1@gmail.com")
                .password("company1")
                .build();

        Company company2 = Company.builder()
                .name("Company 2")
                .email("company2@gmail.com")
                .password("company2")
                .build();

        Company company3 = Company.builder()
                .name("Company 3")
                .email("comp3@gmail.com")
                .password("company3")
                .build();

        Company company4 = Company.builder()
                .name("Company 4")
                .email("comp4@gmail.com")
                .password("company4")
                .build();

        Customer customer1 = Customer.builder()
                .firstName("First 1")
                .lastName("Last 1")
                .email("cust1@gmail.com")
                .password("customer1")
                .build();

        Customer customer2 = Customer.builder()
                .firstName("First 2")
                .lastName("Last 2")
                .email("customer2@gmail.com")
                .password("customer2")
                .build();

        Customer customer3 = Customer.builder()
                .firstName("First 3")
                .lastName("Last 3")
                .email("cust3@gmail.com")
                .password("customer3")
                .build();

        Customer customer4 = Customer.builder()
                .firstName("First 4")
                .lastName("Last 4")
                .email("cust4@gmail.com")
                .password("customer4")
                .build();

        //Admin login and methods
        AdminService adminUser = (AdminService) loginManager.login(LoginManager.ClientType.ADMINISTRATOR, MyConstants.adminMail, MyConstants.adminPassword);

        adminUser.addCompany(company1);
        adminUser.addCompany(company2);
        adminUser.addCompany(company3);
        adminUser.addCompany(company4);
        //Getting the companies with their ID from the DB (for updating by ID later)
        company1 = adminUser.getOneCompany(1);
        company2 = adminUser.getOneCompany(2);
        company3 = adminUser.getOneCompany(3);
        company2.setEmail("comp2@gmail.com");
        adminUser.updateCompany(company2);
//        System.out.println("Company #3 details: " + adminUser.getOneCompany(3));
//        adminUser.deleteCompany(3);
//        System.out.println("All companies details: " + adminUser.getAllCompanies());

        adminUser.addCustomer(customer1);
        adminUser.addCustomer(customer2);
        adminUser.addCustomer(customer3);
        adminUser.addCustomer(customer4);
        //Getting the customers with their ID from the DB (for updating by ID later)
        customer1 = adminUser.getOneCustomer(1);
        customer2 = adminUser.getOneCustomer(2);
        customer3 = adminUser.getOneCustomer(3);
        customer2.setEmail("cust2@gmail.com");
        adminUser.updateCustomer(customer2);
//        System.out.println("Customer #3 details: " + adminUser.getOneCustomer(3));
//        adminUser.deleteCustomer(3);
//        System.out.println("All customers details: " + adminUser.getAllCustomers());
//        System.out.println();

        //Company login and methods
        CompanyService companyUser = (CompanyService) loginManager.login(LoginManager.ClientType.COMPANY, "comp1@gmail.com", "company1");

        companyUser.addCoupon(coupon1);
        companyUser.addCoupon(coupon2);
        companyUser.addCoupon(coupon3);
        companyUser.addCoupon(coupon4);
        companyUser.addCoupon(coupon5);
        companyUser.addCoupon(coupon6);
        companyUser.addCoupon(coupon7);
        companyUser.addCoupon(coupon8);
        companyUser.addCoupon(coupon9);
        companyUser.addCoupon(coupon10);
        companyUser.addCoupon(coupon11);
        companyUser.addCoupon(coupon12);
        companyUser.addCoupon(coupon13);
        companyUser.addCoupon(coupon14);
        companyUser.addCoupon(coupon15);
        companyUser.addCoupon(coupon16);
        companyUser.addCoupon(coupon17);
        companyUser.addCoupon(coupon18);
        companyUser.addCoupon(coupon19);
        companyUser.addCoupon(coupon20);
        companyUser.addCoupon(coupon21);

        //Getting the coupons with their ID from the DB (for updating by ID later)
        coupon1 = companyUser.getOneCoupon(1);
        coupon2 = companyUser.getOneCoupon(2);
        coupon3 = companyUser.getOneCoupon(3);
        coupon4 = companyUser.getOneCoupon(4);
        coupon1.setPrice(55);
        companyUser.updateCoupon(coupon1);
//        companyUser.deleteCoupon(4);
//        System.out.println("All company #1 coupons: " + companyUser.getAllCoupons());
//        System.out.println("All company #1 coupons from Food category: " + companyUser.getCategoryCoupons(Category.FOOD));
//        System.out.println("All company #1 coupons that cost under 60: " + companyUser.getMaxPriceCoupons(60));
//        System.out.println("Company #1 details: " + companyUser.getCompanyDetails());
//        System.out.println();

        //Customer login and methods
        CustomerService customerUser = (CustomerService) loginManager.login(LoginManager.ClientType.CUSTOMER,"cust1@gmail.com", "customer1");

        customerUser.purchaseCoupon(coupon2);
        customerUser.purchaseCoupon(coupon3);
        customerUser.purchaseCoupon(coupon16);
        customerUser.purchaseCoupon(coupon17);
        customerUser.purchaseCoupon(coupon21);

//        System.out.println("All customer #1 coupons: " + customerUser.getAllCoupons());
//        System.out.println("All customer #1 coupons from Home category: " + customerUser.getCategoryCoupons(Category.HOME));
//        System.out.println("All customer #1 coupons that cost under 40: " + customerUser.getMaxPriceCoupons(40));
//        System.out.println("Customer #1 details: " + customerUser.getCustomerDetails());
    }
}