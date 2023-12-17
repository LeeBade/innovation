package com.titian.innovation.item.controller;

import com.titian.innovation.common.http.Item;
import com.titian.innovation.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("items")
public class ItemController {
    final ItemService itemService;

    public ItemController (ItemService itemService) {
        this.itemService = itemService;
    }

    // 获取全部项目
    @GetMapping
    public List<Item> getItems() {

    }

    // 搜索项目
    @GetMapping("/search")
    public List<Item> searchItems(@RequestParam String keywords) {
    }

    // 获取用户的全部项目
    @GetMapping("/user")
    public List<Item> getUserItems(@RequestParam String userId) {
    }

    // 删除项目及其文件
    @DeleteMapping
    public void deleteItem(@RequestParam String itemId) {
    }

    // 创建项目
    @PostMapping("/create")
    public Item createItem(@RequestParam String userId, @RequestParam String itemName) {
    }
}


