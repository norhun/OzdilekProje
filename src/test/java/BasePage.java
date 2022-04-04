import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class BasePage extends BaseTest {




    @Step("<int> saniye kadar bekle")
    public void waitForsecond(int s) throws InterruptedException {
        Thread.sleep(1000 * s);
    }

    @Step("<id> elemetin sayfada gorunur olduğunu kontrol et ve tıkla")
    public void findByelementEndclick(String id) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()) {
            element.click();
        } else {
            System.out.println("Kontrol edilen element Görünür olmadı");
        }
    }

    @Step("Scroll Down The Page")
    public void swipeUp() {
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Ekran Boyutu " + dims);
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("Başlangıç noktası " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 2, dims.height / 4);
        System.out.println("Bitiş noktası " + pointOptionEnd);
        try {
            new TouchAction(appiumDriver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @Step("Xpath <xpath> li elementi bul ve tıkla")
    public void clickByxpath(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();


    }


    @Step("Id <id> li elementi bul ve tıkla")
    public void clickByid(String id) {
        appiumDriver.findElement(By.id(id)).click();


    }


    @Step("<xpath> Rastgele secim")
    public void randomEkle(String xpath) {
        List<MobileElement> elements = appiumDriver.findElements(By.xpath(xpath));
        Random rnd = new Random();
        int rndInt = rnd.nextInt(elements.size());
        elements.get(rndInt).click();



    }

    @Step ("<id> li alana kullanıcı adını gir")

    public void kullaniciTextGir(String id){

        MobileElement element = (MobileElement) appiumDriver.findElementById(id);
        element.sendKeys("kaanakca01@gmail.com");





    }

    @Step ("<id> li alana şifreyi gir")

    public void sifreGir(String id){

        MobileElement element = (MobileElement) appiumDriver.findElementById(id);
        element.sendKeys("123456");




    }

    @Step("<id> li elementi bul ve iki kere tıkla")
    public void clicktwoTimes(String id) throws InterruptedException {
        MobileElement buttonElement = appiumDriver.findElementById(id);
        int i;
        for (i =0; i <2; i++){

            buttonElement.click();

       waitForsecond(2);
        }


    }
    @Step("<id> Verification of shopping page")
    public void shoppingPageVerification(String id){
        Assert.assertTrue(BaseTest.appiumDriver.findElementById(id).isDisplayed());
        Loglama.logger.info("The Shopping Page Has Opened!");
    }
    @Step("<id> Verification of categories page")
    public void categoriesPageVerification(String id){
        Assert.assertTrue(BaseTest.appiumDriver.findElementById(id).isDisplayed());
        Loglama.logger.info("The Categories Page Has Opened!");
    }

    @Step("<id> Verification of Login page")
    public void loginPageVerification(String id){
        Assert.assertTrue(BaseTest.appiumDriver.findElementById(id).isDisplayed());
        Loglama.logger.info("The Login Page Has Opened!");
    }



}