package com.stock.exchange.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StockValue {
    @NonNull
    private int id;
    @NonNull
    private String company;
    /**
     * The term stock price refers to the current price that a share of stock is
     * trading for on the market.
     **/
    @NonNull
    private double price;
    /**
     * Beta is a measure of a stock's volatility in relation to the overall market.
     * By definition, the market, such as the S&P 500 Index, has a beta of 1.0, and
     * individual stocks are ranked according to how much they deviate from the market.
     *
     * A stock that swings more than the market over time has a beta above 1.0. If a
     * stock moves less than the market, the stock's beta is less than 1.0. High-beta
     * stocks are supposed to be riskier but provide higher return potential; low-beta
     * stocks pose less risk but also lower returns.
     */
    private double beta;
    /**
     * A stock dividend is a dividend payment to shareholders that is made in shares rather
     * than as cash. The stock dividend has the advantage of rewarding shareholders without
     * reducing the company's cash balance, although it can dilute earnings per share.
     *
     * These stock distributions are generally made as fractions paid per existing share.
     * For example, a company might issue a stock dividend of 5%, which will require it to
     * issue 0.05 shares for every share owned by existing shareholders, so the owner of 100
     * shares would receive 5 additional shares.
     **/
    private double dividends;
    private double revenue;
    private double adjustedEarnings;
}
