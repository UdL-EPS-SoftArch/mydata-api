package cat.udl.eps.entsoftarch.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by yatan on 2/27/17.
 */
public class SearchDatasetByDescriptionStepDefs {

    @Autowired private StepDefs stepDefs;



    @When("^I search with a blank description$")
    public void iSearchWithABlankDescription() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/datasets/search/findByDescriptionContaining?description={}"))
                .andDo(print());
    }

    @When("^I search with \"([^\"]*)\"$")
    public void iSearchWith(String description) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/datasets/search/findByDescriptionContaining?description={description}",description))
                .andDo(print());
    }

    @Then("^Show (\\d+) datasets$")
    public void showDatasets(int count) throws Throwable {
        stepDefs.result.andExpect(jsonPath("$._embedded.datasets", hasSize(count)));
    }
}
