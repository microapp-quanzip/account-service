package com.viettel.account.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false, of = "id")
@Entity
@Table(name = "account")
@Data
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String passW;
    private String email;
    private int status;

    @Schema(name = "Roles of one account")
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Role> roles;
}
