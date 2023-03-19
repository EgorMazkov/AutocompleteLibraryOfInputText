package org.example.api;

import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MainColumnLineFormatter implements LineFormatter {

    private static final String PATTERN = "{0} {1}";

    private final int columnNumber;

    @Override
    public List<String> format(List<String[]> lines) {
        return lines.stream()
                .map(this::formatting)
                .collect(Collectors.toList());
    }

    private String formatting(String[] line) {
        return MessageFormat.format(PATTERN, line[columnNumber], Arrays.toString(line));
    }
}
