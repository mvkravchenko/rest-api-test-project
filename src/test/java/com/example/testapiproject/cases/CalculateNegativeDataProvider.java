package com.example.testapiproject.cases;

import com.example.testapiproject.entities.calculate.CalculateRequest;
import com.example.testapiproject.entities.calculate.Operations;
import com.example.testapiproject.entities.common.ErrorResponse;
import com.example.testapiproject.helpers.RND;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CalculateNegativeDataProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                arguments("TEST-55: Не задан левый операнд",
                        new CalculateRequest(null, Operations.MINUS, new Random().nextInt(1000)),
                        400,
                        new ErrorResponse("\"left_operand\" is not specified in request body")),

                arguments("TEST-56: Не задан правый операнд",
                        new CalculateRequest(new Random().nextInt(1000), Operations.MINUS, null),
                        400,
                        new ErrorResponse("\"right_operand\" is not specified in request body")),

                arguments("TEST-57: Не задана операция",
                        new CalculateRequest(new Random().nextInt(1000), null, new Random().nextInt(1000)),
                        400,
                        new ErrorResponse("\"operation\" is not specified in request body")),

                arguments("TEST-58: Не заданы все параметры",
                        new CalculateRequest(null, null, null),
                        400,
                        new ErrorResponse("\"left_operand\" is not specified in request body")),

                arguments("TEST-59: Невалидное значение в поле \"Левый операнд\"",
                        new CalculateRequest("as", Operations.PLUS, new Random().nextInt(1000)),
                        400,
                        new ErrorResponse("\"left_operand\" must be instanced as integer value")),

                arguments("TEST-60: Невалидное значение в поле \"Правый операнд\"",
                        new CalculateRequest(new Random().nextInt(1000), Operations.MINUS, "test"),
                        400,
                        new ErrorResponse("\"right_operand\" must be instanced as integer value")),

                arguments("TEST-63: Невалидное значение в поле \"Операция\"",
                        new CalculateRequest(new Random().nextInt(1000), RND.getRandomString(1), new Random().nextInt(1000)),
                        400,
                        new ErrorResponse("\"operation\" value must be one of \"['-', '+', '/', '*']\"")),

                arguments("TEST-64: Пустое значение в поле \"Операция\"",
                        new CalculateRequest(new Random().nextInt(1000), "", new Random().nextInt(1000)),
                        400,
                        new ErrorResponse("\"operation\" value must be one of \"['-', '+', '/', '*']\"")),

                arguments("TEST-65: Деление на 0",
                        new CalculateRequest(new Random().nextInt(1000), Operations.DIVIDE, 0),
                        400,
                        new ErrorResponse("Ошибка! Деление на 0 запрещено."))

        );
    }
}
