package ru.iteco.bookscatalogue.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс - представление книги
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookView {

    /**
     * Идентификатор записи
     */
    public Long id;

    /**
     * Название
     */
    public String name;
}
