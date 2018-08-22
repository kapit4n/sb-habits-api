package com.habits.habits;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/habits")
public class HabitController {

  @Autowired
  HabitRepository habitRepository;

  private final AtomicLong counter = new AtomicLong();
  
  @GetMapping("")
  public ResponseEntity<List<Habit>> getAllHabits() {
    return ResponseEntity.ok().body(habitRepository.findAll());
  }

  @PostMapping("")
  public ResponseEntity<Habit> createHabit(@Valid @RequestBody Habit habit) {
    return ResponseEntity.ok().body(habitRepository.save(habit));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Habit> getResumeById(@PathVariable(value = "id") Long habitId) {
    Optional<Habit> habit = habitRepository.findById(habitId);
    if (habit == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(habit.get());
  }
}
