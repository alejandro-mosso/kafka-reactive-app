package com.stock.exchange.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StockRatio {
    @NonNull
    private int id;
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
}
