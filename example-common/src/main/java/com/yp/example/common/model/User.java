package com.yp.example.common.model;

import java.io.Serializable;

/**
 * @author yp
 * @date: 2024/3/10
 */
public class User implements Serializable {

    private static final long serialVersionUID = -5802314977971393517L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
