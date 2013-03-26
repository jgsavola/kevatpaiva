package ohtu.kevatpaiva;
 
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * The persistent class for the article database table.
 *
 */
public class Article implements Serializable {
    
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
    
    private Set tags = new HashSet();
 
    public Article() {
 
    }
 
    public Article(String id, String author, String title, String year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public Set getTags() {
        return tags;
    }

    public void setTags(Set tags) {
        this.tags = tags;
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
        /**
         * FIXME: Kentät pitää koodata BibTeX-muotoon.
         */
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
