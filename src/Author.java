import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 1.	(1 point) Create the following classes:
 * a.	Author with fields:
 * i.	String name
 * ii.	short age
 * iii.	List<Book> books
 */
public class Author {
    private final String name;
    private final short age;
    private final List<Book> books = new ArrayList<>();

    public Author(String name, short age) {
        this.name = name;
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }

    public short getAge() {
        return age;
    }

    public void addBook(Book book){
        books.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return age == author.age &&
                Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Author{" +
                "'" + name + '\'' +
                ", " + age +
                '}';
    }
}
