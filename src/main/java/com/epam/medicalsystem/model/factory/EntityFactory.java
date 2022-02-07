package com.epam.medicalsystem.model.factory;

import java.util.Map;
import java.util.Optional;

public interface EntityFactory<T> {
    Optional<T> create(Map<String, String> fields);
}
