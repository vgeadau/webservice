package com.vali.webservice;

import com.vali.webservice.services.ParseCSV;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CSV Controller class.
 * Useful links:
 * @see <a href="http://localhost:8080/swagger-ui/index.html">API Dcoumentation</a>
 * @see <a href="http://localhost:8080/v3/api-docs">JSON Informations</a>
 * <br>
 * Generate JACOCO report:
 * mvn clean jacoco:prepare-agent install jacoco:report
 * <br>
 * 100% code coverage
 * file:///C:/Users/Valentin/IdeaProjects/webservice/target/site/jacoco/index.html
 * <br>
 * More details can be found in {readme.md}
 */
@RestController
@Tag(name ="CSV API", description = "Parses currencies defined in a CSV.")
public class CSVController {

    /**
     * Service class that implements this API requirements.
     */
    @Autowired
    public ParseCSV parseCSV;

    /**
     * Get average values from a CSV file for the provided currency.
     *
     * @param currency String currency CODE supported are GBP, USD, EUR, CHF
     * @return 0 if the currency not present in the CSV file but supported,
     *         average if present and supported
     *         Bad request if the requested currency is not supported
     *         Note that an unsupported currency can exist in the CSV file, the validation of CSV file is out of scope
     */
    @Operation(summary = "Get average values for provided currency.")
    @GetMapping("/getAverage/{currency}")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "OK - Currency is supported, calculated average is returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Currency not supported", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found - Missing / Bad configuration fullPath", content = @Content)
    })
    public double getAverage(@PathVariable @Parameter(description = "The currency code") String currency) {
        return parseCSV.getAverage(currency);
    }

    /**
     * Methods that checks the distinct currencies present in the CSV file.
     * @return A sorted list of currencies
     */
    @Operation(summary = "Gets the currencies present in CSV.")
    @GetMapping("/getAvailableCurrencies")
    public List<String> getAvailableCurrencies() {
        return parseCSV.getAvailableCurrencies();
    }

}