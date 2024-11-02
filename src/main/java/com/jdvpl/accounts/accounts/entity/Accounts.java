package com.jdvpl.accounts.accounts.entity;

import com.jdvpl.accounts.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Accounts extends BaseEntity {

    @Column(name = "customer_id")
    private Long customerId;
    @Id
    @Column(name = "account_number")
    private Long accountNumer;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "branch_address")
    private String branchAddres;
}
