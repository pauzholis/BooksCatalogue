package ru.iteco.bookscatalogue.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс описывающий поля при добавлении новой книги
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookForm {
    /**
     * Наименование книги
     */
    public String bookName;
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
