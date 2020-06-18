package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.CartPage;

import java.util.List;

import static steps.BaseSteps.pageObject;

public class CartPageSteps {
    CartPage cartPage = new CartPage();

    @Then("корзина {string} загружена")
    public void pageLoadedCart(String name) throws Exception {
        Class example = Class.forName("pages." + name);
        pageObject = (BasePage)example.getDeclaredConstructor().newInstance();
    }

    @Then("проверяем соответствие записанных значений фактическим в корзине:")
    public void checkPurchases() {
        cartPage.checkPurchases();
    }

    @Then("проверяем соответствие общего количества купленного товара ожидаемому {string}")
    public void checkNumbers(String expected) {
        cartPage.checkNumbers(expected);
    }

    @When("в корзине выполнено нажатие на:")
    public void click(List<String> arg) throws Exception {
        for (String name: arg) {
            pageObject.click(name);
        }
    }

    @Then("проверяем, что корзина пуста:")
    public void checkEmty() {
        cartPage.checkEmpty();
    }

}
