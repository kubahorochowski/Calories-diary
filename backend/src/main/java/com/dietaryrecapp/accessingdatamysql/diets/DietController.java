package com.dietaryrecapp.accessingdatamysql.diets;

import com.dietaryrecapp.accessingdatamysql.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/diets")
public class DietController {

    private final DietService dietService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Diet> getAllDiets() {
        return dietService.getAllProducts();
    }

    @GetMapping(path="/user/{user_id}")
    public @ResponseBody Iterable<Diet> getUserDiet(@PathVariable Integer user_id) {
        return dietService.getUserDietByUserID(user_id);
    }

    @GetMapping(path="/user/{user_id}/date/{date}")
    public @ResponseBody List<Diet> getUserDiet(@PathVariable Integer user_id, @PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return dietService.getUserDailyDiet(user_id, localDate);
    }

    @PostMapping(path="/add")
    public ResponseEntity<Object> addDiet(@RequestBody Diet diet) {
        Diet addedDiet =  dietService.addDiet(diet);
        return new ResponseEntity<>(addedDiet, HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{diet_id}")
    public ResponseEntity<Integer> deleteDiet(@PathVariable int diet_id) {
        dietService.deletePost(diet_id);
        return new ResponseEntity<>(diet_id, HttpStatus.OK);
    }
}
