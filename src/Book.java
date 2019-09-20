import java.util.*;

/**
 * b.	Book with fields
 * i.	String title
 * ii.	List<Author> authors
 * iii.	int numberOfPages
 */
public class Book {
    private final String title;
    private final Set<Author> authors = new HashSet<>();
    private final int numberOfPages;

    public Book(String title, int numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.addBook(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numberOfPages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "'" + title + '\'' +
                ", " + numberOfPages +
                '}';
    }
}
