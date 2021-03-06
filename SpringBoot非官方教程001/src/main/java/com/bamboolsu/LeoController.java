package com.bamboolsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({ConfigBean.class})
public class LeoController {
    @Autowired
    ConfigBean configBean;

    // 访问地址： http://localhost:8080/springboot/leo
    @RequestMapping(value = "/leo")
    public String miya() {
        return configBean.getGreeting() + " >>>>" + configBean.getName() + " >>>>" + configBean.getUuid() + " >>>>" + configBean.getMax();
    }

    // 访问地址： http://localhost:8080/springboot/leo

    @Autowired
    User user;

    @RequestMapping(value = "/user")
    public String user() {
        return user.getName() + user.getAge();
    }
}
