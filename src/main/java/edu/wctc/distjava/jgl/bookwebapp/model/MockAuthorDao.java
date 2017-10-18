package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;

import java.util.*;
import javax.servlet.ServletException;

/**
 *
 * @author jerem
 */
public final class MockAuthorDao implements IAuthorDao {

    public MockAuthorDao() {

    }

    @Override
    public List<Author> getListOfAuthors()
            throws SQLException, ClassNotFoundException {

        List<Author> list = Arrays.asList(
                new Author(1, "a", new Date()),
                new Author(2, "b", new Date())
        );

        return list;
    }

    //Unit TESTING
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//       
//        IAuthorDao adao = new MockAuthorDao();
//           List<Author> list = adao.getListOfAuthors();
//        
//        for(Author rec : list) {
//            System.out.println(rec.getAuthorId() + ", " + rec.getAuthorName() + ", " + rec.getDateAdded() + "\n");
//        }
//
//    }

    @Override
    public int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException {
      return 1;
    }

    @Override
    public int addAuthor(List<Object> colValues) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateAuthor(List<Object> colValues, Object pkValue) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
