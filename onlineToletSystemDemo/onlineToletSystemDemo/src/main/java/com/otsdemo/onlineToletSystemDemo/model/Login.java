package com.otsdemo.onlineToletSystemDemo.model;

public class Login {

    public boolean loggedin;

    public Login(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public  boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }
}
