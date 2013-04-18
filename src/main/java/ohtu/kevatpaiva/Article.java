package ohtu.kevatpaiva;
 
import java.io.Serializable;
import javax.persistence.Entity;
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
    private String volume;
    private String number;
    private String year;
    private String pages;
    private String publisher;
    private String address;
 
    public Article() {
 
    }
 
    public Article(String id, String author, String title, String year) {
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
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

        String bibtex = "@article{" + this.getId() + ",\n";

        if (this.getAuthor() != null) {
            bibtex += "    author = {" + Muunto.muunnaMuoto(this.getAuthor(), false) + "},\n";
        }
        if (this.getTitle() != null) {
            bibtex += "    title = {" + Muunto.muunnaMuoto(this.getTitle(), true) + "},\n";
        }
        if (this.getJournal() != null) {
            bibtex += "    journal = {" + Muunto.muunnaMuoto(this.getJournal(), false) + "},\n";
        }
        if (this.getVolume() != null) {
            bibtex += "    volume = {" + Muunto.muunnaMuoto(this.getVolume(), false) + "},\n";
        }
        if (this.getNumber() != null) {
            bibtex += "    number = {" + Muunto.muunnaMuoto(this.getNumber(), false) + "},\n";
        }
        if (this.getYear() != null) {
            bibtex += "    year = {" + Muunto.muunnaMuoto(this.getYear(), false) + "},\n";
        }
        if (this.getPages() != null) {
            bibtex += "    pages = {" + Muunto.muunnaMuoto(this.getPages(), false) + "},\n";
        }
        if (this.getPublisher() != null && this.getPublisher() != "") {
            bibtex += "    publisher = {" + Muunto.muunnaMuoto(this.getPublisher(), false) + "},\n";
        }
        if (this.getAddress() != null) {
            bibtex += "    address = {" + Muunto.muunnaMuoto(this.getAddress(), false) + "},\n";
        } 
       
        bibtex += "}\n";

        return bibtex;
    }
}
