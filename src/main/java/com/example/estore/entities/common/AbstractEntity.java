package com.example.estore.entities.common;

import com.example.estore.enums.ValidationMessages;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.example.estore.enums.ValidationMessages.UUID_VALIDATION_MESSAGE;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private boolean deleted;

}