/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jeremy Santorelli
 */
@Stateless
public class BookFacade extends AbstractFacade<Book1> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book1.class);
    }

    public List<Book1> findAllByAuthorId(int authorId) {

        TypedQuery<Book1> query
                = em.createNamedQuery("Book1.findByAuthorId", Book1.class);
        Author author = em.find(Author.class, authorId);
        query.setParameter("authorId", author);
        return query.getResultList();
    }
    
    public Author getAuthorFromBook(int authorId){
    
        return em.find(Author.class, authorId);
    
    }
    
     


    


}
