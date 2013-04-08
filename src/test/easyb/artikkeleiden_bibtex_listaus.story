import ohtu.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User gets a list of articles in bibtex form'

scenario "user can see the articles in bibtex form", {
    given 'user has inserted an article', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/");   
        element = driver.findElement(By.name("id"));
        element.sendKeys("RRR03");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Anthony Robins and Janet Rountree and Nathan Rountree");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Learning and teaching programming: A review and discussion");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2003");
        element = driver.findElement(By.name("form-submit"));
        element.submit();
    }

    when 'user clicks BibTex-button', {
        element = driver.findElement(By.name("bibtex"));
        element.submit();
    }
 
    then 'articles will be listed', {
        driver.getPageSource().contains("Artikkelit BibTeX-muodossa").shouldBe true
    }
}