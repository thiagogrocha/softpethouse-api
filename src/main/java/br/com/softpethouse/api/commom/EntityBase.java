package br.com.softpethouse.api.commom;

import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@Check(constraints = "upper(active) in ('S', 'N')")
public abstract class EntityBase{

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String active;

    @PrePersist
    private void prePersist() {
        setActive("S");
    }

}
