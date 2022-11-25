package ru.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Основной класс приложения
 */
@SpringBootApplication
public class Main {
    /**
     * Точка входа в приложение.
     *
     * @param args аргументы переданные из командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
