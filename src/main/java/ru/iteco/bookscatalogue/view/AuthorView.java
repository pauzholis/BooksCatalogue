package ru.iteco.bookscatalogue.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс - представление автора
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorView {

    /**
     * Идентификатор записи
     */
    public Long id;

    /**
     * Фамилия
     */
    public String lastName;

    /**
     * Имя
     */
    public String firstName;

    /**
     * Отчество
     */
    public String middleName;
}
