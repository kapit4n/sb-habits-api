package com.habits.habits;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;;

@RestController
@RequestMapping("/habits")
public class HabitController {

  private final AtomicLong counter = new AtomicLong();

  @GetMapping("")
  public ArrayList<Habit> list() {
    ArrayList<Habit> res = new ArrayList<Habit>();
    String name1 = "Default Habit 1";
    String name2 = "Default Habit 2";
    res.add(new Habit(counter.incrementAndGet(), name1));
    res.add(new Habit(counter.incrementAndGet(), name2));
    return res;
  }

  @GetMapping("/{habitId}")
  public Habit habit(@PathVariable Long habitId) {
    return new Habit(habitId, "Default Habit " + habitId);
  }
}