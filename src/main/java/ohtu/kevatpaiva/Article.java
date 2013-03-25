package ohtu.kevatpaiva;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * The persistent class for the article database table.
 *
 */
@Entity
@Table(name = "article")
public class Article {
    private Integer id;
    private String author;
    private String title;
    private int year;
 
    public Article() {
 
    }
 
    public Article(Integer id, String author, String title, int year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }
 
    @Id
    public Integer getId() {
        return this.id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getAuthor() {
        return this.author;
    }
 
    public void setAuthor(String author) {
        this.author = author;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
