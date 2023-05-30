package org.test.task.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaContainerTest {

    @Test
    public void shouldReturnBooleanLambdaResponse() {

        Function function = LambdaContainer.BOOLEAN.function;
        String result = (String) function.apply(Boolean.TRUE);
        assertEquals("Булево значение, правда", result);
    }

    @Test
    public void shouldReturnIntegerLambdaResponse() {

        Function function = LambdaContainer.INTEGER.function;
        String result = (String) function.apply(123);
        assertEquals("Целое число, * 10 = 1230", result);
    }

    @Test
    public void shouldReturnCollectionLambdaResponse() {

        Function function = LambdaContainer.COLLECTION.function;
        String result = (String) function.apply(Collections.singletonList("Wow"));
        assertEquals("Список, количество элементов у коллекции 1", result);
    }

    @Test
    public void shouldReturnStringLambdaResponse() {

        Function function = LambdaContainer.STRING.function;
        String result = (String) function.apply("Apple");
        assertEquals("Строка Apple, количество символов 5", result);
    }

    @Test
    public void shouldReturnDefaultLambdaResponse() {

        Function function = LambdaContainer.DEFAULT.function;
        String result = (String) function.apply(LocalDate.of(2023, 5, 30));
        assertEquals("Другое, 2023-05-30", result);
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void shouldReturnCorrectLambdaExpression(Object input, Function expected) {
        assertEquals(expected, LambdaContainer.getSuitableLambda(input));
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of("abc", LambdaContainer.STRING.function),
                Arguments.of(777, LambdaContainer.INTEGER.function),
                Arguments.of(Boolean.TRUE, LambdaContainer.BOOLEAN.function),
                Arguments.of(null, LambdaContainer.DEFAULT.function),
                Arguments.of(new HashSet<>(), LambdaContainer.COLLECTION.function),
                Arguments.of(LocalDateTime.now(), LambdaContainer.DEFAULT.function)

        );
    }

}
