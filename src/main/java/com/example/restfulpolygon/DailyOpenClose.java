package com.example.restfulpolygon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok annotation to create getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to create a no-args constructor
public class DailyOpenClose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore // This will prevent 'id' from being included in the JSON
    private Long id;

    private String symbol;

    @Column(name = "from_date") // assuming the column is named 'from_date' because 'from' is a reserved keyword
    @JsonProperty("from") // Specify the JSON property name
    private String fromDate;

    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private double afterHours;
    private double preMarket;
    private String status;

    // Lombok will handle the getters and setters
}
