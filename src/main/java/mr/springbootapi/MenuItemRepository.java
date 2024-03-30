package mr.springbootapi;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
 This interface is marked with @Repository annotation, indicating that it's a Spring Data JPA repository.
 Spring will automatically detect and manage this bean during application startup.
*/
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

  /*
   This method uses a custom query defined with the @Query annotation.
   The annotation specifies a native SQL query (nativeQuery = true) to search for menu items based on the "hot" property.
   The placeholder ?1 will be replaced with the provided boolean value for "hot".
  */
  @Query(value = "select * from menu_item m where m.hot = ?1 ", nativeQuery = true)
  List<MenuItem> searchByTemperatureNativeSQL(boolean hot);

  /*
   This method uses a custom query defined with the @Query annotation.
   The annotation specifies a Spring Data JPA query to search for menu items based on the "hot" property.
   The parameter name ":hot" will be automatically mapped to the method argument "hot".
  */
  @Query(value = "select m from MenuItem m where m.hot = :hot ")
  List<MenuItem> searchByTemperatureJPAQuery(boolean hot);

  /*
   This method attempts to find menu items by the "hot" property using the default naming convention provided by Spring Data JPA.
   However, it's generally recommended to use explicit @Query annotations for better clarity and control over the queries especially if the query is complex.
  */
  List<MenuItem> findByHot(boolean hot);

  /*
   This method uses a custom query defined with the @Query annotation.
   The annotation specifies a native SQL query (nativeQuery = true) to search for menu items based on a keyword.
   The query searches for items where the lowercase version of "name" or "ingredients" column
   contains the provided lowercase keyword (using wildcards '%').
  */
  @Query(
      value =
          """
            select * from menu_item m
            where
              lower(m.name) like concat('%',lower(:keyword),'%') or lower(m.ingredients) like concat('%',lower(:keyword),'%')
      """,
      nativeQuery = true)
  List<MenuItem> searchByKeyword(String keyword);

  /*
   This method uses a custom query defined with the @Query annotation.
   The annotation specifies a native SQL query (nativeQuery = true) to search for menu items based on a category and a keyword.
   The query searches for items where the lowercase version of "category" matches the
   provided category AND (the lowercase version of "name" or "ingredients" contains the provided lowercase keyword using wildcards '%').
  */
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
