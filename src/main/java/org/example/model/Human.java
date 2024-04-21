package org.example.model;

import java.util.Date;
import java.util.Objects;

public class Human {

    private int id;
    private String name;
    private Gender gender;
    private Date birthDate;
    private float salary;
    private Division division;

    public Human(int id, String name, Gender gender, Date birthDate, float salary, Division division) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.salary = salary;
        this.division = division;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human human)) return false;
        return id == human.id && Float.compare(salary, human.salary) == 0 && Objects.equals(name, human.name) && gender == human.gender && Objects.equals(birthDate, human.birthDate) && Objects.equals(division, human.division);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, birthDate, salary, division);
    }

    @Override
    public String toString() {
        return "Human(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", division=" + division +
                ')';
    }
}
