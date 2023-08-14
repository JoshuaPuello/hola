package com.serviceplazoleta.infrastructure.input.rest;

import com.serviceplazoleta.application.dto.CategoryRequestDto;
import com.serviceplazoleta.application.handler.ICategoryHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final ICategoryHandler categoryHandler;

    public CategoryController(ICategoryHandler categoryHandler) {
        this.categoryHandler = categoryHandler;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveCategory(
        @Valid @RequestBody CategoryRequestDto categoryRequestDto,
        HttpServletRequest request
    ) {
        categoryHandler.saveCategory(categoryRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
