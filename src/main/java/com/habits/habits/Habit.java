package com.habits.habits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "habits")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Habit implements Serializable {
  
  @Id
  @Column(name = "habit_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long habitId;

  @NotBlank
  private String name;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;

  @OneToMany(mappedBy = "habit", targetEntity = HabitLog.class, fetch = FetchType.EAGER)
  private Collection<HabitLog> habitLogs;

  public Habit(){
  }

  public Habit(String name) {
        this.name = name;
  }

  public long getHabitId() {
    return habitId;
  }

  public String getName() {
    return name;
  }

  public void setName(String ti) {
    name = ti;
  }

  public Collection<HabitLog> getHabitLogs() {
    return habitLogs;
  }

  public void setHabitLogs(Collection<HabitLog> habitLogs) {
    this.habitLogs = habitLogs;
  }

  @Override
  public String toString() {
    return String.format("Habit[id=%d, name='%s']", habitId, name);
  }
  
}
