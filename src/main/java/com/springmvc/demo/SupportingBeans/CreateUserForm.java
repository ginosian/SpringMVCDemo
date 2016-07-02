package com.springmvc.demo.SupportingBeans;

import java.util.HashMap;

/**
 * Created by Martha on 7/2/2016.
 */
public class CreateUserForm {

    private String name;
    private String userName;
    private String password;
    private String role;
    private HashMap<String, String> errors = new HashMap<>(); // Error tag, Error message
    public boolean validate() {
        boolean validData = true;
        if (name.equals("")) {
            errors.put("name","Please enter your first name");
            validData = false;
        }
        if (userName.equals("") || (userName.indexOf('@') == -1)) {
            errors.put("username","Please enter a valid email address");
            validData = false;
        }
        if (password.equals("") ) {
            errors.put("password","Please enter a valid password");
            validData = false;
        }
        if (role.equals("") ) {
            errors.put("password","Please choose authorization level");
            validData = false;
        }
        return validData;
    }
    public String getErrorMsg(String s) {
        String errorMsg = errors.get(s.trim());
        return (errorMsg == null) ? "":errorMsg;
    }

    public void set(String name, String userName, String password, String role) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public CreateUserForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
