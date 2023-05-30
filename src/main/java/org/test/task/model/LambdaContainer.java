package org.test.task.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;

public enum LambdaContainer {

    BOOLEAN(Boolean.class, value -> "Булево значение, " + ((Boolean) value ? "правда" : "ложь")),
    INTEGER(Integer.class, value -> "Целое число, * 10 = " + ((Integer) value).longValue() * 10),
    COLLECTION(Collection.class, value -> "Список, количество элементов у коллекции " + ((Collection<?>) value).size()),
    STRING(String.class, value -> "Строка " + value + ", количество символов " + ((String) value).length()),
    DEFAULT(null, value -> "Другое, " + (isNull(value) ? null : value.toString())),
    ;

    public final Class<?> clazz;
    public final Function<?, String> function;

    private static final Map<Class<?>, Function<?, String>> lambdas = Arrays
            .stream(LambdaContainer.values())
            .collect(toMap(en -> en.clazz, en -> en.function));

    LambdaContainer(Class<?> clazz, Function<?, String> function) {
        this.clazz = clazz;
        this.function = function;
    }

    public static Function<?, String> getSuitableLambda(Object arg) {
        Object key = isNull(arg) ? null
                : Collection.class.isAssignableFrom(arg.getClass()) ? Collection.class : arg.getClass();
        return lambdas.getOrDefault(key, DEFAULT.function);
    }

}
