import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Task2 {

    static class AuthorBuilder {

        private final List<Author> authors = new ArrayList<>();
        private List<Book> books = new ArrayList<>();

        private List<Book> getBooks() {
            return books;
        }

        BookBuilder author(String authorName, short authorAge) {
            Author author = new Author(authorName, authorAge);
            Optional<Author> optional = authors.stream().filter(author1 -> author1.equals(author)).findFirst();
            if (optional.isEmpty()) {
                authors.add(author);
            }
            return new BookBuilder(author, this);
        }

        void provide(BiConsumer<List<Author>, List<Book>> consumer) {
            consumer.accept(new ArrayList<>(authors), new ArrayList<>(books));
        }

        static class BookBuilder {

            private Author author;
            private final AuthorBuilder authorBuilder;

            BookBuilder(Author author, AuthorBuilder authorBuilder) {
                this.author = author;
                this.authorBuilder = authorBuilder;
            }

            BookBuilder book(String title, int numberOfPages) {
                Book book = new Book(title, numberOfPages);
                Optional<Book> optional = authorBuilder.getBooks().stream().filter(book1 -> book1.equals(book)).findFirst();
                if (optional.isEmpty()) {
                    book.addAuthor(author);
                    authorBuilder.getBooks().add(book);
                } else {
                    optional.get().addAuthor(author);
                }
                return this;
            }

            BookBuilder author(String authorName, short authorAge) {
                return authorBuilder.author(authorName, authorAge);
            }

            void provide(BiConsumer<List<Author>, List<Book>> consumer) {
                authorBuilder.provide(consumer);
            }

        }

    }


    static class Second {

        private static BiConsumer<List<Author>, List<Book>> consumer = (authors, books) -> {
            System.out.println(authors);
            System.out.println(books);

            System.out.println("\nAuthors:");
            authors.forEach(author -> System.out.printf("%n%s - %s", author, author.getBooks()));
            System.out.println("\n\nBooks:");
            books.forEach(book -> System.out.printf("%n%s - %s", book, book.getAuthors()));
        };

        static AuthorBuilder.BookBuilder arrays = new AuthorBuilder()
                .author("Bexby, C.", (short) 22).book("Referencing and plagiarism.", 90)
                .author("Nigel, E.", (short) 22).book("Referencing and plagiarism.", 90)
                .author("Smith, K.", (short) 22).book("Referencing and plagiarism.", 90)

                .author("Taras Shevchenko", (short) 62)
                .book("Kobzar", 300)
                .book("Zapovit", 150)

                .author("Gogol", (short) 42)
                .book("Dead Souls", 453)

                .author("Rudyard Kipling", (short) 70)
                .book("The Story of the Gadsbys", 453)
                .book("Many Inventions ", 545)
                .book("Rikki-Tikki-Tavi", 100);

        public static void main(String[] args) {
            System.out.printf("%nTask2.2%n");
            arrays.provide(consumer);
        }

    }


    static class Third {

        private static BiConsumer<List<Author>, List<Book>> consumer = (authors, books) -> {

            System.out.println("check if all book(s) have more than 200 pages = " + books.stream().allMatch(book -> book.getNumberOfPages() > 200));
            System.out.println("check if some book(s) have more than 200 pages = " + books.stream().anyMatch(book -> book.getNumberOfPages() > 200));

            System.out.println("find the books with min number of pages = " + books.stream().min(Comparator.comparingInt(Book::getNumberOfPages)).get());
            System.out.println("find the books with max number of pages = " + books.stream().max(Comparator.comparingInt(Book::getNumberOfPages)).get());
            System.out.println("filter books with only single author = " + books.stream().filter(book -> book.getAuthors().size() == 1).collect(Collectors.toList()));

            books.sort(Comparator.comparingInt(Book::getNumberOfPages));
            System.out.printf("%nSort by numberOfPages: %s",books);

            books.sort(Comparator.comparing(Book::getTitle));
            System.out.printf("%nSort by title: %s",books);

            System.out.println("\nget list of all titles = " + books.stream().map(Book::getTitle).collect(Collectors.joining(", ")));
            System.out.println("print them using forEach method:");
            books.stream().map(Book::getTitle).forEach(System.out::println);

            System.out.println("get distinct list of all authors = " + authors.stream().distinct().collect(Collectors.toList()));
        };


        public static void main(String[] args) {
            System.out.printf("%nTask2.3%n");
            Second.arrays.provide(consumer);
        }

    }


}
