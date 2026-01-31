package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.entities.Address;
import dev.lmarchesoti.order_management_test.external.producta.dto.ProductAAddress;
import dev.lmarchesoti.order_management_test.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address getOrCreateAddress(ProductAAddress productAAddress) {
        Optional<Address> optionalAddress = addressRepository.findByZipCodeAndNumber(productAAddress.zipCode(), productAAddress.number());

        if (optionalAddress.isPresent()) {
            return optionalAddress.get();
        }

        Address address = Address.builder()
                .zipCode(productAAddress.zipCode())
                .number(productAAddress.number())
                .build();

        addressRepository.save(address);

        return address;
    }
}
