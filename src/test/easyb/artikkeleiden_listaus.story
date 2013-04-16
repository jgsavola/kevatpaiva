import ohtu.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User gets a list of articles'

scenario "user can see articles as a list starting from insertion", {

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
        element = driver.findElement(By.name("year"));
        element.sendKeys("2003");
        element = driver.findElement(By.name("form-submit"));
        element.submit();
    }

    when 'user clicks lista-link', {
        element = driver.findElement(By.linkText("Tarkastele viitteitä"))
        element.click()
    }
 
    then 'articles will be listed', {
        driver.getPageSource().contains("Artikkeliviitteet").shouldBe true
    }

}

scenario "user can see articles as a list starting from the main page", {

    given 'user is on the main page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/")
    }

    when 'user clicks lista-link', {
        element = driver.findElement(By.linkText("Tarkastele viitteitä"))
        element.click()
    }
 
    then 'articles will be listed', {
        driver.getPageSource().contains("Artikkeliviitteet").shouldBe true
    }

}

scenario "user can see articles as a list starting from the insertion page", {

    given 'user is on the lomake page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake")
    }

    when 'user clicks lista-link', {
        element = driver.findElement(By.linkText("Tarkastele viitteitä"))
        element.click()
    }
 
    then 'articles will be listed', {
        driver.getPageSource().contains("Artikkeliviitteet").shouldBe true
    }

}

scenario "user can see articles as a list starting from the bibtex page", {

    given 'user is on the bibtex page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/haebibtex")
    }

    when 'user clicks lista-link', {
        element = driver.findElement(By.linkText("Tarkastele viitteitä"))
        element.click()
    }
 
    then 'articles will be listed', {
        driver.getPageSource().contains("Artikkeliviitteet").shouldBe true
    }

}
