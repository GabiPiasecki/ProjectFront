package com.jb.CouponMaster.Services;

import com.jb.CouponMaster.Beans.Category;
import com.jb.CouponMaster.Beans.Coupon;
import com.jb.CouponMaster.Beans.Customer;
import com.jb.CouponMaster.Exceptions.CouponMasterException;
import com.jb.CouponMaster.Repositories.CouponRepository;
import com.jb.CouponMaster.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService extends ClientService{
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private int customerID;

    /**
     * A method that overrides its parent login method, and validates that the credentials given are valid via
     the isCustomerExists method and assigns its customer ID for later use.
     * @param email customer's email
     * @param password customer's password
     * @return An indication whether the action was successful or not
     */

    public boolean login(String email, String password) throws CouponMasterException {
        if (!customerRepository.existsByEmail(email)) {
            throw new CouponMasterException("There is no account with that email!");
        } if (customerRepository.existsByEmailAndPassword(email, password)) {
            this.customerID = customerRepository.getOneByEmailAndPassword(email, password).getId();
        } else throw new CouponMasterException("Wrong password!");
        return true;
    }
    /**
     * A method that allows the customer user to purchase a coupon under a set of conditions. Throws a custom
     exception is all the conditions are not met. if they do, it deducts the amount of the relevant coupon by 1.
     * @param coupon An instance of a coupon to be purchased
     * @return An indication whether the action was successful or not
     * @throws CouponMasterException
     */
    public boolean purchaseCoupon(Coupon coupon) throws CouponMasterException {
        if (coupon.getAmount() < 1) {
            throw new CouponMasterException("This coupon is out of stock!");
        }
        Customer customer = customerRepository.getOne(customerID);
        List<Coupon> coupons = customer.getCoupons();
        for (Coupon item : coupons) {
            if (item.equals(coupon)) {
                throw new CouponMasterException("You have already purchased this coupon!");
            } else if (coupon.getEndDate().isBefore(LocalDate.now())) {
                throw new CouponMasterException("This coupon is expired!");
            }
        }
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepository.saveAndFlush(coupon);
        coupons.add(coupon);
        customer.setCoupons(coupons);
        customerRepository.saveAndFlush(customer);
        return true;
    }

    /**
     * A method that allows the customer user to fetch all of its coupons, or throws a custom exception if there are none
     * @return A list of coupons
     * @throws CouponMasterException
     */
    public List<Coupon> getAllCoupons() throws CouponMasterException {
        Customer customer = customerRepository.getOne(customerID);
        if (customer.getCoupons().isEmpty()) {
            throw new CouponMasterException("There are no coupons to this customer yet!");
        }
        return customer.getCoupons();
    }
    /**
     * A method that allows the customer to fetch all of his coupon under a given category, or throws a custom
     exception if there are none
     * @param category The category which all of the coupons related to will be returned
     * @return A list of coupons
     * @throws CouponMasterException
     */
    public List<Coupon> getCategoryCoupons(Category category) throws CouponMasterException {
        List<Coupon> returnCoupons = new ArrayList<>();
        Customer customer = customerRepository.getOne(customerID);
        List<Coupon> coupons = customer.getCoupons();
        if (coupons.isEmpty()) {
            throw new CouponMasterException("The customer has no coupons yet!");
        }

        if (category == null) {
            throw new CouponMasterException("This is not a valid category!");
        }

        for (Coupon item : coupons) {
            if (item.getCategory().equals(category)) {
                returnCoupons.add(item);
            }
        }
        if (returnCoupons.isEmpty()) {
            throw new CouponMasterException("There are no coupons from this category to this customer!");
        }
        return returnCoupons;
    }
    /**
     * A method that allows the customer to fetch all of his coupons under a given price, or throws a custom
     excpetion if there are none
     * @param maxPrice The maximum price of the returned coupons
     * @return A list of coupons
     * @throws CouponMasterException
     */
    public List<Coupon> getMaxPriceCoupons(double maxPrice) throws CouponMasterException {
        List<Coupon> returnCoupons = new ArrayList<>();
        Customer customer = customerRepository.getOne(customerID);
        List<Coupon> coupons = customer.getCoupons();
        if (coupons.isEmpty()) {
            throw new CouponMasterException("The customer has no coupons yet!");
        }
        for (Coupon item : coupons) {
            if (item.getPrice() <= maxPrice) {
                returnCoupons.add(item);
            }
        }
        if (returnCoupons.isEmpty()) {
            throw new CouponMasterException("There are no coupons under this price to this customer!");
        }
        return returnCoupons;
    }
    /**
     * A method that return the details of the logged in customer
     * @return customer instance
     */
    public Customer getCustomerDetails() {
        return customerRepository.findById(customerID);
    }
}