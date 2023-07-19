package com.example.demo.Service;

import com.example.demo.DTO.AddressDTO;
import com.example.demo.Entity.Address;
import com.example.demo.Repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService  {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public AddressService(AddressRepository addressRepository,ModelMapper modelMapper) {
        super();
        this.addressRepository = addressRepository;
        this.modelMapper=modelMapper;
    }

    public List<AddressDTO> getAllAddress() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOs = addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());

        return addressDTOs;
    }
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElse(null);

        if (address == null) {
            return null;
        }

        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);

        return addressDTO;
    }

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        addressRepository.save(address);
        addressDTO.setId(address.getId());

        return addressDTO;
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Address address = addressRepository.findById(id).orElse(null);

        if (address == null) {
            return null;
        }

        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());

        addressRepository.save(address);

        AddressDTO updatedAddressDTO = modelMapper.map(address, AddressDTO.class);

        return updatedAddressDTO;
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

}
