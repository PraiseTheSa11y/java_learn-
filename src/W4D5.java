import java.util.ArrayList;
import java.util.Scanner;

public class W4D5 {
    static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            FilmManager filmManager = new FilmManager();
            // Фильмы захаркожены чтобы было легче тестить
            // Для добавления фильма в пустой список есть метод в меню отдельный
            Film Odyssey = new Film("Одиссея", "Кристофер Нолан", Genre.ACTION, 8);
            Film SpiderMan = new Film("Человек-Паук", "Сэм Рэйми", Genre.ACTION, 10);
            Film ShutterIsland = new Film("Остров проклятых", "Мартин Скорсезе", Genre.THRILLER, 9);

            filmManager.addFilm(Odyssey);
            filmManager.addFilm(SpiderMan);
            filmManager.addFilm(ShutterIsland);


            filmManager.printMenu(sc);

    }
}
class Film {
    private String title;
    private String director;
    private Genre genre;
    private int rating;
    Film(String title, String director, Genre genre, int rating) {
        if(title != null && director != null && genre != null && rating >= 0 && rating <= 10) {
            this.title = title;
            this.director = director;
            this.genre = genre;
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Некорректные параметры фильма");
        }
    }
    public String getTitle() {
        return title;
    }
    public String getDirector() {
        return director;
    }
    public Genre getGenre() {
        return genre;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int newRating) {
        if(newRating >= 0 && newRating <= 10) {
            this.rating = newRating;
        }
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
class FilmManager {
    private final ArrayList<Film> films = new ArrayList<Film>();

    public void addFilm(Film film) {
        if(film != null) {
            films.add(film);
        }
    }
    public void removeFilm(Film film) {
        if(film != null) {
            films.remove(film);
        }
    }
    public void printMenu(Scanner sc) {
        while(true) {
            System.out.println("== МЕНЮ ==");
            System.out.println(" 1.Добавить фильм \n 2.Показать все фильмы \n 3.Найти фильм по названию \n 4.Изменить оценку \n 5.Изменить жанр \n 6.Проверить есть ли фильм в коллекции \n 7.Показать количество фильмов с высоким рейтингом");
            int userInput = sc.nextInt();
            sc.nextLine();
            switch (userInput) {
                case 1:
                    addFilmToCollection(sc);
                    break;
                case 2:
                    printFilms();
                    break;
                case 3:
                    findFilm(sc);
                    break;
                case 4:
                    changeRating(sc);
                    break;
                case 5:
                    changeGenre(sc);
                    break;
                case 6:
                    if(hasFilm(sc)) {
                        System.out.println("Фильм с таким названием или режиссёром есть в каталоге");
                    }
                    break;
                case 7:
                    System.out.println("Количество фильмов с рейтингом больше 8: " + countHighRankedFilms());
                    break;
            }
        }

    }
    public void printFilms() {
        for (Film film : films) {
            System.out.println("Название: " + film.getTitle() + "\n" + "Режиссёр: " + film.getDirector() + "\n" + "Жанр: " + film.getGenre().getGenreRus() + "\n" + "Рейтинг: " + film.getRating());
            System.out.println("--------------");
        }
    }
    public void addFilmToCollection(Scanner sc) {
        System.out.println("Введите название фильма: ");
        String title = sc.nextLine();
        System.out.println("Введите имя режиссёра: ");
        String director = sc.nextLine();
        Genre userGenre = null;
        while (true) {
            System.out.println("Выберите жанр: ");
            for (Genre genre : Genre.values()) {
                System.out.println(genre.getGenreRus());
            }
            boolean found = false;
            String userChoiceGenre = sc.nextLine();
            for (Genre searchGenre : Genre.values()) {
                if (searchGenre.getGenreRus().equalsIgnoreCase(userChoiceGenre)) {
                    userGenre = searchGenre;
                    found = true;
                }
            }
            if (found) {
                break;
            }
            if (!found) {
                System.out.println("Введите корректный жанр");
            }
        }
        System.out.println("Введите рейтинг: ");
        int userRating = 0;
        userRating = sc.nextInt();
        Film userFilm = new Film(title, director, userGenre, userRating);
        addFilm(userFilm);
    }
    public void findFilm(Scanner sc) {
        System.out.println(" == Поиск фильма ==");
        while (true) {
            System.out.println("Введите слово для поиска: ");
            String userTarget = sc.nextLine();
            Film userFilm = filmContains(userTarget);
            if (userFilm != null) {
                System.out.println("Результат поиска: " + userFilm.getTitle() + " " + userFilm.getDirector());
                break;
            } else {
                System.out.println("Ничего не найдено, попробуйте ещё раз");
            }
        }

    }
    public Film filmContains(String target) {

        String targetToLower = target.toLowerCase();
        for (Film film : films) {
            String titleToLower = film.getTitle().toLowerCase();
            String directorToLower = film.getDirector().toLowerCase();
            if (titleToLower.contains(targetToLower) || directorToLower.contains(targetToLower)) {
                return film;
            }
        }
        return null;
    }
    public void changeRating(Scanner sc) {
        System.out.println("== Изменение оценки ==");
        System.out.println("Выберите фильм: ");
        printFilms();
        String userFilmTitle = sc.nextLine();
        System.out.println("Введите новую оценку: ");
        int newUserRating = sc.nextInt();
        boolean changed = false;
        for(Film film : films) {
            if (film.getTitle().equalsIgnoreCase(userFilmTitle)) {
                film.setRating(newUserRating);
                changed = true;
            }
        }
        if(changed) {
            System.out.println("Оценка успешно изменена");
        } else {
            System.out.println("Не удалось поменять оценку попробуйте ещё раз");
        }
    }
    public void changeGenre(Scanner sc) {
        Genre selectedGenre;
        System.out.println("== Изменение жанра ==");
        if (!films.isEmpty()) {
            System.out.println("Выберите фильм от 1 до " + (films.size()));
            printFilms();
            int userChoice = sc.nextInt();
            if(userChoice >= 1 && userChoice <= (films.size())) {
                Film userFilm = films.get(userChoice - 1);
                System.out.println("Выберите жанр: ");
                int i = 1;
                for (Genre genre : Genre.values()) {
                    System.out.println(i + "." + genre.getGenreRus());
                    i++;
                }
                userChoice = sc.nextInt();
                if (userChoice >= 1 && userChoice <= 6) {
                    switch (userChoice) {
                        case 1 -> selectedGenre = Genre.COMEDY;
                        case 2 -> selectedGenre = Genre.ACTION;
                        case 3 -> selectedGenre = Genre.HORROR;
                        case 4 -> selectedGenre = Genre.DRAMA;
                        case 5 -> selectedGenre = Genre.FANTASY;
                        case 6 -> selectedGenre = Genre.THRILLER;
                        default -> selectedGenre = Genre.ACTION;
                    }
                    userFilm.setGenre(selectedGenre);
                    System.out.println("Жанр успешно изменён");
                }
            }
        }
    }
    public boolean hasFilm(Scanner sc) {
        if (!films.isEmpty()) {
            System.out.println("Введите название или режиссёра для проверки наличия в каталоге");
            String userInput = sc.nextLine();
            for (Film film : films) {
                if (film.getTitle().contains(userInput) || film.getDirector().contains(userInput)) {
                    return true;
                }
            }
        }
            return false;
    }
    public int countHighRankedFilms() {
        int counter = 0;
        for (Film film : films) {
            if (film.getRating() >= 8) {
                counter++;
            }
        } return counter;
    }
}
enum Genre {
    COMEDY("Комедия"),
    ACTION("Экшен"),
    HORROR("Хоррор"),
    DRAMA("Драма"),
    FANTASY("Фэнтези"),
    THRILLER("Триллер");

    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }
    public String getGenreRus() {
        return genre;
    }
}

