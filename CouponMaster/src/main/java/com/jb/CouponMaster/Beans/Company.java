package com.jb.CouponMaster.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 ** Entity -an annotation makes the bean a table in the database
 *  * Data - from lombok gives us the the getters, setters, toString, equals and hashcode
 *  * AllArgsConstructor provides a constructor that requires all of the attributes
 *  * NoArgsConstructors provides a default constructor that requires no args
 *  * Builder for working builder methods
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Company {

    /**
     *Company's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private int id;

    /**
     *Company's name
     */
    @Column(nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private String name;

    /**
     *Company's email
     * */
    @Column(nullable = false)
    private String email;

    /**
     *Company's password
     */
    @Column(nullable = false)
    //@Getter(AccessLevel.PRIVATE)
    private String password;

    /**
     *List of the company's coupons
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Singular
    private List<Coupon> coupons;
}
