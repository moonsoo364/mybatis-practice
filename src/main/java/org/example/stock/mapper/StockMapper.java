package org.example.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.stock.domain.Stock;

import java.util.List;

@Mapper
public interface StockMapper {
    Stock findById(String id);
    List<Stock> findAll();
    void insert(Stock stock);
    void update(Stock stock);
    void delete(String id);
}
