package org.ruby.productservice.repositories;

import org.ruby.productservice.models.Category;
import org.ruby.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long productId);

    List<Product> findByIdAndTitle(Long productId, String title);

    Optional<Product> findByTitle(String title);

    List<Product> findByTitleContainsIgnoreCase(String title);

    Page<Product> findByTitleContainsIgnoreCase(String title, Pageable pageable);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByCategory(Category category);

    List<Product> findAllByCategory_Id(Long categoryId);

    //Join query
    List<Product> findAllByCategory_Title(String categoryTitle);

    //    @Query("select title from products where id = ?")
    //    Optional<Product> findProductTitleById(Long productId);

    Product save(Product product);
    //Update + Insert => Upsert

    List<Product> findAllByOrderByPriceDesc();

    //    @Query("select p from products p where p.category.id = ?1")
    @Query("select p from Product p where p.id = ?1")
    Optional<Product> findProductWithGivenIdHQL(Long productId);

    @Query(value = "select * from products p where p.id= :id", nativeQuery = true)
    Optional<Product> findProductWithGivenIdSQL(@Param("id") Long productId);

}

