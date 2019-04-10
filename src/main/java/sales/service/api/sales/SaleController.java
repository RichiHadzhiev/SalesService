package sales.service.api.sales;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sales.service.api.customers.Customer;

@RestController
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@RequestMapping("/customers/{customerId}/sales")
	public List<Sale> getAllSales(@PathVariable Long customerId) {
		return saleService.getAllSales(customerId);
	}
	
	@RequestMapping("/customers/{customerId}/sales/{id}")
	public Sale getSale(@PathVariable Long id) {
		return saleService.getSale(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/customers/{customerId}/sales")
	public void addSale(@RequestBody Sale sale, @PathVariable Long customerId) {
		Customer customer = new Customer();
		customer.setId(customerId);
		sale.setCustomer(customer);
		sale.setDiscount(new BigDecimal(10));
		sale.setDiscountedPrice(sale.getPrice().subtract(sale.getPrice().multiply(sale.getDiscount().divide(new BigDecimal(100)))));
		saleService.addSale(sale);
		//System.out.println(customer.getDiscount());
	}
	
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
	
	@RequestMapping("/customer/{cutomerId}/sales/min_price")
	public Sale getMinPrice() {
		return saleService.getMinPrice();
	}
}
