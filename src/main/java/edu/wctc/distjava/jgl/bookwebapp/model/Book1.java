/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeremy Santorelli
 */
@Entity
@Table(name = "book1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book1.findAll", query = "SELECT b FROM Book1 b")
    , @NamedQuery(name = "Book1.findByBookId", query = "SELECT b FROM Book1 b WHERE b.bookId = :bookId")
    , @NamedQuery(name = "Book1.findByTitle", query = "SELECT b FROM Book1 b WHERE b.title = :title")
    , @NamedQuery(name = "Book1.findByIsbn", query = "SELECT b FROM Book1 b WHERE b.isbn = :isbn")
    , @NamedQuery(name = "Book1.findByAuthorId", query = "SELECT b FROM Book1 b WHERE b.authorId = :authorId")})
public class Book1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "book_id")
    private Integer bookId;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 25)
    @Column(name = "isbn")
    private String isbn;
    @JoinColumn(name = "author_id",
            referencedColumnName = "author_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Author authorId;

    public Book1() {
    }

    public Book1(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book1)) {
            return false;
        }
        Book1 other = (Book1) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.wctc.distjava.jgl.bookwebapp.model.Book1[ bookId=" + bookId + " ]";
    }

}
