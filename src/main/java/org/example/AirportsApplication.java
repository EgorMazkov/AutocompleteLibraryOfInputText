package org.example;

import org.example.service.DefaultAirportService;
import org.example.validator.ArgumentValidator;

public class AirportsApplication {
    public static void main(String[] args) {
        DefaultAirportService.getInstance(ArgumentValidator.checkAndGetColumnNumberOrExit(args)).run();
    }
}
