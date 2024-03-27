package mr.springbootapi;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

  List<MenuItem> findAllByNameContainingIgnoreCaseOrIngredientsContainingIgnoreCase(
      String name, String ingredient);
}
