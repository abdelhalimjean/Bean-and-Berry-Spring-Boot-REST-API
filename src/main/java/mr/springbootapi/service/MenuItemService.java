package mr.springbootapi.service;

import mr.springbootapi.entity.MenuItem;
import mr.springbootapi.exception.InvalidPriceException;
import mr.springbootapi.exception.ItemNotFoundException;
import mr.springbootapi.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    /*
      Inject an instance of MenuItemRepository, which is used for interacting with the database and manage the table menu_item.
    */
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    /*
      Method to add a new menu item.
      Throws an InvalidPriceException if the price of the menu item is negative.
      Saves the menu item to the database using MenuItemRepository.
    */
    public MenuItem addMenuItem(MenuItem menuItem) {
        if (menuItem.getPrice() < 0) {
            throw new InvalidPriceException("Invalid price");
        }
        return menuItemRepository.save(menuItem);
    }

    /*
      Method to retrieve all menu items from the database using MenuItemRepository.
    */
    public List<MenuItem> listAllMenuItems() {
        return menuItemRepository.findAll();
    }

    /*
      Method to retrieve a menu item by its ID.
      Uses MenuItemRepository to find the menu item with the given ID.
      Throws an ItemNotFoundException if no menu item is found with the given ID.
    */
    public MenuItem getById(int id) {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findById(id);
        if (menuItemOptional.isPresent()) {
            return menuItemOptional.get();
        } else {
            throw new ItemNotFoundException("Item not found");
        }
    }

    /*
      Method to delete a menu item by its ID.
      Throws an ItemNotFoundException if no menu item is found with the given ID.
    */
    public void deleteMenuItem(int id) {
        if (menuItemRepository.findById(id).isEmpty()) {
            throw new ItemNotFoundException("Item Not Found");
        }
        menuItemRepository.deleteById(id);
    }

    /*
      Method to update an existing menu item.
      Throws an ItemNotFoundException if no menu item is found with the given ID.
      Saves the updated menu item to the database using MenuItemRepository.
    */
    public MenuItem updateMenuItem(int id, MenuItem menuItem) {
        Optional<MenuItem> existingMenuItemOptional = menuItemRepository.findById(id);
        if (existingMenuItemOptional.isPresent()) {
            return menuItemRepository.save(menuItem);
        } else {
            throw new ItemNotFoundException("MenuItem not found with id " + id);
        }
    }

    /*
      Method to add multiple menu items at once.
      Saves all menu items to the database using MenuItemRepository.
    */
    public List<MenuItem> addMenuItems(List<MenuItem> menuItems) {
        return menuItemRepository.saveAll(menuItems);
    }

    /*
      Method to search for menu items based on a keyword and an optional category.
      If a category is provided, searches for menu items in that category matching the keyword.
      If no category is provided, searches for menu items across all categories matching the keyword.
    */
    public List<MenuItem> searchMenuItems(String keyword, String category) {
        if (category != null) {
            return menuItemRepository.searchByCategoryAndKeyword(category, keyword);
        } else {
            return menuItemRepository.searchByKeyword(keyword);
        }
    }
}
