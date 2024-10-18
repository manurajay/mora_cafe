package com.mora.cafe.wrapper;

import com.mora.cafe.POJO.User;
import lombok.Data;

@Data
public class UserWrapper {
    private String username;
    private String name;
    private String email;
    private String contactNumber;
    private boolean status;

    public UserWrapper(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
        this.contactNumber = user.getContactNumber();
        this.status = user.getStatus();
    }
}
