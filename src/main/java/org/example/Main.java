package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//На вход получаем список названий книг.
//Распределяем книги по полкам так, чтобы на каждой полке было примерно одинаковое количество книг.
//Все книги должны быть отсортированы по алфавиту с первой до последней полки.
//Количество полок константное — 5 штук.
//Вернуть книги, распределенные по полкам.


public class Main {
    public static void main(String[] args) {

        List<String> books = new ArrayList<>();
        books.add("Война и мир");
        books.add("Улисс");
        books.add("Лолита");
        books.add("Звук и ярость");
        books.add("Человек-невидимка");
        books.add("На маяк");
        books.add("Гордость и предубеждение");
        books.add("Божественная комедия");
        books.add("Мидлмарч");
        books.add("Путешествия Гулливера");
        books.add("Распад");

        List<ArrayList<String>> ret = getShelvesOfBooks(books);

        ret.forEach(System.out::println);
    }

    private static List<ArrayList<String>> getShelvesOfBooks(List<String> books) {

        books = books.stream()
                .sorted()
                .collect(Collectors.toList());

        List<ArrayList<String>> shelves = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ArrayList<String> shelf = new ArrayList<>();
            shelves.add(shelf);
        }

        int booksInd = 0;
        int shelfInd = 0;

        while (booksInd < books.size()) {
            int perShelf = getBooksPerShelf(books.size() - booksInd, shelves.size() - shelfInd);
            for (int i = 0; i < perShelf; i++) {
                shelves.get(shelfInd).add(books.get(booksInd));
                booksInd++;
            }
            shelfInd++;
        }
        return shelves;
    }


    private static int getBooksPerShelf(int books, int shelves) {
        int perShelf = books / shelves;
        if (perShelf * shelves < books)
            perShelf++;
        return perShelf;
    }
}
