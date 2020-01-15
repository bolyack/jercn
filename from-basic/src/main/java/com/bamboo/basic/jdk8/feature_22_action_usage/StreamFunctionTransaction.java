package com.bamboo.basic.jdk8.feature_22_action_usage;

/**
 * @Deacription 交易对象
 * @Author bamboo
 * @Date 2020/1/15 16:51
 * @Version 1.0
 **/
public class StreamFunctionTransaction {

    private StreamFunctionTrade trade;
    private Integer year;
    private Integer amount;

    public StreamFunctionTransaction(StreamFunctionTrade trade, Integer year, Integer amount) {
        this.trade = trade;
        this.year = year;
        this.amount = amount;
    }

    public StreamFunctionTrade getTrade() {
        return trade;
    }

    public void setTrade(StreamFunctionTrade trade) {
        this.trade = trade;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "StreamFunctionTransaction{" +
                "trade=" + trade +
                ", year=" + year +
                ", amount=" + amount +
                '}';
    }
}
