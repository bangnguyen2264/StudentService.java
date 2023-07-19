package com.example.demo.Controller;

import com.example.demo.DTO.AddressDTO;
import com.example.demo.Service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/address")
public class AddressController {
    @Autowired
    private ModelMapper modelMapper;
    private final AddressService addressService;
    public AddressController (AddressService addressService,ModelMapper modelMapper){
        super();
        this.addressService = addressService;
        this.modelMapper=modelMapper;
    }
    @GetMapping
    public List<AddressDTO> getAllAddress() {
        List<AddressDTO> addressDTOs = addressService.getAllAddress();
        return addressDTOs.stream().map(addressDTO -> modelMapper.map(addressDTO, AddressDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AddressDTO getAddress(@PathVariable Long id) {
        AddressDTO addressDTO = addressService.getAddressById(id);
        return modelMapper.map(addressDTO, AddressDTO.class);
    }

    @PostMapping
    public AddressDTO createAddress(@RequestBody AddressDTO addressDTO) {
        AddressDTO createdAddressDTO = addressService.createAddress(addressDTO);
        return modelMapper.map(createdAddressDTO, AddressDTO.class);
    }

    @PutMapping("/{id}")
    public AddressDTO updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        AddressDTO updatedAddressDTO = addressService.updateAddress(id, addressDTO);
        return modelMapper.map(updatedAddressDTO, AddressDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }

}
