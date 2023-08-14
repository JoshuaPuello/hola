package com.serviceplazoleta.infrastructure.input.rest;

import com.serviceplazoleta.application.dto.RestaurantPaginationResponseDto;
import com.serviceplazoleta.application.dto.RestaurantRequestDto;
import com.serviceplazoleta.application.dto.RestaurantResponseDto;
import com.serviceplazoleta.application.handler.IRestaurantHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("restaurant")
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    public RestaurantRestController(IRestaurantHandler restaurantHandler) {
        this.restaurantHandler = restaurantHandler;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto, HttpServletRequest request) throws Exception {
        Long propietarioId = getUserIdAndValidateAccess(request, "ADMIN");
        restaurantHandler.saveRestaurant(restaurantRequestDto, propietarioId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Este va a ser llamado por el microservicio de usuarios a traves del feign client.
    @PostMapping("/{id}/linkEmployee")
    public ResponseEntity<Void> linkEmployee(
        @PathVariable("id") Long restaurantId,
        @RequestParam("employeeId") Long employeeId,
        HttpServletRequest request
    ) throws Exception {
        getUserIdAndValidateAccess(request, "PROPIETARIO");
        restaurantHandler.linkEmployee(restaurantId, employeeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/propietario/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByIdPropietario(
        @PathVariable("id") Long idPropietario
    ) throws Exception {
        return ResponseEntity.ok(restaurantHandler.getRestaurantByIdPropietario(idPropietario));
    }

    @GetMapping("/")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants(){
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<List<RestaurantPaginationResponseDto>> getAllRestaurantPagination(
        @PathVariable(value = "page") Integer page,
        @PathVariable(value = "size") Integer size) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantPaginationResponseDto(page, size));
    }

    private Long getUserIdAndValidateAccess(HttpServletRequest request, String requiredRole) throws Exception {
        Long userId = Long.valueOf((String) request.getAttribute("userId"));
        String token = request.getHeader("Authorization");
        restaurantHandler.validateAccess(userId, requiredRole, token);
        return userId;
    }
}
