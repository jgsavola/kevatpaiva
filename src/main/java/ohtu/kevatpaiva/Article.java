package ohtu.kevatpaiva;
 
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * The persistent class for the article database table.
 *
 */
@Entity
@Table(name = "article")
public class Article implements Serializable {
    
    @Id
    private String id;
    private String author;
    private String title;
    private String journal;
    private int volume;
    private int number;
    private int year;
    private String pages;
    private String publisher;
    private String address;
 
    public Article() {
 
    }
 
    public Article(String id, String author, String title, int year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }
 
    
    public String getId() {
        return this.id;
    }
 
    public void setId(String id) {
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

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Luo BibTeX-esitys artikkelista. Lopputuloksena pitäisi olla
     * merkkijono käyvässä BibTeX-formaatissa.
     *
     * @return Artikkelin BibTeX-esitys.
     */
    public String toBibTeX() {
        /**
         * FIXME: Kentät pitää koodata BibTeX-muotoon.
         */
        String bibtex = "@article{" + this.getId() + ",\n";

        if (this.getAuthor() != null) {
            bibtex += "    author = {" + this.getAuthor() + "},\n";
        }
        if (this.getTitle() != null) {
            bibtex += "    title = {" + this.getTitle() + "},\n";
        }
        if (this.getJournal() != null) {
            bibtex += "    journal = {" + this.getJournal() + "},\n";
        }
        /**
         * FIXME: volume, number, year ovat tyyppiä int, joten ne eivät
         * voi olla null. Siis niistä tulee käytännössä pakollisia kenttä.
         */
        bibtex += "    volume = {" + this.getVolume() + "},\n";
        bibtex += "    number = {" + this.getNumber() + "},\n";
        bibtex += "    year = {" + this.getYear() + "}\n";

        bibtex += "}\n";

        return bibtex;
    }
}
