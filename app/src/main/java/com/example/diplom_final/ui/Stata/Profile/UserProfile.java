package com.example.diplom_final.ui.Stata.Profile;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "user_profile")
public class UserProfile {
    @PrimaryKey(autoGenerate = true)
    public int id;
    
    @ColumnInfo(name = "name")
    public String name;
    
    @ColumnInfo(name = "age")
    public int age;
    
    @ColumnInfo(name = "gender")
    public String gender;
    
    @ColumnInfo(name = "height")
    public double height;
    
    @ColumnInfo(name = "weight")
    public double weight;
    
    // Добавляем силовые параметры
    @ColumnInfo(name = "bench_press")
    public double benchPress; // жим
    @ColumnInfo(name = "deadlift")
    public double deadlift;  // становая тяга
    @ColumnInfo(name = "squat")
    public double squat;     // присед

    // Конструктор по умолчанию
    public UserProfile() {}

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getBenchPress() { return benchPress; }
    public void setBenchPress(double benchPress) { this.benchPress = benchPress; }

    public double getDeadlift() { return deadlift; }
    public void setDeadlift(double deadlift) { this.deadlift = deadlift; }

    public double getSquat() { return squat; }
    public void setSquat(double squat) { this.squat = squat; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}