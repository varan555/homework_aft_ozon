package pages;

import annotation.ElementName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.VirtualCart;

import java.util.List;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//div[contains(text(), 'Цена')]/parent::div//input[@qa-id = 'range-to']")
    @ElementName(nameElement = "максимальная цена")
    public WebElement maxPrice;

    @FindBy(xpath = "//div[@value = 'Высокий рейтинг']//div[@class = 'ui-at4']")
    @ElementName(nameElement = "высокий рейтинг")
    public WebElement highRate;

    @FindBy(xpath = "//span[contains(text(),'NFC')]/parent::div/parent::label//div[@class='ui-at4']")
    @ElementName(nameElement = "NFC")
    public WebElement NFC;

    @FindBy(xpath = "//div[@data-widget='searchResultsV2']/child::div/child::div")
    public List<WebElement> searchResults;

    @FindBy(xpath = "//div[@data-widget='searchResultsV2']/child::div/child::div//div[contains(text(), 'В корзину')]")
    public List<WebElement> addToCart;

    @FindBy(xpath = "//div[contains(@style,'grid-column-start')]/div/div/div[2]/div/a")
    public List<WebElement> resultName;

    @FindBy(xpath = "//div[contains(@style,'grid-column-start')]/div/div/div[3]//span[contains(text(), '\u20BD')]")
    public List<WebElement> resultPrice;

    @FindBy(xpath = "//span[contains(text(),'Корзина')]")
    @ElementName(nameElement = "корзина")
    public WebElement cart;

    @FindBy(xpath = "//div[contains(text(),'Бренды')]/parent::div//span[contains(text(),'Beats')]")
    @ElementName(nameElement = "Beats")
    public WebElement beats;

    @FindBy(xpath = "//div[contains(text(),'Бренды')]/parent::div//span[contains(text(),'Samsung')]")
    @ElementName(nameElement = "Samsung")
    public WebElement samsung;

    @FindBy(xpath = "//div[contains(text(),'Бренды')]/parent::div//span[contains(text(),'Xiaomi')]")
    @ElementName(nameElement = "Xiaomi")
    public WebElement xiaomi;

    @FindBy(xpath = "//div[contains(text(),'Бренды')]/parent::div//span[contains(text(),'Посмотреть все')]")
    @ElementName(nameElement = "Посмотреть все")
    public WebElement expand;




    public void addNPurchases (String  numberOfPurchase, String name) {

        //определяем какие нужно добавить товары - четные или нечетные
        int oddEven = 0;
        if(name.equals("четных")||name.equals("четные")||name.equals("четный")) {
            oddEven = 1;
        }

        //определяем какое количество товаров будет добавлено
        int number = 0;
        if (numberOfPurchase.equals("все")) {
            number = resultName.size()/2;
        }
        else number = Integer.parseInt(numberOfPurchase);


        int numberOfResult = oddEven;
        for(int i = 0; i < number; i++) {
            WebElement thisAddToCart = addToCart.get(numberOfResult-i);
//            try{
//            thisAddToCart = addToCart.get(numberOfResult-i);
//            }
//            catch (Exception e) {
//                thisAddToCart = addToCart.get(numberOfResult-i+1);
//            }
            WebElement thisResultName = resultName.get(numberOfResult);
            WebElement thisResultPrice = resultPrice.get(numberOfResult);
            click(thisAddToCart);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String nameVirtual = thisResultName.getText();
            double priceVirtual = getValue(thisResultPrice);
            VirtualCart.addPurchase(nameVirtual, priceVirtual);
            numberOfResult = numberOfResult + 2;
        }
    }

    public void toCart(){
        click(cart);
    }

    @Override
    public WebElement getField(String name) throws Exception {
        return getField(name, "pages.SearchPage");
    }

}
