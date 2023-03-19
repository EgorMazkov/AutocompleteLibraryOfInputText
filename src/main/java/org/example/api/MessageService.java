package org.example.api;

import org.example.model.SearchResult;

public interface MessageService {
    void printLine(SearchResult searchResult);

    void notFoundMessage(SearchResult searchResult);
}
