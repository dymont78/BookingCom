package steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

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
        $(By.xpath("//span[contains(text(),'Показать цены')]")).shouldBe(Condition.visible);
        assertThat($$(".sr-hotel__name").texts(), hasItem(hotel));
    }

    @Then("Hotel {string} rating is {string}")
    public void hotelRating(String hotel, String rate) {
        String nameHotel = $(By.xpath("//span[contains(@class,'sr-hotel__name')][1]")).getText();
        String rateHotel = $(By.xpath(String.format("//span[contains(@class,'sr-hotel__name')][contains(text(), '%s')]/ancestor::div[contains(@class,'sr_property_block_main_row')]//div[contains(@class,'bui-review-score__badge')]", nameHotel))).getText();
        Assert.assertEquals(rateHotel, rate);
    }
}