package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.api.LineFormatter;
import org.example.api.MessageService;
import org.example.model.SearchResult;

import java.text.MessageFormat;
import java.util.List;

@RequiredArgsConstructor
public class DefaultMessageService implements MessageService {

    private static final String DEFAULT_MESSAGE_TEMPLATE = "Количество строк: {0} Время выполнения: {1} мс";

    private static final String NOT_FOUND_MESSAGE_TEMPLATE = "По запросу ничего не найдено. Время выполнения: {0} мс";

    private final LineFormatter lineFormatter;

    @Override
    public void printLine(SearchResult searchResult) {
        List<String> airportInformationFormattedLines = lineFormatter.format(searchResult.getAirportInformation());
        airportInformationFormattedLines.stream()
                .sorted()
                .forEach(System.out::println);
        String message = MessageFormat.format(DEFAULT_MESSAGE_TEMPLATE, searchResult.getTotalCount(), searchResult.getSearchingTime());
        System.out.println(message);
    }

    @Override
    public void notFoundMessage(SearchResult searchResult) {
        String message = MessageFormat.format(NOT_FOUND_MESSAGE_TEMPLATE, searchResult.getSearchingTime());
        System.out.println(message);
    }
}