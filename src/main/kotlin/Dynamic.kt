import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.Thread.sleep
import java.time.Duration

val driverLocation = "src/main/kotlin/chromedriver.exe"

fun main() {
    System.setProperty("webdriver.chrome.driver", driverLocation);
    System.setProperty("webdriver.http.factory", "jdk-http-client");

    val driver = ChromeDriver()
    driver.get("https://www.tokopedia.com/910ninetenshoes/etalase/running-shoes")  // Load the query

    println(driver.toString())

    WebDriverWait(driver, Duration.ofSeconds(10)).until(
        ExpectedConditions.presenceOfElementLocated(
            By.cssSelector(
                "div.css-tjjb18 > div"
            )
        )
    )

    val cards: MutableList<String> = mutableListOf()   // Create a list to hold tweets
    while( cards.size< 10){   // Repeat loop until you have at least 10 tweets
        val card = driver.findElements(By.cssSelector("div[data-testid=\"master-product-card\"]"))  // Find the tweets container
        println(card.toString())
        for (e in card) {
            val name: WebElement = e.findElement(By.cssSelector("div[data-testid=\"linkProductName\"]"))
            if (name.text != null){  // Look for the link attribute
                cards.add(name.text)  // If present, add it to the links list
            }
        }

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN)  // Find the body tag and press the down key to simulate scrolling

        sleep(1500) // Sleep to allow the client to load new tweets
    }

    driver.quit();  // Closes the Selenium session

    println(cards); // Prints out all the links
}
