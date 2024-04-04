package mr.springbootapi.service;

import mr.springbootapi.entity.MenuItem;
import mr.springbootapi.exception.InvalidPriceException;
import mr.springbootapi.exception.ItemNotFoundException;
import mr.springbootapi.repository.MenuItemRepository;
import mr.springbootapi.service.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Test class for MenuItemService
//Unit Test Tutorial : https://youtu.be/uGZQdD9IpQc?si=Y6cE3XihoiNvG6e3
class MenuItemServiceTest {

    // Mocking the MenuItemRepository
    @Mock
    private MenuItemRepository menuItemRepository;

    // Injecting mocks into MenuItemService
    @InjectMocks
    private MenuItemService menuItemService;

    // Setting up Mockito annotations before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testing adding an item
    @Test
    void should_add_item() {
        // Given
        MenuItem menuItem = new MenuItem("Item", 10.0f);

        // Mocking behavior of menuItemRepository.save() method
        when(menuItemRepository.save(menuItem)).thenReturn(menuItem);

        // When
        MenuItem result = menuItemService.addMenuItem(menuItem);

        // Then
        assertNotNull(result);
        assertEquals(menuItem.getName(), result.getName());
        assertEquals(menuItem.getPrice(), result.getPrice());
    }

    // Testing throwing exception when trying to add MenuItem with invalid price
    @Test
    void should_throw_when_trying_to_add_menuItem_with_invalid_price() {
        // Given
        MenuItem menuItem = new MenuItem("Item", -10.0f);

        // When & Then
        assertThrows(InvalidPriceException.class, () -> menuItemService.addMenuItem(menuItem));
    }

    // Testing returning an item with a specific id
    @Test
    void should_return_item_with_id() {
        // Given
        int id = 1;
        MenuItem menuItem = new MenuItem("Item", 10.0f);

        // Mocking behavior of menuItemRepository.findById() method
        when(menuItemRepository.findById(id)).thenReturn(Optional.of(menuItem));

        // When
        MenuItem result = menuItemService.getById(id);

        // Then
        assertNotNull(result);
        assertEquals(menuItem.getName(), result.getName());
        assertEquals(menuItem.getPrice(), result.getPrice());
    }

    // Testing throwing an exception when trying to fetch an item that does not exist
    @Test
    void should_throw_when_trying_to_fetch_item_that_not_exist() {
        // Given
        int id = 1;

        // Mocking behavior of menuItemRepository.findById() method
        when(menuItemRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ItemNotFoundException.class, () -> menuItemService.getById(id));
    }

    // Testing deleting a menu item
    @Test
    void should_delete_menu_item() {
        // Given
        int id = 1;
        MenuItem menuItem = new MenuItem("Item", 10.0f);

        // Mocking behavior of menuItemRepository.findById() method
        when(menuItemRepository.findById(id)).thenReturn(Optional.of(menuItem));

        // When
        menuItemService.deleteMenuItem(id);

        // Then
        verify(menuItemRepository, times(1)).deleteById(id);
    }

    // Testing throwing an exception when trying to delete an item that does not exist
    @Test
    void should_throw_when_trying_to_delete_item_that_not_exist() {
        // Given
        int id = 1;

        // Mocking behavior of menuItemRepository.findById() method
        when(menuItemRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ItemNotFoundException.class, () -> menuItemService.deleteMenuItem(id));
    }

    // Testing updating a menu item
    @Test
    void should_update_menu_item() {
        // Given
        int id = 1;
        MenuItem existingMenuItem = new MenuItem("Existing Item", 10.0f);
        MenuItem updatedMenuItem = new MenuItem("Updated Item", 15.0f);

        // Mocking behavior of menuItemRepository.findById() and menuItemRepository.save() methods
        when(menuItemRepository.findById(id)).thenReturn(Optional.of(existingMenuItem));
        when(menuItemRepository.save(updatedMenuItem)).thenReturn(updatedMenuItem);

        // When
        MenuItem result = menuItemService.updateMenuItem(id, updatedMenuItem);

        // Then
        assertNotNull(result);
        assertEquals(updatedMenuItem.getName(), result.getName());
        assertEquals(updatedMenuItem.getPrice(), result.getPrice());
    }

    // Testing throwing an exception when trying to update an item that does not exist
    @Test
    void should_throw_when_trying_to_update_item_that_not_exist() {
        // Given
        int id = 1;
        MenuItem menuItem = new MenuItem("Item", 10.0f);
        when(menuItemRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ItemNotFoundException.class, () -> menuItemService.updateMenuItem(id, menuItem));
    }

    // Testing adding menu items in bulk
    @Test
    void should_add_menuItem_in_bulk() {
        // Given
        List<MenuItem> menuItems = Collections.singletonList(new MenuItem("Item", 10.0f));

        // Mocking behavior of menuItemRepository.saveAll() method
        when(menuItemRepository.saveAll(menuItems)).thenReturn(menuItems);

        // When
        List<MenuItem> result = menuItemService.addMenuItems(menuItems);

        // Then
        assertNotNull(result);
        assertEquals(menuItems.size(), result.size());
    }

    // Testing returning items matching category and keyword
    @Test
    void should_return_items_matching_category_and_keyword() {
        // Given
        String keyword = "keyword";
        String category = "category";
        List<MenuItem> menuItems = Collections.singletonList(new MenuItem("Item", 10.0f));

        // Mocking behavior of menuItemRepository.searchByCategoryAndKeyword() method
        when(menuItemRepository.searchByCategoryAndKeyword(category, keyword)).thenReturn(menuItems);

        // When
        List<MenuItem> result = menuItemService.searchMenuItems(keyword, category);

        // Then
        assertNotNull(result);
        assertEquals(menuItems.size(), result.size());
    }

    // Testing returning item matching keyword if no category present
    @Test
    void should_return_item_matching_keyword_if_no_category_present() {
        // Given
        String keyword = "keyword";
        List<MenuItem> menuItems = Collections.singletonList(new MenuItem("Item", 10.0f));

        // Mocking behavior of menuItemRepository.searchByKeyword() method
        when(menuItemRepository.searchByKeyword(keyword)).thenReturn(menuItems);

        // When
        List<MenuItem> result = menuItemService.searchMenuItems(keyword, null);

        // Then
        assertNotNull(result);
        assertEquals(menuItems.size(), result.size());
    }
}