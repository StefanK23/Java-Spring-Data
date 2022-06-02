package PROJECT.services;

import PROJECT.entities.Book;

import java.io.IOException;
import java.util.List;

public interface SeedService {
    void seedAuthors() throws IOException;

    void seedCategories() throws IOException;

    void seedBooks() throws IOException;

   default void seedAll() throws IOException {
    seedAuthors();
    seedCategories();
    seedBooks();
   }



}
