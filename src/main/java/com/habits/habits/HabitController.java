package com.habits.habits;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/habits")
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
  public ResponseEntity<Habit> getHabitById(@PathVariable(value = "id") Long habitId) {
    Optional<Habit> habit = habitRepository.findById(habitId);
    if (habit == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(habit.get());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Habit> updateHabit(@PathVariable(value = "id") Long habitId, @Valid @RequestBody Habit habitDetails) {
    Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new ResourceNotFoundException("Habit", "id", habitId));
    habit.setName(habitDetails.getName());
    Habit updatedHabit = habitRepository.save(habit);
    return ResponseEntity.ok().body(updatedHabit);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteHabit(@PathVariable(value = "id") Long habitId) {
    Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new ResourceNotFoundException("Habit", "id", habitId));

    habitRepository.delete(habit);

    return ResponseEntity.ok().build();
  }
}
