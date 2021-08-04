package com.jb.CouponMaster.Services;

import com.jb.CouponMaster.Beans.Category;
import com.jb.CouponMaster.Beans.Company;
import com.jb.CouponMaster.Beans.Coupon;
import com.jb.CouponMaster.Exceptions.CouponMasterException;
import com.jb.CouponMaster.Repositories.CompanyRepository;
import com.jb.CouponMaster.Repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService extends ClientService {
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;
    private final AdminService adminService;
    private int companyID;

    /**
     * A method that overrides the login method of its parent, checks whether the credentials taken in exist
     via the exists by mail method, checks if the crednetials are corret via exists by email and password method
     and assigns its companyID for later use. throws relevant exception for any of these checks returning false.
     * @param email Company's email
     * @param password Company's password
     * @return An indication whether the action was successful or not
     * @throws CouponMasterException
     */
    public boolean login(String email, String password) throws CouponMasterException{
        if (!companyRepository.existsByEmail(email)) {
            throw new CouponMasterException("There is no company with this email!");
        } if (companyRepository.existsByEmailAndPassword(email, password)) {
            this.companyID = companyRepository.getOneByEmailAndPassword(email, password).getId();
        } else throw new CouponMasterException("Wrong password!");
        return true;
    }
    /**
     * A method that allows the company user to add a new coupon to the database, or throws a custom exception
     if a coupon with that title already exists
     * @param coupon A coupon instance to be added to the database
     * @return An indication whether the action was successful or not
     * @throws CouponMasterException
     */
    public boolean addCoupon(Coupon coupon) throws CouponMasterException {
        if (couponRepository.existsByTitleAndCompanyID(coupon.getTitle(), coupon.getCompanyID())) {
            throw new CouponMasterException("There is already a coupon with that title belongs to that company!");
        }
        coupon.setCompanyID(companyID);
        couponRepository.save(coupon);
        Company company = companyRepository.getOne(companyID);
        company.getCoupons().add(coupon);
        adminService.updateCompany(company);
        return true;
    }
    /**
     * A method that allows the company user to update an existing coupon and log it back in to the database
     * @param coupon A coupon instance to be updated and sent back to the database
     * @return An indication whether the action was successful or not
     */

    public boolean updateCoupon(Coupon coupon) throws CouponMasterException {
        if (coupon.getCompanyID() != this.companyID
        || !couponRepository.existsByTitleAndCompanyID(coupon.getTitle(), coupon.getCompanyID())) {
            throw new CouponMasterException("There is no such coupon related to this company!");
        }
        couponRepository.saveAndFlush(coupon);
        return true;
    }

    /**
     * A method that allows the company user to delete a coupon from the database identifying it via its id
     or throws a custom exception is there is no such coupon
     * @param couponID A coupon ID that will be the indicator for which coupon to delete from the database
     * @return An indication whether the action was successful or not
     * @throws CouponMasterException
     */
    public boolean deleteCoupon(int couponID) throws CouponMasterException {
        if (!couponRepository.existsById(couponID)) {
            throw new CouponMasterException("There is no coupon by that ID!");
        } if (couponRepository.findById(couponID).getCompanyID() != companyID) {
            couponRepository.deleteCustomerCouponsByCouponID(couponID);
            couponRepository.deleteCompanyCouponsByCouponID(couponID);
            couponRepository.deleteById(couponID);
            return true;
        }
        return false;
    }
    /**
     * A method that fetches all of the company's coupons, or throws a custom exception if there are none
     * @return A list of coupons
     * @throws CouponMasterException
     */

    public List<Coupon> getAllCoupons() throws CouponMasterException {
        if (couponRepository.findByCompanyID(companyID).isEmpty()) {
            throw new CouponMasterException("There are no coupons to this company yet!");
        }
        return couponRepository.findByCompanyID(companyID);
    }
    /**
     * A method that allows the company user to fetch a single coupon identifying it via its ID
     * @param couponID A coupon ID that will be the indicator of which coupon to fetch
     * @return An instance of a coupon
     */
    public Coupon getOneCoupon(int couponID) throws CouponMasterException {
        if (!couponRepository.existsById(couponID)) {
            throw new CouponMasterException("There is no coupon by that ID!");
        }
        return couponRepository.findById(couponID);
    }
    /**
     * A method that fetches all the coupons with the given category, or throws a custom exception if there are none
     * @param category A category that all the coupons under it will be returned
     * @return A list of coupons
     * @throws CouponMasterException
     */
    public List<Coupon> getCategoryCoupons(Category category) throws CouponMasterException {
        if (couponRepository.findByCompanyIDAndCategory(companyID, category).isEmpty()) {
            throw new CouponMasterException("There are no coupons in this category to this company!");
        }
        return couponRepository.findByCompanyIDAndCategory(companyID, category);
    }
    /**
     * A method that fetches all the coupons that have a price under the given value, or throws a custom exception
     if there are none
     * @param maxPrice The maximum price of the returned coupons
     * @return A list of coupons
     * @throws CouponMasterException
     */
    public List<Coupon> getMaxPriceCoupons(double maxPrice) throws CouponMasterException {
        if (couponRepository.findByCompanyIDAndPriceLessThan(companyID, maxPrice).isEmpty()) {
            throw new CouponMasterException("There are no coupons under this price to this company!");
        }
        return couponRepository.findByCompanyIDAndPriceLessThan(companyID, maxPrice);
    }

    /**
     * A method that fetches the details of the company currently logged in.
     * @return A company instance
     */
    public Company getCompanyDetails() {
        return companyRepository.findById(companyID);
    }
}