package ru.base.to;

import java.util.Objects;

public class AuthenticationRequestTo {
    private String id;
    private String name;
    private String email;
    private String password;
    

    public AuthenticationRequestTo() {
    }

    public AuthenticationRequestTo(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationRequestTo id(String id) {
        setId(id);
        return this;
    }

    public AuthenticationRequestTo name(String name) {
        setName(name);
        return this;
    }

    public AuthenticationRequestTo email(String email) {
        setEmail(email);
        return this;
    }

    public AuthenticationRequestTo password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthenticationRequestTo)) {
            return false;
        }
        AuthenticationRequestTo authenticationRequestTo = (AuthenticationRequestTo) o;
        return Objects.equals(id, authenticationRequestTo.id) && Objects.equals(name, authenticationRequestTo.name) && Objects.equals(email, authenticationRequestTo.email) && Objects.equals(password, authenticationRequestTo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

    

}