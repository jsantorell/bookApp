/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.modelCRUD;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jeremy Santorelli
 */
public interface IAuthorDao {

    List<Author> getListOfAuthors(String query) throws SQLException, ClassNotFoundException;
    public int getRowsAffected(String query) throws SQLException, ClassNotFoundException;
    
}
