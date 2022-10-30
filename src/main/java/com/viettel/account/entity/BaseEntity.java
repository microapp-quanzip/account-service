package com.viettel.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class BaseEntity {
    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDate createdDate;

    @JsonIgnore
    @Column(name = "created_by")
    private String createdBy;


    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @JsonIgnore
    @Column(name = "modified_by")
    private String modifiedBy;
}
