package sales.service.api.customers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers(){
		List<Customer> customers = new ArrayList<Customer>();
		customerRepository.findAll().forEach(customers::add);
		return customers;
	}
	
	public Customer getCustomer(Long id) {
		return customerRepository.findOne(id);
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
}