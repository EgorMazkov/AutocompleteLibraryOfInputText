package org.example.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SearchResult {

    private List<String[]> airportInformation;

    private long searchingTime;

    private int totalCount;

    private boolean isEmpty;
}
