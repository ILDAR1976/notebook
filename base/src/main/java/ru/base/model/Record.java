
package ru.base.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;



@NamedQuery(name = Record.GET, query = "SELECT r FROM Record r WHERE r.id=:id AND r.user.id=:userId")
@NamedQuery(name = Record.ALL_SORTED, query = "SELECT r FROM Record r WHERE r.user.id=:userId ORDER BY r.dateTime DESC")
@NamedQuery(name = Record.DELETE, query = "DELETE FROM Record r WHERE r.id=:id AND r.user.id=:userId")
@NamedQuery(name = Record.DELETE_ALL, query = "DELETE FROM Record i WHERE i.id=:userId")
@NamedQuery(name = Record.UPDATE, query = "UPDATE Record r SET r.dateTime = :datetime," +
                "r.description=:desc where r.id=:id and r.user.id=:userId")
@Entity
@Table(name = "records", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "records_unique_user_datetime_idx")})
public class Record extends AbstractNamedEntity {
    public static final String GET = "Record.get";
    public static final String ALL_SORTED = "Record.getAll";
    public static final String DELETE = "Record.delete";
    public static final String DELETE_ALL = "Record.delete_all";
    public static final String UPDATE = "Record.update";


    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
     private String description;
    
    @Column(name = "date_time", nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("dateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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
            this.name +
            this.description +
            this.dateTime.toString() +
            "}";
    }

    @Override
    public int compareTo(AbstractBaseEntity o) {
        if (this.id == o.id) {
            return 0;
        } else {
            return this.id - o.id;
        }
    }

}