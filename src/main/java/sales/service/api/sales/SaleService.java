package sales.service.api.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	public List<Sale> getAllSales(Long customerId){
		return saleRepository.findByCustomerId(customerId);
	}
	
	public Sale getSale(Long id) {
		return saleRepository.findOne(id);
	}

	public void addSale(Sale sale) {
		saleRepository.save(sale);
	}
	
	public void updateSale(Long id, Sale sale) {
		saleRepository.save(sale);
	}
	
	public void deleteSale(Long id) {
		saleRepository.delete(id);
	}
	
	public Sale getMinPrice() {
		return saleRepository.leastExpensive();
	}
}
