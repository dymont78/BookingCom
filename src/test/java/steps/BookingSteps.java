package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class BookingSteps {

    String city;

    @Given("User is looking for hotels in {string} city")
    public void userIsLookingForHotelsInCity(String city) {
        this.city = city;
    }
    @When("User does search")
    public void userDoesSearch() {
        open("https://www.booking.com/");
        $(By.id("ss")).sendKeys(city);
        $(".sb-searchbox__button").click();
    }

    @Then("Hotel {string} should be on the first line of search results page")
    public void hotelApartmentOnRepinaShouldBeOnTheFirstLineOfSearchResultsPage(String hotel) {
        $(By.xpath("//span[contains(text(),'Show prices')]")).shouldBe(Condition.visible);
        ArrayList<String> hotelsNames = new ArrayList<>();
        for (SelenideElement element : $$(".sr-hotel__name")){
            hotelsNames.add(element.getText());
        }
        Assert.assertTrue(hotelsNames.contains(hotel));
        //assertThat(hotelsNames, hasItem(hotel));

    }
}
