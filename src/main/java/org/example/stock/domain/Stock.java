package org.example.stock.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Stock {
    private String stockId;

    private String stockName;

    private String stockExchange;

    private Float currentPrice;

    private LocalDateTime createdDate;

}
