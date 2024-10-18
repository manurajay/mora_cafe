package com.mora.cafe.POJO;

import com.mora.booking.models.Booking;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email=:email")
@NamedQuery(name = "User.getAllUsers",
        query = "select new com.mora.cafe.wrapper.UserWrapper(u) from User u JOIN u.roles r where r.name = 'ROLE_ADMIN'")
@NamedQuery(name = "User.getAllAdmins",
        query = "select new com.mora.cafe.wrapper.UserWrapper(u) from User u JOIN u.roles r where r.name = 'ROLE_USER'")
//@NamedQuery(name = "User.deleteUser", query = "delete from User u where u.email  = :email")



@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
            @UniqueConstraint(columnNames = "contactNumber")
    })
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "contactNumber")
    private String contactNumber;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private boolean status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "user_roles",
        joinColumns = @JoinColumn(name = "email"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Booking> booking;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus() {
         this.status = true;
    }
}
