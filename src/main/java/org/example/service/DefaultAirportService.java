package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.api.*;
import org.example.model.SearchResult;
import org.example.repository.InMemoryColumnRecordRepository;
import org.example.type.ExitStatus;

import java.util.Scanner;

@RequiredArgsConstructor
public class DefaultAirportService implements AirportService {

    private static final String ENTER_THE_LINE = "Введите строку:";

    private static final String EXIT_MESSAGE = "!quit";

    private final SearchService searchService;

    private final MessageService messageService;

    public static AirportService getInstance(int columnNumber) {
        ColumnRecordRepository inMemoryColumnRecordRepository = new InMemoryColumnRecordRepository(columnNumber).init();
        SearchService searchService = new FileCSVSearchService(inMemoryColumnRecordRepository);
        LineFormatter lineFormatter = new MainColumnLineFormatter(columnNumber);
        MessageService messageService = new DefaultMessageService(lineFormatter);
        return new DefaultAirportService(searchService, messageService);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(ENTER_THE_LINE);
            String input = new Scanner(System.in).nextLine();
            if (input.isEmpty()) {
                continue;
            }
            if (input.equalsIgnoreCase(EXIT_MESSAGE)) {
                System.exit(ExitStatus.SUCCESS.count);
            }
            SearchResult searchResult = searchService.searchByInput(input);
            if (searchResult.isEmpty()) {
                messageService.notFoundMessage(searchResult);
                continue;
            }
            messageService.printLine(searchResult);
        }
    }


}
