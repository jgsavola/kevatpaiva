import ohtu.*
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select

description 'User gets a list of articles'

scenario "user can see the main page", {
    given 'user starts the program'
    when 'user gives the address of the program'
    then 'the main page will be shown'
}
