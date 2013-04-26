import ohtu.*
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select

description 'User changes a reference'

scenario "user can find a reference he wants to change on the list page", {

    given 'user goes on the list page', {
        driver = new HtmlUnitDriver();

        // tallennetaan ensin viite, jota muutetaan
        driver.get("http://localhost:8080/miniprojekti/lomake?viiteTyyppi=article")
        tallentaja = new ArtikkelinTallentaja()
        tallentaja.poistaViite("RRR06")

        element = driver.findElement(By.name("id"));
        element.sendKeys("RRR06");
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
        driver.getPageSource().contains("[RRR06]").shouldBe true
    }

}

scenario "user can make the changes", {

    given 'user is on the list page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/listaa")
    }

    when 'user clicks a change-link behind the reference he wants to change', {
//        System.out.println("== tulostetaan tulosivun koodi RRR06 ==");
//        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("muutos/RRR06"))
        element.submit()
    }
 
    and 'reference changes are done', {
        element = driver.findElement(By.name("author"));
        element.clear();
        element.sendKeys("Black, Toni R. ***");
        element = driver.findElement(By.name("form-submit"));
        element.submit();
    }
    then 'the reference will be changed', {
        driver.getPageSource().contains("onnistuneesti!").shouldBe true
    }
}

scenario "user cannot change a reference without author", { 

    given 'user is on the list page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/listaa")
    }

    when 'user clicks a change-link behind the reference he wants to change', {
//        System.out.println("== tulostetaan tulosivun koodi RRR06 ==");
//        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("muutos/RRR06"))
        element.submit()
    }
 
    and 'author will be cleared', {
        element = driver.findElement(By.name("author"));
        element.clear();
        element = driver.findElement(By.name("form-submit"));
        element.submit();
    }
 
    then 'reference will not be changed', {
        driver.getPageSource().contains("on pakollinen").shouldBe true
    }

}