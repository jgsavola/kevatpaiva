import ohtu.*
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select

description 'User gets a copy-paste list of articles in bibtex form'

scenario "user can see the articles in bibtex form starting from insertion", {

    given 'user has inserted an article', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake")
        tallentaja = new ArtikkelinTallentaja()
        tallentaja.poistaViite("RRR03")

        element = driver.findElement(By.name("type"))
        select = new Select(element)
        select.selectByValue("article")

        element = driver.findElement(By.name("id"));
        element.sendKeys("RRR03");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Anthony Robins and Janet Rountree and Nathan Rountree");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Learning and teaching programming: A review and discussion");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("Pakollinen journaali!");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2003");
        element = driver.findElement(By.name("form-submit"));
        element.submit();
    }

    when 'user clicks BibTex-link', {
        element = driver.findElement(By.partialLinkText("BibTex-muodossa"))
        element.click()
    }
 
    then 'articles will be listed', {
    }

}

scenario "user can see the articles in copy-paste bibtex form starting from the main page", {

    given 'user is on the main page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/")
    }

    when 'user clicks BibTex-link', {
        element = driver.findElement(By.partialLinkText("BibTex-muodossa"))
        element.click()
    }
 
    then 'articles will be listed', {
    }

}

scenario "user can see the articles in copy-paste bibtex form starting from the insertion page", {

    given 'user is on the lomake page', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080/miniprojekti/lomake/")
    }

    when 'user clicks BibTex-link', {
        element = driver.findElement(By.partialLinkText("BibTex-muodossa"))
        element.click()
        System.out.println("== tulostetaan tulosivun koodi ==");
        System.out.println( driver.getPageSource() );
    }

    then 'articles will be listed', {
    }
}

scenario "user can see the articles in copy-paste bibtex form starting from the reference list", {

    given 'user is on the reference list page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/listaa")
    }

    when 'user clicks BibTex-link', {
        element = driver.findElement(By.partialLinkText("BibTex-muodossa"))
        element.click()
    }

    then 'articles will be listed', {
    }

}
