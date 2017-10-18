/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author Jeremy Santorelli
 */
public interface IAuthorDao {

    List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;
      public int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException;
      public int addAuthor(List<Object> colValues) throws ClassNotFoundException, SQLException;
      public int updateAuthor(List<Object> colValues, Object pkValue) 
            throws ClassNotFoundException, SQLException;
      
}
