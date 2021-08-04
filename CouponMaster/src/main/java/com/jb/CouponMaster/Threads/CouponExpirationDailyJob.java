package com.jb.CouponMaster.Threads;

import com.jb.CouponMaster.Beans.Coupon;
import com.jb.CouponMaster.Repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@EnableAsync
@RequiredArgsConstructor
public class CouponExpirationDailyJob {
    private final CouponRepository couponRepository;

    /**
     * A method that sets the time of day it will be run, what is the period between every run,
      the action itself, which is to delete all coupons with passed expiration date, and the condition for it to stop.
     */
    @Async
    @Scheduled(cron = "1 0 0 * * ?", zone = "Asia/Jerusalem")
    public void dailyJob() {
        List<Coupon> coupons = couponRepository.findAll();
        for (Coupon item : coupons) {
            if (item.getEndDate().isBefore(LocalDate.now())) {
                couponRepository.deleteCustomerCouponsByCouponID(item.getId());
                couponRepository.deleteCompanyCouponsByCouponID(item.getId());
                couponRepository.deleteById(item.getId());
                System.out.println(item.getTitle() + " was deleted!");
            }
        }
    }
}
