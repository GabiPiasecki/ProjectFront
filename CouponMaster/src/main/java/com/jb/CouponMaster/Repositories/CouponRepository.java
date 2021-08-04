package com.jb.CouponMaster.Repositories;

import com.jb.CouponMaster.Beans.Category;
import com.jb.CouponMaster.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    /**
     * @param title Coupon's title
     * @param companyID Coupon's company ID
     * @return a boolean value that indicated is a coupon with the same title and that companyID already exists
     * to prevent the possibility that a company would add two coupons with the same title.
     */
    boolean existsByTitleAndCompanyID(String title, int companyID);

    /**
     * @param companyID Coupon's company ID
     * @return a list of coupons that all belong to the company with given Id.
     */
    List<Coupon> findByCompanyID(int companyID);

    /**
     * @param companyID Coupon's company ID
     * @param category Coupon's category
     * @return a list of coupons that all belong to the company with given Id and match the given category.
     */
    List<Coupon> findByCompanyIDAndCategory(int companyID, Category category);

    /**
     *
     * @param category
     * @return
     */
    List<Coupon> findByCategory(Category category);

    /**
     * @param companyID Coupon's company ID
     * @param maxPrice Coupon's maximum price
     * @return a list of coupons that all belong to the company with given Id and have a lesser price value than the number given
     */
    List<Coupon> findByCompanyIDAndPriceLessThan(int companyID, double maxPrice);

    Coupon findById(int id);

    /**
     * @param couponID Coupon's ID
     * A custom query that deletes the coupon's Id from the connecting table customer_coupons. Used in the deleteCoupon
     * method, before the actual coupon is deleted, as the coupon's id is a foreign key.
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `springcouponmaster`.`customer_coupons` WHERE coupons_id=:couponID", nativeQuery = true)
    void deleteCustomerCouponsByCouponID(int couponID);

    /**
     * A custom query that deletes the coupon's Id from the connecting table company_coupons. Used in the deleteCoupon
     * method, before the actual coupon is deleted, as the coupon's id is a foreign key.
     * @param couponID Coupon's ID
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `springcouponmaster`.`company_coupons` WHERE coupons_id=:couponID", nativeQuery = true)
    void deleteCompanyCouponsByCouponID(int couponID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `springcouponmaster`.`company_coupons` WHERE company_id=:companyID", nativeQuery = true)
    void deleteCompanyCouponsByCompanyID(int companyID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `springcouponmaster`.`coupon` WHERE companyid=:companyID", nativeQuery = true)
    void deleteCouponsByCompanyID(int companyID);
}