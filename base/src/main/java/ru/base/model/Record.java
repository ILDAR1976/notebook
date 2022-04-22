
package ru.base.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

public class Record extends AbstractNamedEntity {

    private String desctiption;
    private Date date_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;







    public String getDesctiption() {
        return this.desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public Date getDate_time() {
        return this.date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }





    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record that = (Record) o; 
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : Objects.hash();
    }

    @Override
    public String toString() {
        return "{" +
            "}";
    }

    @Override
    public int compareTo(AbstractBaseEntity arg0) {
        return 0;
    }

}