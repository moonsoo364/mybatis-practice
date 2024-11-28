package org.example.stock.controller;

import org.example.stock.domain.Stock;
import org.example.stock.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/id")
    public Stock findById(@PathVariable String id){
        return stockService.findById(id);
    }

    @GetMapping
    public List<Stock> findAll(){
        return stockService.findAll();
    }

    @PostMapping
    public void insert(@RequestBody Stock stock){
        stockService.insert(stock);
    }

    @PutMapping
    public void update(@RequestBody Stock stock){
        stockService.update(stock);
    }

    @DeleteMapping
    public void delete(@PathVariable String id){
        stockService.delete(id);
    }
}
