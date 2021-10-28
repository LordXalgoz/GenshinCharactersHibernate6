package com.example.demo.controllers;

import com.example.demo.models.entities.Character;
import com.example.demo.models.tables.TableCharacters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/characters", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CharacterController {

    @Autowired
    private final TableCharacters tableCharacters;

    public CharacterController(TableCharacters tableCharacters) {
        this.tableCharacters = tableCharacters;
    }

    private void checkApiKey(String apiKey) throws Exception {
        String originalApiKey = "1212";

        if (originalApiKey.equals(apiKey) == false) {
            throw new Exception("Ошибка неверный API ключ");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("ERROR: " + exception.getMessage());
    }

    @GetMapping(value = "/hello")
    public String hello() throws Exception {
        return "hello world";
    }

    @GetMapping(value = "/getAll")
    public List<Character> getAll(@RequestHeader("APIKEY") String apiKey) throws Exception {
        checkApiKey(apiKey);

        return tableCharacters.findAll();
    }

    @GetMapping(value = "/getAllWithCname")
    public List<Character> getAllWithCname(@RequestHeader("APIKEY") String apiKey) throws Exception {
        checkApiKey(apiKey);

        return tableCharacters.findWhereNameStartsFromC();
    }

    @GetMapping(value = "/getById/{id}")
    public Character getById(@RequestHeader("APIKEY") String apiKey, @PathVariable int id) throws Exception {
        checkApiKey(apiKey);

        return tableCharacters.findById(id).get();
    }

    @PostMapping(value = "/insertOne")
    public void insertOne(@RequestHeader("APIKEY") String apiKey, @RequestBody Character character) throws Exception {
        checkApiKey(apiKey);

        tableCharacters.save(character);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public void deleteById(@RequestHeader("APIKEY") String apiKey, @PathVariable int id) throws Exception {
        checkApiKey(apiKey);

        tableCharacters.deleteById(id);
    }

    @PutMapping(value = "/updateById/{id}")
    public void updateById(@RequestHeader("APIKEY") String apiKey, @PathVariable int id,@RequestBody Character character) throws Exception {
        checkApiKey(apiKey);

        Character findedCharacter = tableCharacters.findById(id).get();

        findedCharacter.name = character.name;
        findedCharacter.sex = character.sex;
        findedCharacter.element = character.element;
        findedCharacter.weapon = character.weapon;

        tableCharacters.save(findedCharacter);
    }
}
