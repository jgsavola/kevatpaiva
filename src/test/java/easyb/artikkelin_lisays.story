import ohtu.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add a new article'

scenario "user can add a new article to the database", {
    given 'article-form selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/miniprojekti");       
    }

    when 'article information is given', {
        element = driver.findElement(By.name("id"));
        element.sendKeys("W04");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Whittington, Keith J.");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Infusing active learning into introductory programming courses");
        element = driver.findElement(By.name("year"));
        element.sendKeys(2004);
        element = driver.findElement(By.name("form-submit"));
        element.submit();
    }
 
    then 'user will be logged in to system', {
        driver.getPageSource().contains("Lisätty!").shouldBe true
    }
}