package mr.springbootapi.controller;

import java.util.List;

import mr.springbootapi.entity.MenuItem;
import mr.springbootapi.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Declare this class as a controller for handling web requests
@RequestMapping("/api/menu") // Specifying that it will handle requests related to "/api/menu"
public class MenuController {
    /*
      Inject an instance of MenuItemService, which is used for interacting with the repository to
      access and manage menu items.
    */
    private final MenuItemService menuItemService;

    public MenuController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    /*
     Define a method to handle GET requests to "/api/menu/all"
     This method will retrieve a list of all menu items from the database
    */
    @GetMapping("/all")
    public ResponseEntity<List<MenuItem>> listAllMenuItems() {
        return ResponseEntity.ok(menuItemService.listAllMenuItems());
    }

    /*
      Define a method to handle GET requests with a path variable "{id}"
      This allows you to retrieve a specific menu item by its ID.
    */
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getById(@PathVariable int id) {
        return ResponseEntity.ok(menuItemService.getById(id));
    }

    /*
      Define a method to handle POST requests to the base "/api/menu" URL
      This method is used to create a new menu item.
    */
    @PostMapping()
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        return ResponseEntity.ok(menuItemService.addMenuItem(menuItem));
    }

    /*
     Defines a method to handle PUT requests with a path variable "{id}". This method allows
     updating an existing menu item based on its ID.
    */
    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable int id, @RequestBody MenuItem menuItem) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, menuItem));
    }

    /*
     Defines a method to handle DELETE requests with a path variable "{id}".
     This method allows deleting a menu item based on its ID.
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable int id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*
     Defines a method to handle GET requests to "/api/menu/search". This method allows searching for
     menu items based on a keyword and optionally a category.
    */
    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItems(
            @RequestParam String keyword, @RequestParam(required = false) String category) {
        return ResponseEntity.ok(menuItemService.searchMenuItems(keyword, category));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<MenuItem>> addMenuItems(@RequestBody List<MenuItem> menuItems) {
        return ResponseEntity.ok(menuItemService.addMenuItems(menuItems));
    }
}
