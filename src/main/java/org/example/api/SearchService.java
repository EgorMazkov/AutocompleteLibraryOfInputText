package org.example.api;

import org.example.model.SearchResult;

public interface SearchService {
    SearchResult searchByInput(String input);
}
