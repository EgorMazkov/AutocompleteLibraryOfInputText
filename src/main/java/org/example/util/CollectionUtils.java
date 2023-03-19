package org.example.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtils {

    public static <T> List<T> mergeAndSortValues(Collection<T> a, Collection<T> b, Comparator<T> comparator) {
        return Stream.concat(a.stream(), b.stream())
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
