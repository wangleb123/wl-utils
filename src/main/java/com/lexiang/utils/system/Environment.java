package com.lexiang.utils.system;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class Environment {

    public String SystemName(){
        return System.getProperty("os.name");
    }

}
