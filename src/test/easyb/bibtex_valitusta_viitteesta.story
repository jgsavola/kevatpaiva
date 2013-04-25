import ohtu.*
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select

description 'User gets a chosen reference in bibtex form'

scenario "user can see the articles in bibtex form starting from insertion", {

    given 'user has inserted an article and gone to the reference list', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake?viiteTyyppi=article")

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

        driver.get("http://localhost:8080/miniprojekti/listaa")
    }

    when 'user clicks BibTex-link', {
        System.out.println("== tulostetaan tulosivun koodiRRR03 ==");
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.partialLinkText("RRR03"))
        element.click()

        System.out.println("== tulostetaan tulosivun koodi ==");
        System.out.println( driver.getPageSource() );
    }

    then 'articles will be listed', {
        driver.getCurrentUrl().contains("haebibtex").shouldBe true
        driver.getPageSource().contains("{RRR03,").shouldBe true
    }

}
