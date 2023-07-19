package com.example.demo.Controller;

import com.example.demo.DTO.StudentInforDTO;
import com.example.demo.Service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    @Autowired
    private ModelMapper modelMapper;
    private final StudentService studentService;
    public StudentController (StudentService studentService){
        super();
        this.studentService = studentService;
    }

    @PostMapping
    public StudentInforDTO createStudent(@RequestBody StudentInforDTO studentDTO) {
        StudentInforDTO createdStudentDTO = studentService.createStudent(studentDTO);
        return modelMapper.map(createdStudentDTO, StudentInforDTO.class);
    }

    @GetMapping("/{id}")
    public StudentInforDTO getStudent(@PathVariable Long id) {
        StudentInforDTO studentDTO = studentService.getStudentById(id);
        return modelMapper.map(studentDTO, StudentInforDTO.class);
    }

    @GetMapping
    public List<StudentInforDTO> getAllStudents() {
        List<StudentInforDTO> studentDTOs = studentService.getAllStudents();
        return studentDTOs.stream().map(studentDTO -> modelMapper.map(studentDTO, StudentInforDTO.class)).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public StudentInforDTO updateStudent(@PathVariable Long id, @RequestBody StudentInforDTO studentDTO) {
        StudentInforDTO updatedStudentDTO = studentService.updateStudent(id, studentDTO);
        return modelMapper.map(updatedStudentDTO, StudentInforDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


}
