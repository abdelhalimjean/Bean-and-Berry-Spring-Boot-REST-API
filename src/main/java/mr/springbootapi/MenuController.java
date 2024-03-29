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

@RestController
@RequestMapping("/api/menu")
public class MenuController {
  // dependency injection : basically being able to use MenuItemService here
  @Autowired private MenuItemRepository menuItemRepository;

  @GetMapping("/all")
  public ResponseEntity<List<MenuItem>> listAllMenuItems() {
    List<MenuItem> menuItems = menuItemRepository.findAll();
    return new ResponseEntity<>(menuItems, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MenuItem> getById(@PathVariable int id) {

    Optional<MenuItem> menuItemOptional = menuItemRepository.findById(id);
    if (menuItemOptional.isPresent()) {
      MenuItem menuItem = menuItemOptional.get();
      return new ResponseEntity<>(menuItem, HttpStatus.OK);
    } else {

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping()
  public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
    MenuItem createdMenuItem = menuItemRepository.save(menuItem);
    return new ResponseEntity<>(createdMenuItem, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MenuItem> updateMenuItem(
      @PathVariable int id, @RequestBody MenuItem menuItem) {
    try {
      MenuItem updateMenuItem = menuItemRepository.save(menuItem);
      return new ResponseEntity<>(updateMenuItem, HttpStatus.ACCEPTED);
    } catch (EntityNotFoundException ex) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMenuItem(@PathVariable int id) {
    menuItemRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/search")
  public ResponseEntity<List<MenuItem>> searchMovies(
      @RequestParam String keyword, @RequestParam(required = false) String category) {
    List<MenuItem> menuItems;

    if (category != null) {
      menuItems = menuItemRepository.searchByCategoryAndKeyword(category, keyword);
    } else {
      menuItems = menuItemRepository.searchByKeyword(keyword);
    }
    return new ResponseEntity<>(menuItems, HttpStatus.OK);
  }

  @PostMapping("/bulk")
  public ResponseEntity<List<MenuItem>> addMenuItems(@RequestBody List<MenuItem> menuItems) {
    List<MenuItem> createdMenuItems = menuItemRepository.saveAll(menuItems);
    return new ResponseEntity<>(createdMenuItems, HttpStatus.CREATED);
  }
}
