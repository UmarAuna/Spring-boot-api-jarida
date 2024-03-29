package com.jarida.server.jaridaserver.jarida.controller;

import com.jarida.server.jaridaserver.exception.ResourceAlreadyExists;
import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.jarida.model.Jarida;
import com.jarida.server.jaridaserver.jarida.repository.JaridaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
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
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
@Validated
@Api(tags = "Jarida API v1")
@SwaggerDefinition(tags = {
        @Tag(name = "Jarida", description = "This is for getting Jarida v1")
})
public class JaridaController {

    @Autowired
    private JaridaRepository jaridaRepository;

    //get jarida
   /* @GetMapping("jarida")
    public List<Jarida> getAllJarida(){
        return this.jaridaRepository.findAll();
    }*/

 /*   @GetMapping("/gaisuwa")
    @ResponseStatus(HttpStatus.OK)
    public List<String> gaisuwa() {
        return List.of("Ina Kwana", "Ina Yini");
    }*/

    //query for a title and get jarida
    @GetMapping("/jarida")
    @ResponseStatus(HttpStatus.OK)
   // @Deprecated
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Jarida> getJaridaById(@PathVariable(value = "id" ) Long jaridaId)
            throws ResourceNotFoundException{

        Jarida jarida = jaridaRepository.findById(jaridaId)
                .orElseThrow(() -> new ResourceNotFoundException("Jarida not found for this id :: " + jaridaId ));
        return ResponseEntity.ok().body(jarida);
    }

    //save jarida
    @PostMapping("/jarida")
    @ResponseStatus(HttpStatus.OK)
    public  Jarida createJarida(
            @Valid @FieldValue @NonNull @NotEmpty(message = "Content is mandatory") /*@RequestBody*/ Jarida jarida){
          if (jaridaExist(jarida.getTitle())){
            throw new ResourceAlreadyExists("This is a title that exists " + jarida.getTitle());
        }
        return this.jaridaRepository.save(jarida);

    }

    //update jarida
    @PutMapping("/jarida/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Jarida> updateJarida(@PathVariable(value = "id") Long jaridaId,
                                                @Valid @FieldValue /*@RequestBody*/ Jarida jaridaDetails) throws ResourceNotFoundException {
        Jarida jarida = jaridaRepository.findById(jaridaId)
                .orElseThrow(() -> new ResourceNotFoundException("Jarida not found for this id :: " + jaridaId ));

        jarida.setTitle(jaridaDetails.getTitle());
        jarida.setContent(jaridaDetails.getContent());
        jarida.setAuthor(jaridaDetails.getAuthor());

        return ResponseEntity.ok(this.jaridaRepository.save(jarida));
    }

    //delete jarida by id
    @DeleteMapping("/jarida/{id}")
    @ResponseStatus(HttpStatus.OK)
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
