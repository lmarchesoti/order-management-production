package dev.lmarchesoti.order_management_test.repositories;

import dev.lmarchesoti.order_management_test.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByZipCodeAndNumber(String zipCode, Long number);
}
