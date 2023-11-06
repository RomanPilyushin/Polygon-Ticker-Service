package com.example.restfulpolygon;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Getter
@Setter
public class DailyOpenClose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Symbol is mandatory")
    private String symbol;

    @NotBlank(message = "Date is mandatory")
    // If you're using a String for the date, ensure it's in the correct format
    // For actual date types, Hibernate will take care of the validation for correct date format
    private String from;

    @NotNull(message = "Open price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Open price must be greater than 0")
    private double open;

    @NotNull(message = "Close price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Close price must be greater than 0")
    private double close;

    @NotNull(message = "High price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "High price must be greater than 0")
    private double high;

    @NotNull(message = "Low price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Low price must be greater than 0")
    private double low;

    @NotNull(message = "Volume cannot be null")
    @Min(value = 1, message = "Volume must be greater than 0")
    private double volume;

    private double afterHours;
    private double preMarket;

    @NotBlank(message = "Status is mandatory")
    private String status;

    // Constructors, getters, and setters...
}
