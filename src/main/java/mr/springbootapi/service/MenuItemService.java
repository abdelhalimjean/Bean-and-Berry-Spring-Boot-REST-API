package mr.springbootapi.service;

import mr.springbootapi.entity.MenuItem;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MenuItemService {
    MenuItem addMenuItem(MenuItem menuItem);

    List<MenuItem> listAllMenuItems();

    MenuItem getById(int id);

    void deleteMenuItem(int id);

    MenuItem updateMenuItem(int id, MenuItem menuItem);

    List<MenuItem> addMenuItems(List<MenuItem> menuItems);

    List<MenuItem> searchMenuItems(String keyword, String category);
}
