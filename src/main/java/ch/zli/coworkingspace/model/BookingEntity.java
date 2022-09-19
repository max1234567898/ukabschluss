package ch.zli.coworkingspace.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "BOOKING")
public class BookingEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id = UUID.randomUUID();

    @Column(name = "startDate", nullable = false)
    private Timestamp startDate;

    @Column(name = "endDate", nullable = false)
    private Timestamp endDate;

    @Column(name = "is_accepted", nullable = false)
    private Boolean is_accepted;

    @ManyToOne
    private MemberEntity memberEntity;

    @ManyToOne
    private PlaceEntity placeEntity;





}
