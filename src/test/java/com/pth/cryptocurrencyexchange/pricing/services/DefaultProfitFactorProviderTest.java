package com.pth.cryptocurrencyexchange.pricing.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class DefaultProfitFactorProviderTest {

    private DefaultProfitFactorProvider profitFactorProvider;

    @BeforeEach
    public void setUp() {
        profitFactorProvider = new DefaultProfitFactorProvider();
        ReflectionTestUtils.setField(profitFactorProvider, "profitFactors", new float[] {5f, 12f, 10f});
    }

    @Test
    void getProfitFactorTest() {
        float profitFactor = profitFactorProvider.getProfitFactor();
        assertNotNull(profitFactor);
    }
}