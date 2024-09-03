package Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageObjects {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public PageObjects(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 saniye bekleme süresi
    }

    // Selecting phones category
    public void clickPhonesCategory() {
        WebElement phonesCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='itemc'][text()='Phones']")));
        phonesCategory.click();
    }

    public String getPriceInfo(int phoneIndex) {
        // XPath ifadesini dinamik hale getiriyoruz
        String phoneLocatorString = String.format("//*[@id=\"tbodyid\"]/div[%d]/div/div/h5", phoneIndex);

        // Öğeyi bulup innerText değerini döndürme
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(phoneLocatorString)));
        return priceElement.getText();
    }

    public void clickPhone(int phoneIndex) {
        String phoneLocatorString = String.format("//*[@id=\"tbodyid\"]/div[%d]", phoneIndex);
        WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(phoneLocatorString)));
        phoneElement.click();
    }

    public String getHeaderText() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/h3")));
        String fullHeaderText = header.getText();

        // Boşluk karakterine kadar olan kısmı alıyoruz
        String priceOnly = fullHeaderText.split(" ")[0];
        return priceOnly;
    }


    public void clickAddToCart(){
        WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")));
        addToCartButton.click();
    }

    // Cart'a eklenme sonrası gelen pop'up için ok'e basar
    public void closePopUp() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("Pop-up closed.");
        } catch (Exception e) {
            System.out.println("No pop-up found or pop-up did not appear in time.");
        }
    }

    // Navigate bar üstünde cart butonuna tıklar
    public void clickToCart(){
        WebElement navigateBarCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cartur\"]")));
        navigateBarCart.click();
    }

    // Place order butonuna tıklar
    public void clickToPlaceOrder(){
        WebElement placeOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")));
        placeOrderButton.click();
    }

    // Order ekranında gelen formu doldurmakla yükümlü metod isim şehir ve diğer parametreler eklenerek dinamik olması sağlanabilir
    public void fillForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Fill in the "Name" field
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='name']")));
        nameField.sendKeys("Sarp Akgündüz");

        // Fill in the "Country" field
        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='country']")));
        countryField.sendKeys("Türkiye");

        // Fill in the "City" field
        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='city']")));
        cityField.sendKeys("Ankara");

        // Fill in the "Credit Card" field
        WebElement cardField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='card']")));
        cardField.sendKeys("1234 5678 9012 3456");

        // Fill in the "Month" field
        WebElement monthField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='month']")));
        monthField.sendKeys("12");

        // Fill in the "Year" field
        WebElement yearField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='year']")));
        yearField.sendKeys("2025");

        System.out.println("Form filled successfully.");
    }

    // Place order aşamasından sonra Purchase butonuna tıklar
    public void clickToPurchase(){
        WebElement purchaseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")));
        purchaseButton.click();
    }

    // Place order ekranında cancel butonuna tıklar ihtiyaç olmadığı için eklenmemiştir testler sırasında eklenme ihtimaline karşı konulmuştur
    public void clickToCancel(){
        WebElement purchaseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]")));
        purchaseButton.click();
    }

    // Purchase ekranında ok butonuna tıklar
    public void clickToOkPurchase(){
        WebElement okButtonPurchaseScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[10]/div[7]/div/button")));
        okButtonPurchaseScreen.click();
    }
}
