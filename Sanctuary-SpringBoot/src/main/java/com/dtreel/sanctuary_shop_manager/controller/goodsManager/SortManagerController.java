package com.dtreel.sanctuary_shop_manager.controller.goodsManager;


import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.SortDO;
import com.dtreel.sanctuary_shop_manager.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DtreeL
 */
@RestController
@RequestMapping("/home/goodsManager/sortManager/api/sort")
public class SortManagerController {
    @Autowired
    private SortService sortService;

    @GetMapping("/")
    public ResponseVO listSort() {
        return sortService.listSort();
    }

    @PostMapping("/")
    public ResponseVO insertSort(@RequestBody SortDO sort) {
        return sortService.insertSort(sort);

    }

    @PutMapping("/")
    public ResponseVO updateSort(@RequestBody SortDO sort) {
        return sortService.updateSort(sort);
    }

    @DeleteMapping("/{id}")
    public ResponseVO deleteSort(@PathVariable Integer id) {
        return sortService.deleteSortById(id);

    }

    @DeleteMapping("/")
    public ResponseVO deleteSort(@PathVariable Integer[] ids) {
        return sortService.deleteSortByIds(ids);
    }
}
