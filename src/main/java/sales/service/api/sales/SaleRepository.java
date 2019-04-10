package sales.service.api.sales;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<Sale, Long>{
	
	public List<Sale> findByCustomerId(Long customerId);
	
	@Query("select s from Sale s where price = (select min(price) from Sale)")
	public Sale leastExpensive();
}
