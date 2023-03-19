package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.api.ColumnRecordRepository;
import org.example.api.SearchService;
import org.example.constant.FileAttribute;
import org.example.model.LineData;
import org.example.model.SearchResult;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FileCSVSearchService implements SearchService {

    private final ColumnRecordRepository columnRecordRepository;

    @Override
    public SearchResult searchByInput(String input) {
        String inputLower = input.toLowerCase();
        List<LineData> lineData = columnRecordRepository.findByInput(inputLower);
        long startTime = System.currentTimeMillis();
        if (lineData.isEmpty()) {
            return SearchResult.builder()
                    .totalCount(0)
                    .searchingTime(System.currentTimeMillis() - startTime)
                    .isEmpty(true)
                    .build();
        }
        int maxIndex = getMaxIndex(lineData);
        int minIndex = getMinIndex(lineData);
        Set<Integer> indexes = toIndexesByInput(inputLower, lineData);
        List<String[]> resultLines = new ArrayList<>();
        InputStream resourceAsStream = openFile();
        try (Scanner scanner = new Scanner(resourceAsStream)) {
            for (int index = 0; index <= maxIndex && scanner.hasNext(); index++) {
                if (index < minIndex || !indexes.contains(index)) {
                    scanner.nextLine();
                    continue;
                }
                String[] lines = scanner.nextLine().split(FileAttribute.SEPARATOR);
                resultLines.add(lines);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        return SearchResult.builder()
                .airportInformation(resultLines)
                .totalCount(resultLines.size())
                .searchingTime(endTime - startTime)
                .isEmpty(resultLines.isEmpty())
                .build();
    }

    private Set<Integer> toIndexesByInput(String input, List<LineData> lineData) {
        return lineData.stream()
                .filter(line -> isContains(input, line))
                .map(LineData::getIndex)
                .collect(Collectors.toSet());
    }

    private int getMinIndex(List<LineData> lineData) {
        return lineData.get(0).getIndex();
    }

    private int getMaxIndex(List<LineData> lineData) {
        return lineData.get(lineData.size() - 1).getIndex();
    }

    private InputStream openFile() {
        return this.getClass().getResourceAsStream(FileAttribute.FILE_NAME);
    }

    private boolean isContains(String input, LineData line) {
        return line.getValue().startsWith(input);
    }

}
