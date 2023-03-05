package com.quiz.app.model;

public class JwtRequest {
    private String userEmail;
    private String password;

    public JwtRequest(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    @Override
    public String toString() {
        return "JwtRequest [userEmail=" + userEmail + ", password=" + password + "]";
    }
}
