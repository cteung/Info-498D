package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
  }

  @Override
  public int compareTo(Person other){
    return (int) (this.salary - other.salary);
  }

  public void setSalary(double salary){
    this.salary = salary;
  }

  public void setAge(int age) {
    if (age < 0){
      throw new IllegalArgumentException("invalid age");
    }
    this.age = age;
  }

  public void setName(String name) {
    if (name == null){
      throw new IllegalArgumentException("name is null");
    }
    this.name = name;
  }

  public int getAge() {

    return age;
  }

  public String getName() {

    return name;
  }

  public double getSalary() {

    return salary;
  }
  
  public String getSSN() {

    return ssn;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    if (old != null){
      this.pcs.firePropertyChange("ssn", old, value);
    }
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person)other;
      return ((this.name.equals(((Person) other).getName())) && (this.age == ((Person) other).getAge()));
    }
    return false;
  }

  public String toString() {
    return "[Person name:"+this.name+" age:"+this.age+" salary:"+this.salary+"]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> family = new ArrayList<Person>();

    family.add(new Person("Ted", 41, 250000));
    family.add(new Person("Charlotte", 43, 150000));
    family.add(new Person("Michael", 22, 10000));
    family.add(new Person("Matthew", 15, 0));

    return family;
  }

  public static class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2){
      return p1.getAge() - p2.getAge();
  }
  }
}
