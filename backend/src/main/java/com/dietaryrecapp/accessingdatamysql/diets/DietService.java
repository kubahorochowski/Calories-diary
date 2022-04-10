package com.dietaryrecapp.accessingdatamysql.diets;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietService {

    private final DietRepository dietRepository;

    public Iterable<Diet> getAllProducts() {
        return dietRepository.findAll();
    }

    public List<Diet> getUserDietByUserID(Integer user_id) {
        return dietRepository.findByUserId(user_id);
    }

    public List<Diet> getUserDailyDiet(Integer user_id, LocalDate date) {
        return dietRepository.findByUserIdAndDate(user_id, date);
    }

    public Diet addDiet(Diet diet) {
        return dietRepository.save(diet);
    }

    public void deletePost(int diet_id) {
        dietRepository.deleteById(diet_id);
    }

}
