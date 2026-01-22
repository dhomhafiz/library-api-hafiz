package com.hafiz.library_api_hafiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "borrowers", uniqueConstraints = {
        @UniqueConstraint(name = "uk_borrower_email", columnNames = "email")
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;
}
