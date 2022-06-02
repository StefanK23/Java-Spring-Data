package PROJECT;

import PROJECT.entities.Author;
import PROJECT.entities.Book;
import PROJECT.repositories.AuthorRepository;
import PROJECT.repositories.BookRepository;
import PROJECT.services.BookService;
import PROJECT.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {



    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    @Autowired
    public ConsoleRunner( BookRepository bookRepository, AuthorRepository authorRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

    }

    private void _01_booksAfter2000() {
        LocalDate year2000 = LocalDate.of(2000,12,31);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);
        books.forEach(b -> System.out.println(b.getTitle()));

        int count = this.bookRepository.countByReleaseDateAfter(year2000);
        System.out.println("Total count: " + count);
    }

    private void _02_allAuthorsWithBookBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);

        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _03_allAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();

        authors
                .stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(author ->
                        System.out.printf("%s %s -> %d%n",
                                author.getFirstName(),
                                author.getLastName(),
                                author.getBooks().size()));
    }

    private void _04_BookOfAuthorWithGivenName() {

        List<Book> books = this.bookRepository.getAllBooksOfAuthorWithGivenName();
            books.stream()
                    .sorted((b1,b2) -> {
                        int sorted = b2.getReleaseDate().compareTo(b1.getReleaseDate());
                        if(sorted ==0 ){
                            sorted = b1.getTitle().compareTo(b2.getTitle());
                        }
                        return  sorted;
                    })
                    .forEach(book -> System.out.printf("Book title: %s%nRelease date: %s%nCopies: %d",
                            book.getTitle(),book.getReleaseDate(),book.getCopies()));
    }





    @Override
    public void run(String... args) throws Exception {

        //this.seedService.seedAll();

        //this._01_booksAfter2000();

        //this._02_allAuthorsWithBookBefore1990();

        //this._03_allAuthorsOrderedByBookCount();

        this._04_BookOfAuthorWithGivenName();



    }
}
