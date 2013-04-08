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
        element.sendKeys("B06");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Black, Toni R.");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Helping novice programming students succeed");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2006");
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
        driver.get("http://localhost:8080/miniprojekti");       
    }

    when 'article information is given', {
        element = driver.findElement(By.name("id"));
        element.sendKeys("B06");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Black, Toni R.");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Helping novice programming students succeed");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2006");
        element = driver.findElement(By.name("form-submit"));
        element.submit();
        System.out.println( driver.getPageSource() );
    }
 
    then 'article will not be added', {
        driver.getPageSource().contains("column id is not unique").shouldBe true
    }
}


scenario "user cannot add a article to the database without id", { 
    given 'article-form selected', {       
    }

    when 'article information is given', {
    }
 
    then 'article will not be added', {
    }
}