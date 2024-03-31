package mr.springbootapi.service;

import mr.springbootapi.entity.MenuItem;
import mr.springbootapi.exception.InvalidPriceException;
import mr.springbootapi.exception.ItemNotFoundException;
import mr.springbootapi.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem addMenuItem(MenuItem menuItem) {
        if (menuItem.getPrice() < 0) {
            throw new InvalidPriceException("Invalid price");
        }
        return menuItemRepository.save(menuItem);
    }

    @Override
    public List<MenuItem> listAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem getById(int id) {
        return menuItemRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
    }

    @Override
    public void deleteMenuItem(int id) {
        if (menuItemRepository.findById(id).isEmpty()) {
            throw new ItemNotFoundException("Item Not Found");
        }
        menuItemRepository.deleteById(id);
    }

    @Override
    public MenuItem updateMenuItem(int id, MenuItem menuItem) {
        Optional<MenuItem> existingMenuItemOptional = menuItemRepository.findById(id);
        if (existingMenuItemOptional.isPresent()) {
            return menuItemRepository.save(menuItem);
        } else {
            throw new ItemNotFoundException("MenuItem not found with id " + id);
        }
    }

    @Override
    public List<MenuItem> addMenuItems(List<MenuItem> menuItems) {
        return menuItemRepository.saveAll(menuItems);
    }

    @Override
    public List<MenuItem> searchMenuItems(String keyword, String category) {
        return category != null ? menuItemRepository
                .searchByCategoryAndKeyword(category, keyword) :
                menuItemRepository.searchByKeyword(keyword);
    }
}
