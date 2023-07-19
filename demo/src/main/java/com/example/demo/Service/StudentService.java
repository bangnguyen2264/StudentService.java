package com.example.demo.Service;

import com.example.demo.DTO.StudentInforDTO;
import com.example.demo.Entity.Address;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.AddressRepository;
import com.example.demo.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository,ModelMapper modelMapper,AddressRepository addressRepository) {
        super();
        this.studentRepository = studentRepository;
        this.addressRepository=addressRepository;
        this.modelMapper=modelMapper;
    }

    public List<StudentInforDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentInforDTO> studentDTOs = students.stream().map(student -> modelMapper.map(student, StudentInforDTO.class)).collect(Collectors.toList());

        return studentDTOs;
    }

    public StudentInforDTO createStudent(StudentInforDTO studentDTO) {
        if (studentRepository.findStudentByEmail(studentDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email is already exist");
        }
        Student student = modelMapper.map(studentDTO, Student.class);

        Address address = modelMapper.map(studentDTO.getAddress(), Address.class);
        address.setStudent(student);

        student.setAddress(address);

        studentRepository.save(student);

        studentDTO.setId(student.getId());

        return studentDTO;
    }


    public StudentInforDTO updateStudent(Long id, StudentInforDTO studentDTO) {
        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            throw new RuntimeException("Student with id " + id + " does not exist");
        }

        student.setName(studentDTO.getName());
        student.setDob(studentDTO.getDob());
        student.setEmail(studentDTO.getEmail());

        Address address = student.getAddress();
        address.setStreet(studentDTO.getAddress().getStreet());
        address.setCity(studentDTO.getAddress().getCity());

        addressRepository.save(address);

        studentRepository.save(student);

        StudentInforDTO updatedStudentDTO = modelMapper.map(student, StudentInforDTO.class);

        return updatedStudentDTO;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentInforDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            throw new RuntimeException("Student with id " + id + " does not exist");
        }

        StudentInforDTO studentDTO = modelMapper.map(student, StudentInforDTO.class);

        return studentDTO;
    }
}



