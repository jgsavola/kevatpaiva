import ohtu.*
import ohtu.kevatpaiva.tallennus.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select

description 'User can add a new reference'

scenario "user can add a new book to the database", {

    given 'reference-form selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake?viiteTyyppi=book")
    }

    when 'book information is given', {
        tallentaja = new ArtikkelinTallentaja()
        tallentaja.poistaViite("VPL11")

        element = driver.findElement(By.name("id"));
        element.sendKeys("VPL11");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Vihavainen, Arto and Paksula, Matti and Luukkainen, Matti");
        element = driver.findElement(By.name("editor"));
        element.sendKeys("Vihavainen, Arto and Paksula, Matti and Luukkainen, Matti");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("Vihavainen, Arto and Paksula, Matti and Luukkainen, Matti");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Extreme Apprenticeship Method in Teaching Programming for Beginners.");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2011");
        element = driver.findElement(By.name("form-submit"));
        element.submit();
    }
 
    then 'reference will be added', {
        driver.getPageSource().contains("onnistuneesti!").shouldBe true
    }

}

scenario "user cannot add a reference with a already used id", { 

    given 'reference-form selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake?viiteTyyppi=inproceedings")
    }

    when 'reference information is given', {

        element = driver.findElement(By.name("id"));
        element.sendKeys("VPL11");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Vihavainen, Arto and Paksula, Matti and Luukkainen, Matti");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Extreme Apprenticeship Method in Teaching Programming for Beginners.");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2011");
        element = driver.findElement(By.name("form-submit"));
        element.submit();
    }
 
    then 'reference will not be added', {
        driver.getCurrentUrl().contains("lisaa").shouldBe true
        driver.getPageSource().contains("jo tallennettu").shouldBe true
    }
}

scenario "user cannot add a reference to the database without id", { 

    given 'reference-form selected', {       
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake?viiteTyyppi=inproceedings")
    }

    when 'reference information is given', {

        element = driver.findElement(By.name("author"));
        element.sendKeys("Vihavainen, Arto and Paksula, Matti and Luukkainen, Matti");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Extreme Apprenticeship Method in Teaching Programming for Beginners.");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2011");
        element = driver.findElement(By.name("form-submit"));
        element.submit();
    }
 
    then 'article will not be added', {
        driver.getPageSource().contains("on pakollinen").shouldBe true
    }
}
