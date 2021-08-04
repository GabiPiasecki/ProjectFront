package com.jb.CouponMaster.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**


 * Entity -an annotation makes the bean a table in the database
 * Data - from lombok gives us the the getters, setters, toString, equals and hashcode
 * AllArgsConstractor provides a constructor that requires all of the attributes
 * NoArgsConstractors provides a default constructor that requires no args
 * Builder for working builder methods
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {
    /**
     * Customer's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private int id;

    /**
     * Customer's first name
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Customer's last name
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Customer's email
     */
    @Column(nullable = false)
    private String email;

    /**
     * Customer's password
     */
    @Column(nullable = false)
    //@Getter(AccessLevel.PRIVATE)
    private String password;

    /**
     * Customer's coupons list
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Singular
    private List<Coupon> coupons;
}