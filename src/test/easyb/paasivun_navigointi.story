import ohtu.*
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select

description 'User gets a list of articles'

scenario "user can see the main page", {

    given 'user starts the program', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/")
    }

    when 'user gives the address of the program'
 
    then 'the main page will be shown', {
        driver.getPageSource().contains("viitteiden hallinta").shouldBe true
    }

}

scenario "user can navigate from the main page to the insertion page", {

    given 'user is on the main page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/")
    }

    when 'user clicks the link for inserting references', {
        element = driver.findElement(By.linkText("Lis\u00e4\u00e4 uusi viite"))
        element.click()
    }
 
    then 'the insertion page will be shown', {
        driver.getPageSource().contains("form-article.jsp").shouldBe true
    }

}

scenario "user can navigate from the main page to the reference list", {

    given 'user is on the main page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/")
    }

    when 'user clicks the reference list link', {
        element = driver.findElement(By.linkText("Tarkastele viitteit\u00e4"))
        element.click()
    }
 
    then 'the reference list will be shown', {
        driver.getPageSource().contains("Artikkeliviitteet").shouldBe true
    }

}

scenario "user can navigate from the main page to the bibtex list", {

    given 'user is on the main page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/")
    }

    when 'user clicks bibtex link', {
        element = driver.findElement(By.partialLinkText("BibTex-muodossa"))
        element.click()
    }
 
    then 'the bibtex list will be shown', {
        System.out.println( driver.getPageSource() );
        driver.getPageSource().contains("bibtex.jsp").shouldBe true
    }

}
