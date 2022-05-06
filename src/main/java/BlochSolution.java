import java.time.Year;

/**
 * The following are some things to note:
 * <p>
 * The scope of the Book constructor has been changed to private, so that it cannot be accessed from the outside of the Book class.
 * This makes it impossible to create a Book instance directly. The object creation process is delegated to the Builder class.
 * The Book constructor takes a Builder instance as its only parameter, which contains all the values to be set by the Book constructor.
 * Alternatively, the Book constructor could take all the parameters corresponding to the Book fields,
 * but this would mean that you must deal again with many parameters to be set in the right order when you call the Book constructor
 * from the Builder’s build method.
 * Mixing up parameters of the same type is one of the potential issues developers try to avoid by implementing the Builder pattern.
 * The Builder class contains the same fields as the Book class, which is necessary to hold the values to be passed to the Book constructor.
 * This has often been rightly criticized as code duplication.
 * For every optional field to be set, the Builder class exposes a setter-like method,
 * which assigns the field’s value and returns the current Builder instance to build the object in a fluent way.
 * Since each method call returns the same Builder instance, method calls can be chained, which makes the client code more concise and readable.
 * The build method calls the Book constructor by passing the current Builder instance as the only parameter.
 * The values held by the Builder instance are then unpacked by the Book constructor, which assigns them to the corresponding Book fields.
 */
public class BlochSolution {

  public static void main(String[] args) {
    Book.Builder builder = new Book.Builder("0-12-345678-9", "Moby-Dick")
        .genre("Adventure")
        .author("Herman Melville")
        .published(Year.of(1990));


    //Create a first book
    Book book = builder.build();
    //Use same builder instance to create a new book, However this approach is not very elastic since you cant change the mandatory fields
    Book secondbook = builder.description("Some description").build();

  }

  private static class Book {
    private final String isbn;
    private final String title;
    private final String genre;
    private final String author;
    private final Year published;
    private final String description;

    public Book(Builder builder) {
      this.isbn = builder.isbn;
      this.title = builder.title;
      this.genre = builder.genre;
      this.author = builder.author;
      this.published = builder.published;
      this.description = builder.description;
    }


    public String getIsbn() {
      return isbn;
    }

    public String getTitle() {
      return title;
    }

    public String getGenre() {
      return genre;
    }

    public String getAuthor() {
      return author;
    }

    public Year getPublished() {
      return published;
    }

    public String getDescription() {
      return description;
    }

    public static class Builder {
      private final IsbnValidator isbnValidator = new IsbnValidator();
      private final String isbn;
      private final String title;
      private String genre;
      private String author;
      private Year published;
      private String description;

      public Builder(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
      }

      public Builder genre(String genre) {
        this.genre = genre;
        return this;
      }

      public Builder author(String author) {
        this.author = author;
        return this;
      }

      public Builder published(Year published) {
        this.published = published;
        return this;
      }

      public Builder description(String description) {
        this.description = description;
        return this;
      }

      public Book build() {
        validate();
        return new Book(this);
      }

      private void validate() throws IllegalStateException {
        StringBuilder mb = new StringBuilder();
        if (isbn == null) {
          mb.append("ISBN must not be null.");
        } else if (!isbnValidator.isValid(isbn)) {
          mb.append("Invalid ISBN!");
        }
        if (title == null) {
          mb.append("Title must not be null.");
        } else if (title.length() < 2) {
          mb.append("Title must have at least 2 characters.");
        } else if (title.length() > 100) {
          mb.append("Title cannot have more than 100 characters.");
        }
        if (author != null && author.length() > 50) {
          mb.append("Author cannot have more than 50 characters.");
        }
        if (published != null && published.isAfter(Year.now())) {
          mb.append("Year published cannot be greater than current year.");
        }
        if (description != null && description.length() > 500) {
          mb.append("Description cannot have more than 500 characters.");
        }
        if (mb.length() > 0) {
          throw new IllegalStateException(mb.toString());
        }
      }
    }

    private static class IsbnValidator {
      public boolean isValid(String isbn) {
        return true;
      }
    }
  }
}
