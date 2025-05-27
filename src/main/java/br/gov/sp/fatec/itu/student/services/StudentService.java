package br.gov.sp.fatec.itu.student.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.itu.student.entities.Student;
import br.gov.sp.fatec.itu.student.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    
    //Pegar estudante
    public List<Student> getAll(){
        return repository.findAll();
    }
    
    //Criar estudante
    public Student save(Student student){
        return repository.save(student);
    }

    //Editar estudante
    public void update(Student student, Long id){
        Student aux  = repository.getReferenceById(id);
        Student sux2 = repository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        System.out.println(aux);
        System.out.println(sux2);
        aux.setCourse(student.getCourse());
        aux.setName(student.getName());   

        repository.save(aux);
    }


    //Deletar Estudante
    public void delete(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Aluno não cadastrado");
        } 
    }
    
}
