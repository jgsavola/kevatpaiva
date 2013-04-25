import ohtu.*
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select

description 'User gets a list of articles'

scenario "user can see articles as a list starting from insertion", {

    given 'user has inserted an article', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake?viiteTyyppi=article")
        tallentaja = new ArtikkelinTallentaja()
        tallentaja.poistaViite("RRR03")

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
        element = driver.findElement(By.linkText("Tarkastele viitteit\u00e4"))
        element.click()
    }
 
    then 'articles will be listed', {
        driver.getPageSource().contains("list.jsp").shouldBe true
    }

}

scenario "user can see articles as a list starting from the main page", {

    given 'user is on the main page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/")
    }

    when 'user clicks lista-link', {
        element = driver.findElement(By.linkText("Tarkastele viitteit\u00e4"))
        element.click()
    }
 
    then 'articles will be listed', {
        driver.getPageSource().contains("list.jsp").shouldBe true
    }

}

scenario "user can see articles as a list starting from the insertion page", {

    given 'user is on the lomake page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti/lomake")
    }

    when 'user clicks lista-link', {
        element = driver.findElement(By.linkText("Tarkastele viitteit\u00e4"))
        element.click()
    }
 
    then 'articles will be listed', {
        driver.getPageSource().contains("list.jsp").shouldBe true
    }

}

//scenario "user can see articles as a list starting from the bibtex page", {
//
// TÄTÄ EI TARVITA, KUN BIBTEXISSÄ EI LINKKEJÄ (COPYPASTE-SIVU)
//
//    given 'user is on the bibtex page', {
//        driver = new HtmlUnitDriver();
//        driver.get("http://localhost:8080/miniprojekti/haebibtex")
//    }
//
//    when 'user clicks lista-link', {
//        element = driver.findElement(By.linkText("Tarkastele viitteit\u00e4"))
//        element.click()
//    }
// 
//    then 'articles will be listed', {
//        driver.getPageSource().contains("Artikkeliviitteet").shouldBe true
//    }
//
//}
