package edu.wctc.distjava.jgl.bookwebapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AuthorService implements Serializable {//Allows server to use ejb's more efficiently "Serializable"

    /*
    Serializable serializes an ejb as a binary file when ram is needed. and 
    deserializes it when it needs to use the ejb. 
     */
    private static final long serialVersionUID = 1L;//A Default value

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Author> getAuthorSearch(String search) {

        return getEm().createQuery(
                "SELECT a FROM Author a WHERE a.authorName LIKE :search")
                .setParameter("search", "%" + search + "%")
                .getResultList();
    }

    public int DeleteAuthor(String id) throws Exception {
        
        int authorId = Integer.parseInt(id);
        Author deleteAuthor = getEm().find(Author.class, authorId);
        getEm().remove(deleteAuthor);
        return 1;

    }

    public List<Author> getAuthorList() throws Exception {

        return getEm().createQuery(
                "SELECT a FROM Author a")
                .getResultList();
    }

    public int addAuthor(String columnValues) throws Exception {

        Date date = new Date();
        Author newAuthor = new Author();
        newAuthor.setAuthorName(columnValues);
        newAuthor.setDateAdded(date);
        getEm().persist(newAuthor);
        return 1;

    }

    public int updateAuthor(String colValues, Object pkValue) throws Exception {

        int authorId = Integer.parseInt(pkValue.toString());
        Author updateAuthor = getEm().find(Author.class, authorId);
        updateAuthor.setAuthorName(colValues);
        getEm().merge(updateAuthor);
        getEm().persist(updateAuthor);
        return 1;
    }

//    Integration Service
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        AuthorService as = new AuthorService(new AuthorDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book",
//                "root", "", new MySqlDataAccess()));
//
//        List<Author> list = as.getAuthorList();
//
//        for (Author rec : list) {
//            System.out.println(rec.getAuthorId() + ", " + rec.getAuthorName() + ", " + rec.getDateAdded() + "\n");
//        }
//    }
}
