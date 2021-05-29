package com.example.BookReview.business.model.strategy;

public class SearchRequestModel {
    private SearchingStrategyEnum strategy;
    private String data;


    public SearchRequestModel() {
    }

    public SearchRequestModel(SearchingStrategyEnum strategy, String data) {
        this.strategy = strategy;
        this.data = data;
    }

    public SearchingStrategyEnum getStrategy() {
        return strategy;
    }

    public void setStrategy(SearchingStrategyEnum strategy) {
        this.strategy = strategy;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
