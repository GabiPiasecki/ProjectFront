package com.jb.CouponMaster.Exceptions;

public class CouponMasterException extends Exception {
    public CouponMasterException() {
        super();
    }

    /**
     * A method that return an exception with a custom message
     * @param message from the throwing method
     */
    public CouponMasterException(String message) {
        super(message);
    }
}
