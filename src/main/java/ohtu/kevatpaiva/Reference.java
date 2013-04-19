package ohtu.kevatpaiva;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * Sisältää kaikki BibTex-viittauksiin käytettävät kentät.
 *
 */
@Entity
@Table(name = "reference")
public class Reference {
    private String type;
    private String id;
    private String author;
    private String title;
    private String year;
    private String address;
    private String annote;
    private String booktitle;
    private String chapter;
    private String crossref;
    private String edition;
    private String editor;
    private String howpublished;
    private String institution;
    private String journal;
    private String key;
    private String month;
    private String note;
    private String number;
    private String organization;
    private String pages;
    private String publisher;
    private String school;
    private String series;
    private String volume;
    private String url;                     // nonstandard field

    public Reference() {
 
    }
 
    public Reference(String id, String author, String title, String year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }
    
    public Reference(String type, String id, String author, String title, String year) {
        this.type = type;
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }
    
    @Id
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAnnote() {
        return annote;
    }

    public void setAnnote(String annote) {
        this.annote = annote;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getCrossref() {
        return crossref;
    }

    public void setCrossref(String crossref) {
        this.crossref = crossref;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getHowpublished() {
        return howpublished;
    }

    public void setHowpublished(String howpublished) {
        this.howpublished = howpublished;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
 
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
 
    /**
     * Luo BibTeX-esitys artikkelista. Lopputuloksena pitäisi olla
     * merkkijono käyvässä BibTeX-formaatissa.
     *
     * @return Artikkelin BibTeX-esitys.
     */
    public String toBibTeX() {

        String bibtex = "@" + this.getType() + "{" + this.getId() + ",\n";

        if (this.getAuthor() != null) {
            bibtex += "    author = {" + Muunto.muunnaMuoto(this.getAuthor(), false) + "},\n";
        }
        if (this.getTitle() != null) {
            bibtex += "    title = {" + Muunto.muunnaMuoto(this.getTitle(), true) + "},\n";
        }
        if (this.getBooktitle() != null) {
            bibtex += "    booktitle = {" + Muunto.muunnaMuoto(this.getBooktitle(), false) + "},\n";
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
        if (this.getPages() != null) {
            bibtex += "    pages = {" + Muunto.muunnaMuoto(this.getPages(), false) + "},\n";
        }
        if (this.getYear() != null) {
            bibtex += "    year = {" + Muunto.muunnaMuoto(this.getYear(), false) + "},\n";
        }
        if (this.getMonth() != null) {
            bibtex += "    month = {" + Muunto.muunnaMuoto(this.getMonth(), false) + "},\n";
        }
        if (this.getAddress() != null) {
            bibtex += "    address = {" + Muunto.muunnaMuoto(this.getAddress(), false) + "},\n";
        }
        if (this.getAnnote() != null) {
            bibtex += "    annote = {" + Muunto.muunnaMuoto(this.getAnnote(), false) + "},\n";
        }
        if (this.getChapter() != null) {
            bibtex += "    chapter = {" + Muunto.muunnaMuoto(this.getChapter(), false) + "},\n";
        }
        if (this.getCrossref() != null) {
            bibtex += "    crossref = {" + Muunto.muunnaMuoto(this.getCrossref(), false) + "},\n";
        }
        if (this.getEdition() != null) {
            bibtex += "    edition = {" + Muunto.muunnaMuoto(this.getEdition(), false) + "},\n";
        }
        if (this.getEditor() != null) {
            bibtex += "    editor = {" + Muunto.muunnaMuoto(this.getEditor(), false) + "},\n";
        }
        if (this.getHowpublished() != null) {
            bibtex += "    howpublished = {" + Muunto.muunnaMuoto(this.getHowpublished(), false) + "},\n";
        }
        if (this.getInstitution() != null) {
            bibtex += "    institution = {" + Muunto.muunnaMuoto(this.getInstitution(), false) + "},\n";
        }
        if (this.getKey() != null) {
            bibtex += "    key = {" + Muunto.muunnaMuoto(this.getKey(), false) + "},\n";
        }
        if (this.getNote() != null) {
            bibtex += "    note = {" + Muunto.muunnaMuoto(this.getNote(), false) + "},\n";
        }
        if (this.getOrganization() != null) {
            bibtex += "    organization = {" + Muunto.muunnaMuoto(this.getOrganization(), false) + "},\n";
        }
        if (this.getPublisher() != null) {
            bibtex += "    publisher = {" + Muunto.muunnaMuoto(this.getPublisher(), false) + "},\n";
        }
        if (this.getSchool() != null) {
            bibtex += "    school = {" + Muunto.muunnaMuoto(this.getSchool(), false) + "},\n";
        }
        if (this.getSeries() != null) {
            bibtex += "    series = {" + Muunto.muunnaMuoto(this.getSeries(), false) + "},\n";
        }
        if (this.getUrl() != null) {
            bibtex += "    url = {" + Muunto.muunnaMuoto(this.getUrl(), false) + "},\n";
        }


        bibtex += "}\n";

        return bibtex;
    }

}
