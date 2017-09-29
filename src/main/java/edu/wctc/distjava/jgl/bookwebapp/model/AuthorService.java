package edu.wctc.distjava.jgl.bookwebapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jerem
 */
public class AuthorService {
    
    public List<Author> getAuthorList() {
        return Arrays.asList(
                new Author(1,"Mark Twain",new Date()),
                new Author(2,"Stephen King",new Date()),
                new Author(3,"George Orwell",new Date())
        );
    }
}
