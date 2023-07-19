package com.example.demo.Controller;

import com.example.demo.DTO.CLassDetailsDTO;
import com.example.demo.DTO.ClassesDTO;
import com.example.demo.DTO.RegisterDTO;
import com.example.demo.Service.CLassesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ClassesController {
    private CLassesService cLassesService;
    private ModelMapper modelMapper;
    @Autowired
    public ClassesController(CLassesService cLassesService, ModelMapper modelMapper) {
        this.cLassesService = cLassesService;
        this.modelMapper=modelMapper;
    }
    @GetMapping("/classes")
    public List<ClassesDTO> getAllClass(){

        return cLassesService.getAllClass();
    }
    @PostMapping
    public ClassesDTO createClass(@RequestBody ClassesDTO classesDTO) {
        ClassesDTO createdClassDTO = cLassesService.createClass(classesDTO);
        return modelMapper.map(createdClassDTO, ClassesDTO.class);
    }
    @DeleteMapping
    public void deleteClass(@PathVariable Long id) {
        cLassesService.deleteCLass(id);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        cLassesService.registerClass(registerDTO);
        return ResponseEntity.ok("Registration successful");
    }
    @GetMapping("/{id}")
    public CLassDetailsDTO getClassDetails(@PathVariable Long id) {
        CLassDetailsDTO cLassDetailsDTO = cLassesService.getClassDetails(id);
        return modelMapper.map(cLassDetailsDTO, CLassDetailsDTO.class);
    }

}
