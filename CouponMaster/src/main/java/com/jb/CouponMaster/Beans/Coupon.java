package com.jb.CouponMaster.Beans;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * all the annotation we need for working with spring
 *
 * * Entity -an annotation makes the bean a table in the database
 *  * Data - from lombok gives us the the getters, setters, toString, equals and hashcode
 *  * AllArgsConstractor provides a constructor that requires all of the attributes
 *  * NoArgsConstractors provides a default constructor that requires no args
 *  * Builder for working builder methods
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Coupon {
    /**
     *Coupon's id field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private int id;

    /**
     *Coupon's company ID
     */
    private int companyID;

    /**
     *Coupon's category
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    /**
     *Coupon's title
     */
    @Column(nullable = false)
    private String title;

    /**
     *Coupon's description
     */
    private String description;

    /**
     *Coupon's start date
     */
    private LocalDate startDate;

    /**
     *Coupon's end date
     */
    @Column(nullable = false)
    private LocalDate endDate;

    /**
     *Coupon's amount
     */
    @Column(nullable = false)
    private int amount;

    /**
     *Coupon's price
     */
    @Column(nullable = false)
    private double price;

    /**
     *Coupon's image link
     */
    private String image;
}
