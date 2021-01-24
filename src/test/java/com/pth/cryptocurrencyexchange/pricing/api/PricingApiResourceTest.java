package com.pth.cryptocurrencyexchange.pricing.api;

import com.pth.cryptocurrencyexchange.JsonUtil;
import com.pth.cryptocurrencyexchange.core.constants.CoreConstants;
import com.pth.cryptocurrencyexchange.pricing.domain.BuyBTCResponse;
import com.pth.cryptocurrencyexchange.pricing.domain.PricingResponse;
import com.pth.cryptocurrencyexchange.pricing.services.PricingService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PricingApiResource.class)
class PricingApiResourceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PricingService pricingService;

    @Test
    void ping() throws Exception {
        mockMvc.perform(get("/api/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string(CoreConstants.PING_SUCCESS_MESSAGE));

    }

    @Test
    void getPriceHaveToPayWhenBuying() throws Exception {
        PricingResponse response = createDummyResponse();
        given(pricingService.getPriceHaveToPayWhenBuying(Mockito.any())).willReturn(response);

        mockMvc.perform(get("/api/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice", is("90.000,00")));
    }

    @Test
    void getPriceWillReceiveWhenSelling() throws Exception {
        PricingResponse response = createDummyResponseForSelling();
        given(pricingService.getPriceWillReceiveWhenSelling(Mockito.any())).willReturn(response);

        mockMvc.perform(get("/api/sell")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice", is("94.500,00")));
    }

    @Test
    void getAmountBCTCanBy() throws Exception {
        BuyBTCResponse response = createDummyBuyBTCResponse();
        given(pricingService.getAmountBTCCanBeBought(Mockito.any())).willReturn(response);

        mockMvc.perform(get("/api/getAmountCanBeBought")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amountBTC", is(1)));
    }

    private PricingResponse createDummyResponseForSelling() {
        PricingResponse response = new PricingResponse();
        response.setId(UUID.randomUUID().toString());
        response.setAmount(2);
        response.setSpotPrice(BigDecimal.valueOf(45000));
        response.setProfitFactor(0.05f);
        response.setTotalPrice(BigDecimal.valueOf(94500));

        return response;
    }

    private PricingResponse createDummyResponse() {
        PricingResponse response = new PricingResponse();
        response.setId(UUID.randomUUID().toString());
        response.setAmount(2);
        response.setSpotPrice(BigDecimal.valueOf(45000));
        response.setProfitFactor(0.05f);
        response.setTotalPrice(BigDecimal.valueOf(90000));

        return response;
    }

    private BuyBTCResponse createDummyBuyBTCResponse() {
        return new BuyBTCResponse("NZD", BigDecimal.valueOf(45000), BigDecimal.ONE);
    }
}