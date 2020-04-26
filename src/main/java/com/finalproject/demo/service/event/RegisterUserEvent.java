package com.finalproject.demo.service.event;

import com.finalproject.demo.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter

public class RegisterUserEvent extends ApplicationEvent {

    private User user;
    private String appUrl;

    public RegisterUserEvent(Object source, User user, String appUrl) {
        super(source);
        this.user = user;
        this.appUrl = appUrl;
    }
}
