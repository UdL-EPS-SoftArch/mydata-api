package cat.udl.eps.entsoftarch.steps;

import cat.udl.eps.entsoftarch.repository.DatasetRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by UNI on 27/02/2017.
 */
public class ListDatasetsStepdefs {

    @Autowired StepDefs stepDefs;
    @Autowired DatasetRepository datasetRepository;

    @When("^I list the dataset$")
    public void iListTheDataset() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(get("/datasets")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @And("^In the position (\\d+) there is a dataset with title \"([^\"]*)\"$")
    public void inThePositionThereIsADatasetWithTitle(int position, String title) throws Throwable {
        stepDefs.result.andExpect(jsonPath("$._embedded.datasets["+ position +"].title", is(title)));

    }

    @When("^I list the dataset order by title$")
    public void iListTheDatasetOrderByTitle() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(get("/datasets?sort=title")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
