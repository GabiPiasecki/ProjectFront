package com.jb.CouponMaster.Repositories;

import com.jb.CouponMaster.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Takes the the domain class to manage as well as the id type of the domain class as type arguments.
 * This interface acts primarily as a marker interface to capture the types to work with and to help
 * you to discover interfaces that extend this one. The CrudRepository provides sophisticated CRUD
 * functionality for the entity class that is being managed.
 * */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    /**
     * @param email Company's email
     * @param password Company's password
     * @return a boolean value whether there is a company with those email and password, for login purposes.
     */
    boolean existsByEmailAndPassword(String email, String password);

    /**
     * @param name Company's name
     * @return a boolean value whether such company with that already exists, to prevent two companies with the same name to be registered.
     */
    boolean existsByName(String name);

    /**
     * @param email Company's email
     * @return a boolean value whether a company with that email exists, for login initial check, and also
     * to prevent two companies with the same email address to be registered.
     */
    boolean existsByEmail(String email);

    /**
     * @param email Company's email
     * @param password Company's password
     * @return an instance of a company that matches those credentials. The ID attribute of this company
     * will be set as the logged in company id, in the login method.
     */
    Company getOneByEmailAndPassword(String email, String password);

    /**
     * @param id Company's ID
     * @return instance of a company that allows us to update a company in the REST controller.
     */
    Company findById(int id);

    Company findByEmail(String email);
    boolean deleteCompanyCouponsById(int companyID);
}
