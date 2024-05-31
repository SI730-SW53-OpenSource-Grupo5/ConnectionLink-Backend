package com.connectionlink.backend.category.interfaces.rest;

import com.connectionlink.backend.category.domain.model.aggregates.Category;
import com.connectionlink.backend.category.domain.model.queries.GetAllCategoryQuery;
import com.connectionlink.backend.category.domain.model.queries.GetCategoryByIdQuery;
import com.connectionlink.backend.category.domain.services.CategoryCommandService;
import com.connectionlink.backend.category.domain.services.CategoryQueryService;
import com.connectionlink.backend.category.interfaces.rest.resources.CategoryResource;
import com.connectionlink.backend.category.interfaces.rest.resources.CreateCategoryResource;
import com.connectionlink.backend.category.interfaces.rest.resources.UpdateCategoryResource;
import com.connectionlink.backend.category.interfaces.rest.transform.CategoryResourceFromEntityAssembler;
import com.connectionlink.backend.category.interfaces.rest.transform.CreateCategoryCommandFromResourceAssembler;
import com.connectionlink.backend.category.interfaces.rest.transform.UpdateCategoryCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryCommandService categoryCommandService;
    private final CategoryQueryService categoryQueryService;

    public CategoryController(CategoryCommandService categoryCommandService, CategoryQueryService categoryQueryService) {
        this.categoryCommandService = categoryCommandService;
        this.categoryQueryService = categoryQueryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResource> addCategory(@RequestBody CreateCategoryResource resource) {
        Optional<Category> category = this.categoryCommandService.handle(CreateCategoryCommandFromResourceAssembler.toCommandFromResource(resource));
        return category.map(source -> ResponseEntity.ok(CategoryResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResource> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryResource resource) {
        Optional<Category> category = this.categoryCommandService.handle(UpdateCategoryCommandFromResourceAssembler.toCommandFromResource(resource), id);
        return category.map(source -> ResponseEntity.ok(CategoryResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResource> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = this.categoryQueryService.handle(new GetCategoryByIdQuery(id));

        return category.map(source -> ResponseEntity.ok(CategoryResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryResource>> getAllCategory() {
        List<Category> categories = this.categoryQueryService.handle(new GetAllCategoryQuery());

        if(categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CategoryResource> categoriesResource = categories.stream().map(CategoryResourceFromEntityAssembler::toResourceFromEntity).toList();

        return ResponseEntity.ok(categoriesResource);
    }
}
