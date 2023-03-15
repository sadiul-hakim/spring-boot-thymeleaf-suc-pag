package com.smartcontact.smartcontactmanager.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank(message = "user name can not be blank")
    @Size(max = 50,message = "user name must be between 3-30")
    private String name;

    @NotBlank(message = "user email can not be blank")
    @Size(max = 50,message = "user name must be between 3-30")
    private String email;

    @NotBlank(message = "user password can not be blank")
    @Size(min = 6,max = 100,message = "user name must be between 6-100")
    private String password;

    private String role;

    private boolean enabled;

    private String imageUrl;

    @Size(min = 15,max = 400,message = "about must be between 15-400")
    private String about;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Contact> contacts=new ArrayList<>();

    public User() {
    }

    public User(String name, String email, String password, String role, boolean enabled, String imageUrl, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.imageUrl = imageUrl;
        this.about = about;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                ", imageUrl='" + imageUrl + '\'' +
                ", about='" + about + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
