package sales.service.api.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sales.service.api.Tier;
import sales.service.api.customers.Customer;
import sales.service.api.customers.CustomerService;
import sales.service.api.dto.SaleDTO;

@RestController
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customers/{customerId}/sales")
	public List<SaleDTO> getAllSales(@PathVariable Long customerId) {
		List<SaleDTO> dtos = new ArrayList<SaleDTO>();
		List<Sale> sales = saleService.getAllSales(customerId);
		for(Sale sale : sales){
			dtos.add(SaleDTO.entityToDTO(sale));
		}
		return dtos;
	}
	
	@RequestMapping("/customers/{customerId}/sales/{id}")
	public Sale getSale(@PathVariable Long id) {
		return saleService.getSale(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/customers/{customerId}/sales")
	public void addSale(@RequestBody Sale sale, @PathVariable Long customerId) throws Exception {
		Customer customer = customerService.getCustomer(customerId);
		sale.setDiscount(customer.getDiscount());
		sale.setDiscountedPrice(sale.getPrice().subtract(sale.getPrice().multiply(sale.getDiscount().divide(new BigDecimal(100)))));
		customer.setTurnOver(customer.getTurnOver().add(sale.getDiscountedPrice()));
		if((customer.getTurnOver().compareTo(new BigDecimal(1000)) >= 0 &&
				customer.getTurnOver().compareTo(new BigDecimal(3000)) < 0) ||
				(customer.getTier() != Tier.PLATINUM && //To skip calling this method if the tier is already platinum.
				customer.getTurnOver().compareTo(new BigDecimal(3000)) >= 0)) { //Without this line it would set the tier to platinum if the updated turnover is below 1000.
			customer.setTier(saleService.updateTier(customer.getTurnOver())); 
			customer.setDiscount(customerService.discountFromTier(customer.getTier())); 
		}
		sale.setCustomer(customer);
		saleService.addSale(sale);
	}
	
	//needs to be corrected or deleted
	@RequestMapping(method = RequestMethod.PUT, value = "/customers/{customerId}/sales/{id}")
	public void updateSale(@RequestBody Sale sale, @PathVariable Long id, @PathVariable Long customerId) {
		Customer customer = new Customer();
		customer.setId(customerId);
		sale.setCustomer(customer);
		saleService.updateSale(id, sale);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/customers/{customerId}/sales/{id}")
	public void deleteSale(@PathVariable Long id) {
		saleService.deleteSale(id);
	}
	
	@RequestMapping("/customers/{cutomerId}/sales/min_price")
	public Sale getMinPrice() {
		return saleService.getMinPrice();
	}
}
