import ohtu.*
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select

description 'User deletes a reference'

scenario "user can find a reference he wants to delete on the list page", {

    given 'user goes on the list page', {
        driver = new HtmlUnitDriver();

        // tallennetaan ensin viite, joka poistetaan
        driver.get("http://localhost:8080/miniprojekti/lomake?viiteTyyppi=article")
        tallentaja = new ArtikkelinTallentaja()
        tallentaja.poistaViite("RRR05")

        element = driver.findElement(By.name("id"));
        element.sendKeys("RRR05");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Anthony Robins and Janet Rountree and Nathan Rountree");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Learning and teaching programming: A review and discussion");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("Journal House");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2005");
        element = driver.findElement(By.name("form-submit"));
        element.submit();

        // mennään oikealle sivulle
        driver.get("http://localhost:8080/miniprojekti/listaa")
    }

    when 'user watches the references', {
    }
 
    then 'he sees a reference he wants to delete', {
        driver.getPageSource().contains("[RRR05]").shouldBe true
    }

}

scenario "user can delete a reference he sees", {

    given 'user is on the list page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/listaa")
    }

    when 'user clicks a delete-link behind the reference he wants to delete', {
        System.out.println("== tulostetaan tulosivun koodi RRR05 ==");
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("poisto/RRR05"))
        element.submit()
    }
 
    then 'articles will be listed', {
        driver.getPageSource().contains("[RRR05]").shouldBe false
    }

}
