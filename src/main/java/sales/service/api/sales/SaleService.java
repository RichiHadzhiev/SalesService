package sales.service.api.sales;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sales.service.api.Tier;

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
	
	public Tier updateTier(BigDecimal turnOver) {
		BigDecimal difference = new BigDecimal(1000);
		if(turnOver.compareTo(difference) >= 0 && 
				turnOver.compareTo(difference.multiply(new BigDecimal(2))) < 0) {
			return Tier.SILVER;
		}
		if(turnOver.compareTo(difference.multiply(new BigDecimal(2))) >= 0 &&
				turnOver.compareTo(difference.multiply(new BigDecimal(3))) < 0) {
			return Tier.GOLD;
		}
		return Tier.PLATINUM;
	}
}
