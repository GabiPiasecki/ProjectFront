package com.jb.CouponMaster.Services;

import com.jb.CouponMaster.Beans.Company;
import com.jb.CouponMaster.Beans.Coupon;
import com.jb.CouponMaster.Beans.Customer;
import com.jb.CouponMaster.Exceptions.CouponMasterException;
import com.jb.CouponMaster.Repositories.CompanyRepository;
import com.jb.CouponMaster.Repositories.CouponRepository;
import com.jb.CouponMaster.Repositories.CustomerRepository;
import com.jb.CouponMaster.Utilities.MyConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService extends ClientService {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    /**
     * A method that verifies that the credentials taken in are valid.
     * @param email Admin's email
     * @param password Admin's password
     * @return An indication whether the action was successful or not
     */
    public boolean login(String email, String password) throws CouponMasterException{
        if (!email.equals(MyConstants.adminMail) || !password.equals(MyConstants.adminPassword)) {
            throw new CouponMasterException("Wrong email or password!");
        }
        return true;
    }

    /**
     * A method that allows the admin user to log in a new company to the database, under checked conditions
     * and throws a custom exception if the conditions arent met
     * @param company Instance of a company to be added to the database
     * @return An indication whether the action was successful or not
     * @throws CouponMasterException
     */
    public boolean addCompany(Company company) throws CouponMasterException {
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponMasterException("There is already a company by that name!");
        } else if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponMasterException("There is already a company with that email!");
        }
        companyRepository.save(company);
        return true;
    }

    /**
     * A method that allows the admin user to update the details of an existing company
     * and return in to the data base., and throws an exception if the there is no company with that id.
     * @param company Instance of a company to be updated and sent back to the database
     * @return An indication whether the action was successful or not
     * @throws CouponMasterException
     */
    public boolean updateCompany(Company company) throws CouponMasterException {
        if (!companyRepository.existsById(company.getId())) {
            throw new CouponMasterException("There is no company with that ID!");
        }
        companyRepository.saveAndFlush(company);
        return true;
    }

    /**
     * A method that allows the admin user to delete an existing company from the data base, identifying it via its id
     * and throws a custom exception if not
     * @param companyID Company ID that will be the indicator for which company to delete
     * @return An indication whether the action was successful or not
     * @throws CouponMasterException
     */
    //Check if coupons and coupon purchases were deleted
    public boolean deleteCompany(int companyID) throws CouponMasterException {
        if (!companyRepository.existsById(companyID)) {
            throw new CouponMasterException("There is no company by this ID!");
        }
        Company company = getOneCompany(companyID);
        List <Coupon> coupons = company.getCoupons();

        for(Coupon item:coupons){
            couponRepository.deleteCustomerCouponsByCouponID(item.getId());
            couponRepository.deleteCompanyCouponsByCouponID(item.getId());
            couponRepository.deleteById(item.getId());
        }
        companyRepository.deleteById(companyID);
        return true;
    }

    /**
     * a method that fetches all of the companies from the database if there are any, and throws a custom exception
     * if there are no companies in the database
     * @return A list of companies
     * @throws CouponMasterException
     */
    public List<Company> getAllCompanies() throws CouponMasterException {
        if (companyRepository.findAll().isEmpty()) {
            throw new CouponMasterException("There are no companies yet!");
        }
        return companyRepository.findAll();
    }

    /**
     * A method that fetches a single company from the database identifying it via its id if it exists
     * and throws a custom exception if not
     * @param companyID Company ID that will be the indicator for which company to return
     * @return the instance
     * @throws CouponMasterException
     */
    public Company getOneCompany(int companyID) throws CouponMasterException {
        if (!companyRepository.existsById(companyID)) {
            throw new CouponMasterException("There is no company by that ID!");
        }
        return companyRepository.findById(companyID);
    }

    /**
     * A method that allows the admin user to add a new customer to the database and throws a custom exception
     * if a customer with that email address already exists
     * @param customer Instance of a customer to be added to the database
     * @return An indication weather the action was successful or not
     * @throws CouponMasterException
     */
    public boolean addCustomer(Customer customer) throws CouponMasterException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponMasterException("There is already a customer with that email!");
        }
        customerRepository.save(customer);
        return true;
    }

    /**
     * A method that allows the admin user to update an existing customer and log it back it in to the database
     * and throws an exception if the there is no customer with that ID
     * @param customer Instance of a customer to be updated and sent back to the database
     * @return An indication weather the action was successful or not
     */
    public boolean updateCustomer(Customer customer) throws CouponMasterException {
        if (!customerRepository.existsById(customer.getId())) {
            throw new CouponMasterException("There is no customer with that ID!");
        }
        customerRepository.saveAndFlush(customer);
        return true;
    }

    /**
     * A method that allows the admin user to delete an existing customer from the database identifying it via his ID
     * or throws a custom exception if there is no such customer
     * @param customerID Customer ID that will be the indicator for which customer to delete
     * @return An indication whether the action was successful or not
     * @throws CouponMasterException
     */
    public boolean deleteCustomer(int customerID) throws CouponMasterException {
        if (!customerRepository.existsById(customerID)) {
            throw new CouponMasterException("There is no customer by this ID!");
        }
        Customer customer = getOneCustomer(customerID);
        List <Coupon> coupons = customer.getCoupons();

        for(Coupon item:coupons){
            couponRepository.deleteCustomerCouponsByCouponID(item.getId());
        }
        customerRepository.deleteById(customerID);
        return true;
    }
    /**
     * A method that fetches all the customers from the database, or throws a custom exception if there are not customers
     * @return A list of customers
     * @throws CouponMasterException
     */
    public List<Customer> getAllCustomers() throws CouponMasterException {
        if (customerRepository.findAll().isEmpty()) {
            throw new CouponMasterException("There are no customers yet!");
        }
        return customerRepository.findAll();
    }

    /**
     * A method that fetches a single customer from the database identifying it via his ID
     or throws a custom exception if there is no such customer
     * @param customerID Customer ID that will be the indicator for which customer to return
     * @return an instance of the requested customer
     * @throws CouponMasterException
     */
    public Customer getOneCustomer(int customerID) throws CouponMasterException {
        if (!customerRepository.existsById(customerID)) {
            throw new CouponMasterException("There is no customer with this ID!");
        }
        return customerRepository.findById(customerID);
    }
}