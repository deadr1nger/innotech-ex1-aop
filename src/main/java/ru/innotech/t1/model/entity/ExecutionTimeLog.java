package ru.innotech.t1.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import ru.innotech.t1.model.enumeration.MethodType;

import java.util.UUID;


@Data
@Table(name = "log")
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionTimeLog implements Persistable<UUID> {
    @Id
    private UUID id;
    private String methodName;
    private MethodType methodType;
    private long executionTime;
    @Transient
    private boolean isNewEntry = true;

    @Override
    public boolean isNew() {
        return isNewEntry;
    }
}
