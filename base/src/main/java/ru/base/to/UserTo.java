package ru.base.to;

import ru.base.model.Role;
import ru.base.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class UserTo implements Serializable {

    protected Integer id;

    @NotBlank
    @Size(min = 2, max = 100)
    protected String name;

    @NotBlank
    @Size(max = 100)
    @Email
    protected String email;

    @NotBlank
    @Size(min = 5, max = 15, message ="must between 5 and 15 characters")
    protected String password;

    
    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isNew() {
        return id == null;
    }


    @Override
    public String toString() {
                return "UserTo{" +
                                "id=" + id +
                                ", name='" + name + '\'' +
                                ", email='" + email + '\'' +
                                '}';
            }

    public void updateUser(User user){
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
    }

    public User asNewUser(){
        return  new User(null, name, email, password, Role.ROLE_USER);
    }
}
