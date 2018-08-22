package com.habits.habits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "habitLog")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class HabitLog implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank
  private String description;

/*  @Column(name = "habit_id")
  private long habitId;
*/

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;

  @ManyToOne(optional = true)
  @JoinColumn(name = "habit_id", referencedColumnName = "habit_id")
  private Habit habit;

  public HabitLog(){
  }

  public HabitLog(String description) {
        this.description = description;
  }

  public long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String ti) {
    description = ti;
  }

  @Override
  public String toString() {
    return String.format("HabitLog[id=%d, description='%s']", id, description);
  }
  
}
