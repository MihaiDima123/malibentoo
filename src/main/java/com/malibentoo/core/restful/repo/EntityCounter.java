package com.malibentoo.core.restful.repo;

public interface EntityCounter {
    int countByName(String name, boolean includeInactive);
    boolean existsByName(String name, boolean includeInactive);

    default int countByName(String name) {
        return countByName(name, false);
    }

    default boolean existsByName(String name) {
        return existsByName(name, false);
    }
}
