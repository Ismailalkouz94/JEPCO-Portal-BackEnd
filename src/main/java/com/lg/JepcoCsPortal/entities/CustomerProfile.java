package com.lg.JepcoCsPortal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "CustomerProfile.findByEmailAndPassword", query = "from CustomerProfile cp where " +
                "cp.email = ?1 and cp.password = ?2"),

        @NamedQuery(name = "CustomerProfile.findByFileNumber", query = "from CustomerProfile cp where " +
                "cp.fileNumber = ?1"),
        @NamedQuery(name = "CustomerProfile.findByMobileNumber", query = "from CustomerProfile cp where " +
                "cp.mobileNumber = ?1"),
        @NamedQuery(name = "CustomerProfile.findByNationalNumber", query = "from CustomerProfile cp where " +
                "cp.nationalNumber = ?1"),
        @NamedQuery(name = "CustomerProfile.findByEmail", query = "from CustomerProfile cp where " +
                "cp.email = ?1")

})


@Table(name = "WEB_CUST_PROFILE")
public class CustomerProfile implements Serializable {

    @Id
//    @Column(name = "CUST_MOB_NO" ,nullable = false ,unique = true ,length = 14)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String nationalNumber;

    @Column(unique = true)
    private String mobileNumber;

    @Column(unique = true)
    private String fileNumber;

    @Column(unique = true)
    private String email;

    private String password;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
