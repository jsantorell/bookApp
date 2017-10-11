/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.modelCRUD.DAO;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jeremy Santorelli
 */
public interface IAuthorDao {

    List<Author> getListOfAuthors(String query) throws SQLException, ClassNotFoundException;

    public int insertAuthor(String query, List<List<String>> dataSets) throws SQLException, ClassNotFoundException;

    public int updateAuthor(String query, Object value) throws SQLException, ClassNotFoundException;

    public int deleteAuthor(String query) throws SQLException, ClassNotFoundException;

    public List<String> getStringOfCols();

}
