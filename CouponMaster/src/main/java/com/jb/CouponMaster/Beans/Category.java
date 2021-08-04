package com.jb.CouponMaster.Beans;

public enum Category {
    FOOD (1),
    ELECTRICITY (2),
    RESTAURANT (3),
    VACATION (4),
    HOME (5),
    SPORT (6),
    FASHION (7),
    BEAUTY (8),
    PETS (9);

    private final int id;

    /**
     *@param id the id of the category
     */
    Category(int id) {
        this.id = id;
    }

}