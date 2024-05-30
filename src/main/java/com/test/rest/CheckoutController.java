package com.test.rest;

import com.test.domain.dto.Checkout;
import com.test.domain.dto.RentalAgreement;
import com.test.exception.CheckoutException;
import com.test.service.CheckoutService;
import com.test.service.impl.CheckoutServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.test.exception.CheckoutException.ERROR_CODE.RENTAL_DAY_COUNT;
import static com.test.exception.CheckoutException.ERROR_CODE.TOOL_CODE;

@RestController
@Validated
public class CheckoutController {
    Logger logger = LoggerFactory.getLogger(CheckoutController.class);

    CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService){
        this.checkoutService = checkoutService;
    }

    @Operation(summary = "Checkout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create rental agreement", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RentalAgreement.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid tool_code supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Check out error", content = @Content)})
    @PostMapping("/checkout")
    private ResponseEntity<RentalAgreement> checkout(
            @NotNull @RequestBody Checkout checkout) {
        try {
            RentalAgreement response = checkoutService.checkout(checkout);
            response.printToConsole();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CheckoutException e) {
            logger.error("checkout:", e);
            if(e.getErrorCode().equals(TOOL_CODE)){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
