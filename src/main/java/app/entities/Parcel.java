package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder

public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String trackingNumber;

    @Column(nullable = false)
    private String senderName;

    @Column(nullable = false)
    private String receiverName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus deliveryStatus;

    @Column(nullable = false)
    private LocalDateTime updated;

    // Kaldes før entiteten er persisteret for første gang
    @PrePersist
    protected void prePersist(){
        updated = LocalDateTime.now();
    }

    // Kaldes før entiteten er opdateret
    @PreUpdate
    protected void preUpdate(){
        updated = LocalDateTime.now();
    }
}

