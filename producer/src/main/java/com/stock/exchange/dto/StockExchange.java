package com.stock.exchange.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StockExchange {
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
     * Companies use the price-to-book ratio (P/B ratio) to compare a firm's market
     * capitalization to its book value. It's calculated by dividing the company's
     * stock price per share by its book value per share (BVPS). An asset's book
     * value is equal to its carrying value on the balance sheet, and companies
     * calculate it netting the asset against its accumulated depreciation.
     *
     * The P/B ratio reflects the value that market participants attach to a company's
     * equity relative to the book value of its equity. A stock's market value is a
     * forward-looking metric that reflects a company's future cash flows. The book
     * value of equity is an accounting measure based on the historic cost principle
     * and reflects past issuances of equity, augmented by any profits or losses, and
     * reduced by dividends and share buybacks.
     */
    private double priceBookRatio;
    /**
     * The debt-to-equity (D/E) ratio is calculated by dividing a company’s total
     * liabilities by its shareholder equity. These numbers are available on the
     * balance sheet of a company’s financial statements.
     *
     * The ratio is used to evaluate a company's financial leverage. The D/E ratio
     * is an important metric used in corporate finance. It is a measure of the degree
     * to which a company is financing its operations through debt versus wholly-owned
     * funds. More specifically, it reflects the ability of shareholder equity to cover
     * all outstanding debts in the event of a business downturn.
     **/
    private double debtEquityRatio;
    private double priceEarningsGrowthRatio;
    private double priceEarningsRatio;
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

    /*
     * relativeStrength: For example, if the price of Ford shares is $7 and the price
     * of GM shares is $25, the relative strength of Ford to GM is 0.28 ($7/25).
     */
}
