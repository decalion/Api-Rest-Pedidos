package com.icaballero.orderapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icaballero.orderapi.entity.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

	public List<OrderLine> findByProduct(Long id);


	@Modifying
	@Transactional
	@Query(value = "delete from ORDER_LINES where FK_PRODUCT = :productid", nativeQuery = true)
	public void deleteByProduct(@Param("productid") Long productid);

}
