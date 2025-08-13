package com.tramite.infrastructure.repository.product;

import com.tramite.domain.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = """
        SELECT 
            p.id_product,
            p.name,
            p.description,
            p.price,
            p.active
        FROM dbo.products p
        WHERE p.active = 1
        ORDER BY p.name
        """, nativeQuery = true)
    List<Object[]> findAllActiveProducts();
}
