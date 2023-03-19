package org.example.api;

import org.example.model.LineData;

import java.util.List;

public interface ColumnRecordRepository {
    List<LineData> findByInput(String input);
}
