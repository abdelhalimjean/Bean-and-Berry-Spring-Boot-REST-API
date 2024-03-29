package mr.springbootapi;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

  @Query(value = "select * from menu_item m where m.hot = ?1 ", nativeQuery = true)
  List<MenuItem> searchByTemperatureNativeSQL(boolean hot);

  @Query(value = "select m from MenuItem m where m.hot = :hot ")
  List<MenuItem> searchByTemperatureJPAQuery(boolean hot);

  List<MenuItem> findByHot(boolean hot);

  @Query(
      value =
          """
            select * from menu_item m
            where
              lower(m.name) like concat('%',lower(:keyword),'%') or lower(m.ingredients) like concat('%',lower(:keyword),'%')
      """,
      nativeQuery = true)
  List<MenuItem> searchByKeyword(String keyword);

  @Query(
      value =
          """
                    select * from menu_item m
                    where
                      lower(m.category) = lower(:category)
                      and
                      (lower(m.name) like concat('%',lower(:keyword),'%') or lower(m.ingredients) like concat('%',lower(:keyword),'%'))
              """,
      nativeQuery = true)
  List<MenuItem> searchByCategoryAndKeyword(String category, String keyword);
}
