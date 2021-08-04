package com.jb.CouponMaster.Repositories;

import com.jb.CouponMaster.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    /**
     * @param email Customer's email
     * @param password Customer's password
     * @return a boolean value whether a customer with these credentials exists, for login purposes
     */
    boolean existsByEmailAndPassword(String email, String password);

    /**
     * @param email Customer's email
     * @param password Customer's password
     * @return an instance of a customer that matches those credentials. The Id attribute of this customer
     *      * will be set as the logged in customer id, in the login method.
     */
    Customer getOneByEmailAndPassword(String email, String password);

    /**
     * @param email Customer's email
     * @return a boolean value whether a customer with that email exists, for login initial check.
     */
    boolean existsByEmail(String email);

    /**
     * @param customerID
     * @return instance of a customer that allows us to update a customer with REST controller
     */
    Customer findById(int customerID);
    Customer findByEmail(String email);
}