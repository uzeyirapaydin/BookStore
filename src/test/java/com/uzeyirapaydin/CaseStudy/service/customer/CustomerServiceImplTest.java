package com.uzeyirapaydin.CaseStudy.service.customer;

import com.uzeyirapaydin.CaseStudy.dto.CustomerDTO;
import com.uzeyirapaydin.CaseStudy.entity.Customer;
import com.uzeyirapaydin.CaseStudy.mapper.CustomerEntityMapper;
import com.uzeyirapaydin.CaseStudy.repository.CustomerRepository;
import com.uzeyirapaydin.CaseStudy.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;


    @Spy
    CustomerEntityMapper entityMapper;

    @Spy
    PasswordEncoder passwordEncoder;

    @Mock
    CustomerRepository customerRepository;



    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contextLoads() {
        assertThat(customerService).isNotNull();
        assertThat(customerRepository).isNotNull();
    }
//
//    @Test
//    public void addCustomer() {
//        UUID customer1_Id = UUID.randomUUID();
//        Customer customer1 = Customer.builder()
//                .id(customer1_Id)
//                .name("name")
//                .surname("surname")
//                .password("hashed")
//                .email("email")
//                .build();
//
//        CustomerDTO res = customerService.create(entityMapper.toDTO(customer1));
//        assertEquals("name", res.getName());
//        assertEquals("surname", res.getSurname());
//        assertEquals(res.getPassword(), passwordEncoder.encode(customer1.getPassword()));
//        assertEquals("email", res.getEmail());
//
//    }
//
//
//    @Test
//    public void findByEmail() {
//
//        UUID customer1_Id = UUID.randomUUID();
//        Customer customer1 = Customer.builder()
//                .id(customer1_Id)
//                .name("name")
//                .surname("surname")
//                .password("hashed")
//                .email("email")
//                .build();
//
//        Customer customerRet = Customer.builder()
//                .id(customer1_Id)
//                .name("name")
//                .surname("surname")
//                .password("password")
//                .email("email")
//                .build();
//
//        Mockito.when(customerRepository.findByEmail("email")).thenReturn(Optional.of(customerRet));
//
//        CustomerDTO res = customerService.getByEmail("email").get();
//
//
//        assertEquals("email", res.getEmail());
//        assertEquals("name", res.getName());
//        assertEquals("surname", res.getSurname());
//
//        assertEquals("password", res.getPassword());
//
//    }
}