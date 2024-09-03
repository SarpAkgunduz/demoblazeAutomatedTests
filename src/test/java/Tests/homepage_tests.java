package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class homepage_tests {
    public static void main(String[] args) {

        // WebDriver object
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Navigate to the website
            driver.get("https://demoblaze.com/index.html#");

            // pageObjects sınıfını oluştur
            PageObjects pageObjects = new PageObjects(driver);

            // Phones kategorisini seç
            pageObjects.clickPhonesCategory();

            // İlk telefonu seç ve fiyat bilgisini al
            String priceFromList = pageObjects.getPriceInfo(1);

            // İlk telefonu seç (detay sayfasına git)
            pageObjects.clickPhone(1);

            // Seçilen telefonun detay sayfasındaki başlık metnini al
            String headerText = pageObjects.getHeaderText();

            // Fiyat bilgisini karşılaştır
            if (priceFromList.equals(headerText)) {
                System.out.println("Test Passed! Price information matches.");
            } else {
                System.out.println("Test Failed! Price information does not match.");
                System.out.println("Price on the list: " + priceFromList);
                System.out.println("Price on the header: " + headerText);
            }

            // Seçilen telefonu karta ekleme
            pageObjects.clickAddToCart();

            // "Product added" pop-up'ını kapatma
            pageObjects.closePopUp();

            // Sepet ekranına erişim
            pageObjects.clickToCart();

            // Place order butonuna tıklanması
            pageObjects.clickToPlaceOrder();

            // formun doldurulmasından sorumlu metod, modüler yazmakla uğraşmadım ancak parametre eklenerek test genişletilebilir
            pageObjects.fillForm();

            // purchase butonuna tıklanması
            pageObjects.clickToPurchase();

            // ok butonuna tıklanması
            pageObjects.clickToOkPurchase();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Tarayıcıyı kapat
            driver.quit();
        }
    }
}
