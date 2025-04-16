package com.example.demo.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
@Data
public class Counter {
    @Id
    private String id;
    private long sequence;
}
