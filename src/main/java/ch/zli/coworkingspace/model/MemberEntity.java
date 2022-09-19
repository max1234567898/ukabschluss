package ch.zli.coworkingspace.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "MEMBER")
public class MemberEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id = UUID.randomUUID();

    @Column(name = "firstname", nullable = false)
    String firstname;

    @Column(name = "lastname", nullable = false)
    String lastname;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name= "password", nullable = false)
    String password;

    @Column(name= "is_admin", nullable = false)
    Boolean is_admin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity that = (MemberEntity) o;
        return id.equals(that.id) && firstname.equals(that.firstname) && lastname.equals(that.lastname) && email.equals(that.email) && password.equals(that.password) && is_admin.equals(that.is_admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, email, password, is_admin);
    }
}
