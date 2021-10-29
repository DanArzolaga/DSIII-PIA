package com.example.sqliteandroidstudiojava.entidades;

public class User {

    private int idUser;
    private String nameUser;
    private String email;
    private String password;

    public User(){

    }

    public User(int id, String name, String email, String pass){
        this.idUser = id;
        this.nameUser = name;
        this.email= email;
        this.password = pass;
    }

    public User(String name, String email, String pass){
        this.nameUser = name;
        this.email= email;
        this.password = pass;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
