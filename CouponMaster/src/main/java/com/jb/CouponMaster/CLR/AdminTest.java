//package com.jb.CouponMaster.CLR;
//
//import com.jb.CouponMaster.Beans.Company;
//import com.jb.CouponMaster.Beans.Customer;
//import com.jb.CouponMaster.Configuration.LoginManager;
//import com.jb.CouponMaster.Exceptions.CouponMasterException;
//import com.jb.CouponMaster.Services.AdminService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Component
//@Order(1)
//@RequiredArgsConstructor
//public class AdminTest implements CommandLineRunner {
//    private final LoginManager loginManager;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("\n" + "----------ADMIN TEST----------");
//
//        //Admin login and methods
//        AdminService adminUser = (AdminService) loginManager.Login(LoginManager.ClientType.ADMINISTRATOR, "admin@gmail.com", "admin");
//
//        try {
//            AdminService anotherAdminUser = (AdminService) loginManager.Login(LoginManager.ClientType.ADMINISTRATOR, "Aadmin@gmail.com", "Aadmin");
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        Company company1 = Company.builder()
//                .name("Company 1")
//                .email("comp1@gmail.com")
//                .password("company1")
//                .build();
//
//        Company company2 = Company.builder()
//                .name("Company 2")
//                .email("company2@gmail.com")
//                .password("company2")
//                .build();
//
//        Company company3 = Company.builder()
//                .name("Company 3")
//                .email("comp3@gmail.com")
//                .password("company3")
//                .build();
//
//        Customer customer1 = Customer.builder()
//                .firstName("First 1")
//                .lastName("Last 1")
//                .email("cust1@gmail.com")
//                .password("customer1")
//                .build();
//
//        Customer customer2 = Customer.builder()
//                .firstName("First 2")
//                .lastName("Last 2")
//                .email("customer2@gmail.com")
//                .password("customer2")
//                .build();
//
//        Customer customer3 = Customer.builder()
//                .firstName("First 3")
//                .lastName("Last 3")
//                .email("cust3@gmail.com")
//                .password("customer2")
//                .build();
//
//        //Company methods
//        try{
//        adminUser.addCompany(company1);
//        adminUser.addCompany(company2);
//        adminUser.addCompany(company3);
//        adminUser.addCompany(company3);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        //Getting the companies with their ID from the DB (for updating by ID later)
//        try{
//        company1 = adminUser.getOneCompany(1);
//        company2 = adminUser.getOneCompany(2);
//        company3 = adminUser.getOneCompany(3);
//        company3 = adminUser.getOneCompany(4);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        company2.setEmail("comp2@gmail.com");
//
//        try{
//            adminUser.updateCompany(company2);
//            Company company4 = Company.builder()
//                    .name("Company")
//                    .email("comp@gmail.com")
//                    .password("company")
//                    .build();
//            adminUser.updateCompany(company4);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Company #3 details: " + adminUser.getOneCompany(3));
//
//        try{
//            adminUser.deleteCompany(3);
//            adminUser.deleteCompany(4);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("All companies details: " + adminUser.getAllCompanies());
//        System.out.println();
//
//        //Customer methods
//        try{
//            adminUser.addCustomer(customer1);
//            adminUser.addCustomer(customer2);
//            adminUser.addCustomer(customer3);
//            adminUser.addCustomer(customer3);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        //Getting the customers with their ID from the DB (for updating by ID later)
//        try{
//        customer1 = adminUser.getOneCustomer(1);
//        customer2 = adminUser.getOneCustomer(2);
//        customer3 = adminUser.getOneCustomer(3);
//        customer3 = adminUser.getOneCustomer(4);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        customer2.setEmail("cust2@gmail.com");
//
//        try{
//            adminUser.updateCustomer(customer2);
//            Customer customer4 = Customer.builder()
//                    .firstName("First")
//                    .lastName("Last")
//                    .email("cust@gmail.com")
//                    .password("customer")
//                    .build();
//            adminUser.updateCustomer(customer4);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Customer #3 details: " + adminUser.getOneCustomer(3));
//
//        try{
//            adminUser.deleteCustomer(3);
//            adminUser.deleteCustomer(4);
//        } catch (CouponMasterException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("All customers details: " + adminUser.getAllCustomers() + "\n");
//    }
//}