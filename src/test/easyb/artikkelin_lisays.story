import ohtu.*
import ohtu.kevatpaiva.tallennus.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select

description 'User can add a new article'

scenario "user can add a new article to the database", {

    given 'article-form selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake")
    }

    when 'article information is given', {
        tallentaja = new ArtikkelinTallentaja()
        tallentaja.poistaViite("B06")

        element = driver.findElement(By.name("type"))
        select = new Select(element)
        select.selectByValue("article")

        element = driver.findElement(By.name("id"));
        element.sendKeys("B06");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Black, Toni R.");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Helping novice programming students succeed");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2006");
        element = driver.findElement(By.name("journal"))
        element.sendKeys("Obligatory Scientific Journal")
        element = driver.findElement(By.name("form-submit"));
        element.submit();
        System.out.println( driver.getPageSource() );
    }
 
    then 'article will be added', {
        driver.getPageSource().contains("onnistuneesti!").shouldBe true
    }

}

scenario "user cannot add a article with a already used id", { 

    given 'article-form selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake")
    }

    when 'article information is given', {
        element = driver.findElement(By.name("type"))
        select = new Select(element)
        select.selectByValue("article")

        element = driver.findElement(By.name("id"));
        element.sendKeys("B06");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Black, Toni R.");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Helping novice programming students succeed");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2006");
        element = driver.findElement(By.name("journal"))
        element.sendKeys("Obligatory Scientific Journal")
        element = driver.findElement(By.name("form-submit"));
        element.submit();
        System.out.println( driver.getPageSource() );
    }
 
    then 'article will not be added', {
        // FIXME: teoriassa kumpikin alla oleva virheilmoitus on mahdollinen
        //        -- Pitäisi saada "or"-ehto toimimaan.
        //driver.getPageSource().contains("column id is not unique").shouldBe true
        //driver.getPageSource().contains("on jo tallennettu").shouldBe true

        tallentaja = new ArtikkelinTallentaja()
        tallentaja.poistaViite("B06")
    }

}

scenario "user cannot add a article to the database without id", { 

    given 'article-form selected', {       
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake")
    }

    when 'article information is given', {
        element = driver.findElement(By.name("type"))
        select = new Select(element)
        select.selectByValue("article")

        element = driver.findElement(By.name("author"));
        element.sendKeys("Black, Toni R.");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Helping novice programming students succeed");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2006");
        element = driver.findElement(By.name("journal"))
        element.sendKeys("Obligatory Scientific Journal")
        element = driver.findElement(By.name("form-submit"));
        element.submit();
        System.out.println( driver.getPageSource() );
    }
 
    then 'article will not be added', {
        driver.getPageSource().contains("on pakollinen").shouldBe true
    }

}

scenario "user cannot add a article to the database without type", { 

    given 'article-form selected', {       
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake")
    }

    when 'article information is given', {
        tallentaja = new ArtikkelinTallentaja()
        tallentaja.poistaViite("B06")

        element = driver.findElement(By.name("id"));
        element.sendKeys("B06");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Black, Toni R.");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Helping novice programming students succeed");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2006");
        element = driver.findElement(By.name("journal"))
        element.sendKeys("Obligatory Scientific Journal")
        element = driver.findElement(By.name("form-submit"));
        element.submit();
        System.out.println( driver.getPageSource() );
    }

    then 'article will not be added'
        // FIXME: tähän tarvitaan tarkastus, kunhan typen validointi ja sen
        // puuttumisen aiheuttava virheilmoitus on toteutettu
}