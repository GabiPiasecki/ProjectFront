package com.jb.CouponMaster.Authorization;

import com.jb.CouponMaster.Configuration.LoginManager.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private String email;
    private String password;
    private ClientType clientType;

    public UserDetails(String email, ClientType clientType) {
        this.email = email;
        this.clientType = clientType;
    }
}