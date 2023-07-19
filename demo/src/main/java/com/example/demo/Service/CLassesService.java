package com.example.demo.Service;

import com.example.demo.DTO.CLassDetailsDTO;
import com.example.demo.DTO.ClassesDTO;
import com.example.demo.DTO.RegisterDTO;
import com.example.demo.Entity.Classes;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.ClassesRepository;
import com.example.demo.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CLassesService {

    private final ClassesRepository classesRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CLassesService(ClassesRepository classesRepository, ModelMapper modelMapper,StudentRepository studentRepository) {
        this.classesRepository = classesRepository;
        this.modelMapper=modelMapper;
        this.studentRepository=studentRepository;
    }
    //  Xem các lớp
    public List<ClassesDTO> getAllClass(){
        List<Classes> clazzs = classesRepository.findAll();
        List<ClassesDTO> classesDTOS = clazzs.stream().map(clazz-> modelMapper.map(clazz, ClassesDTO.class))
                .collect(Collectors.toList());
        return classesDTOS;
    }
//    Tạo lớp
    public ClassesDTO createClass(ClassesDTO classesDTO) {
        if(classesRepository.findById(classesDTO.getId()).isPresent()){
            throw new IllegalArgumentException("This class is already exist");
        }
        Classes classes = modelMapper.map(classesDTO, Classes.class);
        classesRepository.save(classes);
        classesDTO.setClassName(classes.getClassName());

        return classesDTO;
    }
//    Xóa lớp
    public void deleteCLass(Long id) {
        classesRepository.deleteById(id);
    }
//    Đăng kí lớp
     public void registerClass(RegisterDTO registerDTO){
         Student student = studentRepository.findById(registerDTO.getStudentId())
                 .orElseThrow(() -> new IllegalArgumentException("Invalid student id"));
         Classes clazz = classesRepository.findById(registerDTO.getClassId())
                 .orElseThrow(() -> new IllegalArgumentException("This class is already exist"));

         student.setClasses(clazz);
         clazz.getStudents().add(student);

         studentRepository.save(student);
         classesRepository.save(clazz);
     }
    //   Xem danh sách sv trong lớp
    public CLassDetailsDTO getClassDetails(Long id) {
        Classes classes = classesRepository.findById(id).orElse(null);

        if (classes == null) {
            throw new RuntimeException("Class with id " + id + " does not exist");
        }

        CLassDetailsDTO cLassDetailsDTO = modelMapper.map(classes, CLassDetailsDTO.class);

        return cLassDetailsDTO;
    }

//    xóa sv khỏi lớp



}
