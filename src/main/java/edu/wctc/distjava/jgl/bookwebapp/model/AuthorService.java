package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jerem
 */
public final class AuthorService {

    private IAuthorDao authorDAO;

    public AuthorService(IAuthorDao authorDAO) {
        setAuthorDAO(authorDAO);
    }

    public IAuthorDao getAuthorDAO() {
        return authorDAO;
    }

    public void setAuthorDAO(IAuthorDao authorDAO) {
        this.authorDAO = authorDAO;
    }

    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {

        return authorDAO.getListOfAuthors();

    }

    //Integration Service
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService as = new AuthorService(new AuthorDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book",
                "root", "", new MySqlDataAccess()));

        List<Author> list = as.getAuthorList();

        for (Author rec : list) {
            System.out.println(rec.getAuthorId() + ", " + rec.getAuthorName() + ", " + rec.getDateAdded() + "\n");
        }

    }
}
