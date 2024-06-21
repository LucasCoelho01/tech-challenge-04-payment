package com.tech_challenge_04_payment.bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech_challenge_04_payment.entity.Payment;
import com.tech_challenge_04_payment.entity.dto.CreatePaymentDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepDefinition {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;
    private CreatePaymentDto createPaymentDto;

    @Autowired
    private ObjectMapper objectMapper;

    @Given("a customer payload with orderId {string}")
    public void a_customer_payload_with_order_id_and_is_payment_ok_true(String string) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        createPaymentDto = new CreatePaymentDto("123456");
    }

    @When("the client requests to create a payment")
    public void the_client_requests_to_create_a_payment() throws Exception {
        mvcResult = mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createPaymentDto)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Then("the response should contain the payment's ID and details")
    public void the_response_should_contain_the_payment_s_id_and_details() throws Exception {
        String content = mvcResult.getResponse().getContentAsString();
        Payment createdPayment = objectMapper.readValue(content, Payment.class);
        assertThat(createdPayment.getId()).isNotNull();
    }
}
