package mr.springbootapi;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    Inject an instance of MenuItemRepository, which is used for interacting with the database to
    access and manage menu items
  */
  @Autowired private MenuItemRepository menuItemRepository;

  /*
   Define a method to handle GET requests to "/api/menu/all"
   This method will retrieve a list of all menu items from the database
  */
  @GetMapping("/all")
  public ResponseEntity<List<MenuItem>> listAllMenuItems() {
    // Fetch all menu items from the database using the repository
    List<MenuItem> menuItems = menuItemRepository.findAll();
    /*
     Create a response with the list of menu items and a success status code
     This response will be sent back to the client
    */
    return new ResponseEntity<>(menuItems, HttpStatus.OK);
  }

  /*
    Define a method to handle GET requests with a path variable "{id}"
    This allows you to retrieve a specific menu item by its ID.
  */
  @GetMapping("/{id}")
  public ResponseEntity<MenuItem> getById(@PathVariable int id) {
    /*
     Use the repository to find a menu item with the given ID.
     The result is wrapped in an Optional object to handle the possibility
     of not finding a matching item.
    */
    Optional<MenuItem> menuItemOptional = menuItemRepository.findById(id);
    // Check if the Optional object contains a value (meaning a menu item was found)
    if (menuItemOptional.isPresent()) {
      // If a menu item is found, extract it from the Optional object
      MenuItem menuItem = menuItemOptional.get();
      // Create a response with the found menu item and a success status code
      return new ResponseEntity<>(menuItem, HttpStatus.OK);
    } else {
      // If no menu item is found, create a response with a "NOT_FOUND" status code
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /*
    Define a method to handle POST requests to the base "/api/menu" URL
    This method is used to create a new menu item.
  */
  @PostMapping()
  public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
    /*
     Before saving the menu item, perform a validation to ensure the price is not negative.
     A negative price doesn't make sense, so rejecting such requests with a
    "BAD_REQUEST" status code is appropriate.
    */
    if (menuItem.getPrice() < 0) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    /*
     Use the repository to save the provided "menuItem" object to the database.
     The saved menu item is returned by the repository
    */
    MenuItem createdMenuItem = menuItemRepository.save(menuItem);
    // Create a response with the newly created menu item and a "CREATED" status code
    return new ResponseEntity<>(createdMenuItem, HttpStatus.CREATED);
  }

  /*
   Defines a method to handle PUT requests with a path variable "{id}". This method allows
   updating an existing menu item based on its ID.
  */
  @PutMapping("/{id}")
  public ResponseEntity<MenuItem> updateMenuItem(
      @PathVariable int id, @RequestBody MenuItem menuItem) {
    /*
     Tries to update the menu item using the repository's save method. The save method attempts
     to save the provided object, performing an update if the object with the same ID already
     exists.
    */
    try {
      MenuItem updateMenuItem = menuItemRepository.save(menuItem);
      // If the update is successful, return the updated menu item with an "ACCEPTED" status code.
      return new ResponseEntity<>(updateMenuItem, HttpStatus.ACCEPTED);
    } catch (EntityNotFoundException ex) {
      // If the update fails due to the menu item not being found, return a "NOT_FOUND" status code.
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /*
   Defines a method to handle DELETE requests with a path variable "{id}".
   This method allows deleting a menu item based on its ID.
  */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMenuItem(@PathVariable int id) {
    // Uses the repository's deleteById method to delete the menu item with the given ID.
    menuItemRepository.deleteById(id);
    /*
     Since no content is returned in the response body after deletion,
     create a successful response with a "NO_CONTENT" status code.
    */
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /*
   Defines a method to handle GET requests to "/api/menu/search". This method allows searching for
   menu items based on a keyword and optionally a category.
  */
  @GetMapping("/search")
  public ResponseEntity<List<MenuItem>> searchMenuItems(
      @RequestParam String keyword, @RequestParam(required = false) String category) {

    List<MenuItem> menuItems;

    // Search for menu items based on the provided keyword and category (if specified).
    if (category != null) {
      menuItems = menuItemRepository.searchByCategoryAndKeyword(category, keyword);
    } else {
      menuItems = menuItemRepository.searchByKeyword(keyword);
    }

    // Return a response containing the list of found menu items and an "OK" status code.
    return new ResponseEntity<>(menuItems, HttpStatus.OK);
  }

  @PostMapping("/bulk")
  public ResponseEntity<List<MenuItem>> addMenuItems(@RequestBody List<MenuItem> menuItems) {
    List<MenuItem> createdMenuItems = menuItemRepository.saveAll(menuItems);
    return new ResponseEntity<>(createdMenuItems, HttpStatus.CREATED);
  }
}
