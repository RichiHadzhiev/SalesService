package sales.service.api.customers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sales.service.api.Tier;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers(){
		List<Customer> customers = new ArrayList<Customer>();
		customerRepository.findAll().forEach(customers::add);
		return customers;
	}
	
	public Customer getCustomer(Long id) throws Exception {
		Customer customer = customerRepository.findOne(id);
		if (customer == null) {
			throw new Exception("There is no customer with id " + id);
		}
		return customer;
	}

	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public void updateCustomer(Long id, Customer customer) {
		customerRepository.save(customer);
	}
	
	public void deleteCustomer(Long id) {
		customerRepository.delete(id);
	}
	
	public BigDecimal discountFromTier(Tier tier) {
		BigDecimal discount;
		switch(tier) {
		case BRONZE:
			discount = new BigDecimal(10);
			break;
		case SILVER:
			discount = new BigDecimal(15);
			break;
		case GOLD:
			discount = new BigDecimal(20);
			break;
		default:
			discount = new BigDecimal(25);
		}
		return discount;
	}
}
