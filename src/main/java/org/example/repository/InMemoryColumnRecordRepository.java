package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.api.ColumnRecordRepository;
import org.example.constant.FileAttribute;
import org.example.model.LineData;
import org.example.type.ExitStatus;
import org.example.util.CollectionUtils;
import org.example.util.StringUtils;

import java.io.InputStream;
import java.util.*;

@RequiredArgsConstructor
public class InMemoryColumnRecordRepository implements ColumnRecordRepository {

    private static final String FILE_NAME_ERROR = "Имя файла неверно";

    private static final String QUOTE = "\"";

    private static final String EMPTY = "";

    private final Map<Character, List<LineData>> firstSymbolLine = new HashMap<>();

    private final int columnNumber;

    public InMemoryColumnRecordRepository init() {
        InputStream resourceAsStream = this.getClass().getResourceAsStream(FileAttribute.FILE_NAME);
        if (resourceAsStream == null) {
            System.out.println(FILE_NAME_ERROR);
            System.exit(ExitStatus.ERROR.count);
        }
        try (Scanner scanner = new Scanner(resourceAsStream)) {
            for (int index = 0; scanner.hasNext(); index++) {
                String line = scanner.nextLine();
                String columnRecord = line.split(FileAttribute.SEPARATOR)[columnNumber]
                        .replace(QUOTE, EMPTY)
                        .toLowerCase();
                Character firstSymbol = StringUtils.firstChar(columnRecord);
                char lowerFirstSymbol = Character.toLowerCase(firstSymbol);
                firstSymbolLine.merge(
                        lowerFirstSymbol,
                        List.of(new LineData(columnRecord, index)),
                        (a, b) -> CollectionUtils.mergeAndSortValues(a, b, Comparator.comparing(LineData::getIndex))
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public List<LineData> findByInput(String input) {
        return firstSymbolLine.getOrDefault(Character.toLowerCase(StringUtils.firstChar(input)), new ArrayList<>());
    }
}
