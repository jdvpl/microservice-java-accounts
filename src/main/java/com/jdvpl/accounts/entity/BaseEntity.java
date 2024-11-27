package com.jdvpl.accounts.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseEntity es una clase base que proporciona campos comunes para las
 * entidades.
 * Esta clase está anotada con @MappedSuperclass, lo que significa que sus
 * campos
 * serán heredados por las entidades que la extiendan, pero no se mapeará
 * directamente
 * a una tabla de base de datos.
 *
 * Campos incluidos:
 * - createdAt: Fecha y hora en que se creó la entidad.
 * - updatedAt: Fecha y hora en que se actualizó la entidad por última vez.
 * - createdBy: Usuario que creó la entidad.
 * - updatedBy: Usuario que actualizó la entidad por última vez.
 */
@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

}
