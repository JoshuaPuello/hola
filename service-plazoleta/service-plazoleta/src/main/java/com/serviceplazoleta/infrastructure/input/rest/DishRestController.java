package com.serviceplazoleta.infrastructure.input.rest;

import com.serviceplazoleta.application.dto.DishRequestDto;
import com.serviceplazoleta.application.dto.DishResponseDto;
import com.serviceplazoleta.application.dto.DishUpdateRequestDto;
import com.serviceplazoleta.application.handler.IDishHandler;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("dish")
public class DishRestController {

    private final IDishHandler dishHandler;

    public DishRestController(IDishHandler dishHandler) {
        this.dishHandler = dishHandler;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto, HttpServletRequest request) throws Exception {
        Long propietarioId = getUserIdAndValidateAccess(request, "PROPIETARIO");
        dishHandler.saveDish(dishRequestDto, propietarioId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishRequestDto> updateDish(
        @PathVariable(value = "id") Long dishId,
        @RequestBody DishUpdateRequestDto dishUpdateRequestDto,
        Long restaurantId,
        HttpServletRequest request
    ) throws Exception {
        Long propietarioId = getUserIdAndValidateAccess(request, "PROPIETARIO");
        dishHandler.updateDish(dishId, dishUpdateRequestDto, restaurantId, request, propietarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/activate/{enableDisable}")
    public ResponseEntity<DishRequestDto> enableDisable(@PathVariable(value = "id") Long id, @PathVariable(value = "enableDisable") Long enableDisble) {
        dishHandler.enableDisableDish(id, enableDisble);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        return ResponseEntity.ok(dishHandler.getAllDishes());
    }

    @GetMapping("/restaurant/{idRestaurant}/page/{page}/size/{size}")
    public ResponseEntity<List<DishResponseDto>> getAllDishByRestaurant(
        @PathVariable(value = "idRestaurant") Long idRestaurant,
        @PathVariable(value = "page") Integer page,
        @PathVariable(value = "size") Integer size,
        @RequestParam(value = "category", required = false) String category) {
        return ResponseEntity.ok(dishHandler.findAllByRestaurantId(idRestaurant, page, size, category));
    }

    private Long getUserIdAndValidateAccess(HttpServletRequest request, String requiredRole) throws Exception {
        Long userId = Long.valueOf((String) request.getAttribute("userId"));
        String token = request.getHeader("Authorization");
        dishHandler.validateAccess(userId, requiredRole, token);
        return userId;
    }
}
