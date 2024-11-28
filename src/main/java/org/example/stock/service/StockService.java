package org.example.stock.service;

import lombok.NoArgsConstructor;
import org.example.stock.domain.Stock;
import org.example.stock.mapper.StockMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {
    private final StockMapper stockMapper;

    public StockService(StockMapper stockMapper){
        this.stockMapper = stockMapper;
    }


    public Stock findById(String id){
        return stockMapper.findById(id);
    }

    public List<Stock> findAll(){
        return stockMapper.findAll();
    }

    public void insert(Stock stock){
        stock.setCreatedDate(LocalDateTime.now());
        stockMapper.insert(stock);
    }

    public void update(Stock stock){
        stock.setCreatedDate(LocalDateTime.now());
        stockMapper.update(stock);
    }

    public void delete(String id){
        stockMapper.delete(id);
    }
}
