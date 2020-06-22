package com.jarida.server.jaridaserver.jarida.controller;

import com.jarida.server.jaridaserver.jarida.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.jarida.model.Jarida;
import com.jarida.server.jaridaserver.jarida.model.JaridaList;
import com.jarida.server.jaridaserver.jarida.repository.JaridaRepository;
import net.bytebuddy.implementation.bind.annotation.FieldValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
@Validated
public class JaridaController {

    @Autowired
    private JaridaRepository jaridaRepository;

    //get jarida
   /* @GetMapping("jarida")
    public List<Jarida> getAllJarida(){
        return this.jaridaRepository.findAll();
    }*/

    //query for a title and get jarida
    @GetMapping("/jarida")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Jarida>> getQueryTitle(@RequestParam(required = false) String title) throws ResourceNotFoundException {
        try {
            List<Jarida> jarida = new ArrayList<>();

            if (title == null){
                jarida.addAll(jaridaRepository.findAll());
            }else{
                jarida.addAll(jaridaRepository.findByTitleContaining(title));
            }

            if (jarida.isEmpty()){
                throw new ResourceNotFoundException("No Record found for " + title);
                //return ResponseEntity.ok().body(jarida);
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //JaridaList jaridaList  = new JaridaList(jarida);
            return new ResponseEntity<>(jarida, HttpStatus.OK);

        }catch (Exception e){
            throw new ResourceNotFoundException("No Record found for " + title);
            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get jarida by id
    @GetMapping("/jarida/{id}")
    public ResponseEntity<Jarida> getJaridaById(@PathVariable(value = "id" ) Long jaridaId)
            throws ResourceNotFoundException{

        Jarida jarida = jaridaRepository.findById(jaridaId)
                .orElseThrow(() -> new ResourceNotFoundException("Jarida not found for this id :: " + jaridaId ));
        return ResponseEntity.ok().body(jarida);
    }

    //save jarida
    @PostMapping("/jarida")
    @ResponseStatus(HttpStatus.CREATED)
    public  Jarida createJarida(
            @Valid @FieldValue @NonNull @NotEmpty(message = "Content is mandatory") /*@RequestBody*/ Jarida jarida){
          if (jaridaExist(jarida.getTitle())){
            throw new ResourceNotFoundException("This is a title that exists " + jarida.getTitle());
        }
        return this.jaridaRepository.save(jarida);

    }

    //update jarida
    @PutMapping("/jarida/{id}")
    public  ResponseEntity<Jarida> updateJarida(@PathVariable(value = "id") Long jaridaId,
                                                @Valid @FieldValue @NotBlank @NotEmpty /*@RequestBody*/ Jarida jaridaDetails) throws ResourceNotFoundException {
        Jarida jarida = jaridaRepository.findById(jaridaId)
                .orElseThrow(() -> new ResourceNotFoundException("Jarida not found for this id :: " + jaridaId ));

        jarida.setTitle(jaridaDetails.getTitle());
        jarida.setContent(jaridaDetails.getContent());
        jarida.setAuthor(jaridaDetails.getAuthor());

        return ResponseEntity.ok(this.jaridaRepository.save(jarida));
    }

    //delete jarida by id
    @DeleteMapping("/jarida/{id}")
    public Map<String,String> deleteJarida(@PathVariable(value = "id" ) Long jaridaId) throws ResourceNotFoundException {

        Jarida jarida = jaridaRepository.findById(jaridaId)
                .orElseThrow(() -> new ResourceNotFoundException("Jarida not found for this id :: " + jaridaId ));

        this.jaridaRepository.delete(jarida);
        Map<String,String> response = new HashMap<>();
        response.put("timestamp", new SimpleDateFormat("dd, MMMM, yyyy - hh:mm aa").format(Calendar.getInstance().getTime()));
        response.put("message","Deleted Successfully");
        return response;
    }

   /* //delete jarida
    @DeleteMapping("/jarida")
    public Map<String, String> deleteJaridaAll() throws ResourceNotFoundException {

        //Jarida jarida = jaridaRepository.findById(jaridaId).orElseThrow(() -> new ResourceNotFoundException("Jarida not found for this id :: " + jaridaId ));

        this.jaridaRepository.deleteAll();
        Map<String,String> response = new HashMap<>();
        response.put("timestamp", new SimpleDateFormat("dd, MMMM, yyyy - hh:mm aa").format(Calendar.getInstance().getTime()));
        response.put("message","Deleted Successfully");
        return response;
    }*/

    public boolean jaridaExist(String jarida){
        return jaridaRepository.findByTitle(jarida) != null;
    }


}
