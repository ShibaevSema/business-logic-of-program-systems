package backend.exceptions;

import backend.exceptions.dto.ErrorDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public enum ErrorEnum {
    AUTH_PASSWORD_ERROR(400,"Введён некорректный пароль"),
    AUTH_LOGIN_ERROR(400,"Введён некорректный логин"),
    UNAUTHORIZED_ACCESS(403,"Неавторизованный доступ"),
    ACCESS_DENIED(403,"Недостаточно прав для доступа к ресурсу"),
    UNKNOWN_ERROR(500,"Неизвестная ошибка сервера"),
    SERVICE_UNAVAILABLE(503, "Сервис недоступен"),
    UNAUTHORIZED_EXCEPTION(403,"Неверные учетные данные пользователя"),
    SERVICE_DATA_BASE_EXCEPTION(500,"Ошибка при сохранении в базу данных"),
    SERVICE_SAVING_PHOTO_EXCEPTION(500,"Ошибка при сохранении в фотографии"),

    BAD_REQUEST_REGISTRATION(400,"Некорректные данные - поля не должны быть пустыми и email должен быть минимум 10 символов"),

    OBJECT_DOES_NOT_EXIST(404, "Объект не был найден"),

    NOT_UNIQUE_NAME(400,"Имя не уникально, а должно быть"),

    EMPTY_FILE(400,"Файл пустой"),

    TOO_BIG_FILE(400,"Файл слишком большой"),

    PHOTO_NOT_UPLOADED(400,"Фотография не была добавлена к pin"),
    NULL_BOARD_NAME(400,"Имя доски не может быть пустым");
    /**
     * Сообщение ошибки.
     */


    private final Integer code;
    private final String message;

    public void throwIfFalse(Boolean condition) {
        if (!condition) {
            throw exception();
        }
    }

    public ApplicationException exception() {
        return new ApplicationException(createApplicationError());
    }

    public ErrorDto createApplicationError() {
        return ErrorDto.of(this.code,this.message, LocalDateTime.now());
    }

    public void throwException() throws ApplicationException {
        throw exception();
    }
}
