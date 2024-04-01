package mr.springbootapi.controller;

import java.util.List;

import mr.springbootapi.dto.ApiResponse;
import mr.springbootapi.entity.MenuItem;
import mr.springbootapi.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuItemService menuItemService;

    // Constructor injection of MenuItemService
    public MenuController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    // Handler for listing all menu items
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> listAllMenuItems() {
        // Call service method to retrieve all menu items
        List<MenuItem> menuItems = menuItemService.listAllMenuItems();
        // Return ApiResponse with HTTP status code OK (200), success message, and menu items
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Success", menuItems));
    }

    // Handler for retrieving a menu item by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable int id) {
        // Call service method to retrieve the menu item by ID
        MenuItem menuItem = menuItemService.getById(id);
        // Return ApiResponse with HTTP status code OK (200), success message, and the retrieved menu item
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Success", menuItem));
    }

    // Handler for adding a new menu item
    @PostMapping()
    public ResponseEntity<ApiResponse> addMenuItem(@RequestBody MenuItem menuItem) {
        // Call service method to add the new menu item
        MenuItem addedMenuItem = menuItemService.addMenuItem(menuItem);
        // Return ApiResponse with HTTP status code OK (200), success message, and the added menu item
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Menu item added successfully", addedMenuItem));
    }

    // Handler for updating an existing menu item
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMenuItem(
            @PathVariable int id, @RequestBody MenuItem menuItem) {
        // Call service method to update the menu item
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(id, menuItem);
        // Return ApiResponse with HTTP status code OK (200), success message, and the updated menu item
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Menu item updated successfully", updatedMenuItem));
    }

    // Handler for deleting a menu item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMenuItem(@PathVariable int id) {
        // Call service method to delete the menu item by ID
        menuItemService.deleteMenuItem(id);
        // Return ApiResponse with HTTP status code OK (200) and success message
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Menu item deleted successfully", null));
    }

    // Handler for searching menu items by keyword and category
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchMenuItems(
            @RequestParam String keyword, @RequestParam(required = false) String category) {
        // Call service method to search for menu items based on keyword and category
        List<MenuItem> menuItems = menuItemService.searchMenuItems(keyword, category);
        // Return ApiResponse with HTTP status code OK (200), success message, and the matched menu items
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Success", menuItems));
    }

    // Handler for adding multiple menu items in bulk
    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse> addMenuItems(@RequestBody List<MenuItem> menuItems) {
        // Call service method to add multiple menu items
        List<MenuItem> addedMenuItems = menuItemService.addMenuItems(menuItems);
        // Return ApiResponse with HTTP status code OK (200), success message, and the added menu items
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Menu items added successfully", addedMenuItems));
    }
}
