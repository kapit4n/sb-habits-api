package com.habits.habits;

public class Habit {
  private final long id;
  private final String name;

  public Habit(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
