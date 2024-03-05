package com.jarida.server.jaridaserver.students.service;

import com.jarida.server.jaridaserver.exception.ResourceAlreadyExists;
import com.jarida.server.jaridaserver.exception.ResourceBadRequestException;
import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.students.model.Student;
import com.jarida.server.jaridaserver.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    //MTN GSM Prefixes
    static String m1 = "0806";
    static String m2 = "0803";
    static String m3 = "0816";
    static String m4 = "0813";
    static String m5 = "0810";
    static String m6 = "0814";
    static String m7 = "0903";
    static String m8 = "0703";
    static String m9 = "0706";
    static String m10 = "0903";
    static String m11 = "0906";

    //GLO GSM Prefixes
    static String g1 = "0805";
    static String g2 = "0705";
    static String g3 = "0905";
    static String g4 = "0807";
    static String g5 = "0815";
    static String g6 = "0811";

    //9MOBILE GSM Prefixes
    static String n1 = "0809";
    static String n2 = "0909";
    static String n3 = "0817";
    static String n4 = "0818";
    static String n5 = "0908";

    //AIRTEL GSM Prefixes
    static String a1 = "0802";
    static String a2 = "0902";
    static String a3 = "0701";
    static String a4 = "0808";
    static String a5 = "0708";
    static String a6 = "0812";
    static String a7 = "0907";
    static String a8 = "0901";

    //STARCOMMS GSM Prefixes
    static String s1 = "07028";
    static String s2 = "07029";
    static String s3 = "0819";

    //VISAFONE GSM Prefixes
    static String v1 = "07025";
    static String v2 = "07026";
    static String v3 = "0704";

    //MULTILINKS GSM Prefixes
    static String mu1 = "07027";
    static String mu2 = "0709";

    //ZOOM GSM Prefix
    static String z1 = "0707";

    //NTEL GSM Prefix
    static String nt1 = "0804";

    //SMILE GSM Prefix
    static String sm1 = "0702";

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new ResourceBadRequestException("email taken");
        }
        studentRepository.save(student);
    }

    public static Map<String, String> phoneNumber(@NonNull String userInput) {
        Map<String,String> response = new HashMap<>();

        if (!userInput.matches("[0-9]+")) {
            throw new ResourceBadRequestException("Error! Invalid number");

        }else{
            String checkNumber = userInput.substring(0, 4);

            String checkNumber2 = userInput.substring(0, 5);

            //Stores initial number
            String iInput = userInput;
            if (userInput.length() < 11 || userInput.length() > 11) {
                throw new ResourceBadRequestException("Error! Invalid number. Number must not be lesser or greater than 11");
            }
            else if (checkNumber.equals( m1 ) || checkNumber.equals( m2 ) || checkNumber.equals( m3 ) || checkNumber.equals( m4 ) || checkNumber.equals( m5 ) || checkNumber.equals( m6 ) || checkNumber.equals( m7 ) || checkNumber.equals( m8 ) || checkNumber.equals( m9 ) || checkNumber.equals( m10 ) || checkNumber.equals( m11 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934123/network_providers/mtn.png");
                response.put("Network Info", iInput + " belongs to MTN network 🟨");
                response.put("Network Name",  "MTN");
            }
            else if (checkNumber.equals( g1 ) || checkNumber.equals( g2 ) || checkNumber.equals( g3 ) || checkNumber.equals( g4 ) || checkNumber.equals( g5 ) || checkNumber.equals( g6 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934123/network_providers/glo.png");
                response.put("Network Info", iInput + " belongs to GLO network 🟩");
                response.put("Network Name",  "GLO");
            }
            else if (checkNumber.equals( n1 ) || checkNumber.equals( n2 ) || checkNumber.equals( n3 ) || checkNumber.equals( n4 ) || checkNumber.equals( n5 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934123/network_providers/9mobile.jpg");
                response.put("Network Info", iInput + " belongs to 9MOBILE network 🟩");
                response.put("Network Name",  "9MOBILE");
            }
            else if (checkNumber.equals( a1 ) || checkNumber.equals( a2 ) || checkNumber.equals( a3 ) || checkNumber.equals( a4 ) || checkNumber.equals( a5 ) || checkNumber.equals( a6 ) || checkNumber.equals( a7 )|| checkNumber.equals( a8 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934123/network_providers/airtel.png");
                response.put("Network Info", iInput + " belongs to AIRTEL network 🟥");
                response.put("Network Name",  "AIRTEL");
            }
            else if (checkNumber2.equals( s1 ) || checkNumber2.equals( s2 ) || checkNumber.equals( s3 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934123/network_providers/starcomms.jpg");
                response.put("Network Info", iInput + " belongs to STARCOMMS network 🟪");
                response.put("Network Name",  "STARCOMMS");
            }
            else if (checkNumber2.equals( v1 ) || checkNumber2.equals( v2 ) || checkNumber.equals( v3 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934123/network_providers/visafone.jpg");
                response.put("Network Info", iInput + " belongs to VISAFONE network 🟥");
                response.put("Network Name",  "VISAFONE");
            }
            else if (checkNumber2.equals( mu1 ) || checkNumber.equals( mu2 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934123/network_providers/multilinks.png");
                response.put("Network Info", iInput + " belongs to MULTILINKS network 🟦");
                response.put("Network Name",  "MULTILINKS");
            }
            else if (checkNumber.equals( s1 ) || checkNumber.equals( s2 ) || checkNumber.equals( s3 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934123/network_providers/starcomms.jpg");
                response.put("Network Info", iInput + " belongs to STARCOMMS network 🟪");
                response.put("Network Name",  "STARCOMMS");
            }
            else if (checkNumber.equals( z1 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934430/network_providers/zoom.jpg");
                response.put("Network Info", iInput + " belongs to ZOOM network 🟧");
                response.put("Network Name",  "ZOOM");
            }
            else if (checkNumber.equals( nt1 )) {
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934161/network_providers/ntel.png");
                response.put("Network Info", iInput + " belongs to NTEL network 🟩");
                response.put("Network Name",  "NTEL");
            }
            else if (checkNumber.equals( sm1 )) {
               // value = iInput + " belongs to SMILE network 💛️";
                response.put("Network Image", "https://res.cloudinary.com/dxrxviiv8/image/upload/v1642934123/network_providers/smile.jpg");
                response.put("Network Info", iInput + " belongs to SMILE network 🟩");
                response.put("Network Name",  "SMILE");
            }
            else {
                throw new ResourceBadRequestException(iInput + " belongs to no Nigerian network, check the number and TRY AGAIN!!");
            }
        }
        return response;
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new ResourceNotFoundException(
                    "student with id " + studentId + " does not exists"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "student with id " + studentId + " does not exists"

                ));

        if (name != null && name.length() > 0/* && !Objects.equals(student.getName(), name)*/) {
            student.setName(name);
        } else {
            throw new ResourceBadRequestException("name is required");
        }

        if (email != null && email.length() > 0 /*&& !Objects.equals(student.getEmail(), email)*/) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new ResourceAlreadyExists("email taken");
            }
            student.setEmail(email);
        } else {
            throw new ResourceBadRequestException("email is required");
        }

    }

    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findStudentById(studentId);
    }

    /*  public List<Student> getStudent() {
        return List.of(
                new Student(
                        1L,
                        "Mariam",
                        "mariam.adams@gmail.com",
                        LocalDate.of(2001, Month.APRIL, 5),
                        21)
        );
    }*/
}
