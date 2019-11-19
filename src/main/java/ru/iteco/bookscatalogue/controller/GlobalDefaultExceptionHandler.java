package ru.iteco.bookscatalogue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Глобальная обработка исключений
 */
@ControllerAdvice
class GlobalDefaultExceptionHandler {

    private static final String DEFAULT_ERROR_VIEW = "error";

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Логирование исключения. Возвращение страницы ошибки.
     *
     * @param req запрос
     * @param e   исключение
     * @return страница ошибки
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView
    defaultErrorHandler(HttpServletRequest req, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        log.error("{}", e.getMessage(), e);
        return mav;
    }
}