package Distancing;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * This class simulates who the person can behave
 *
 * @since 2020-12-04
 * @author Saeed
 */

public class Simulation {

    private ArrayList<Person> people;

    //create the people and draw them on the screen
    public Simulation(Pane world,int popSize){
        people=new ArrayList<>();
        for (int i=0;i<popSize;i++){
            people.add(new Person(State.SUSCEPTIBLE,world));
        }
        people.add(new Person(State.INFECTED,world));
         draw();
    }

    public ArrayList<Person> getPeople(){
        return people;
    }
    //to move the people on the screen
    public void move(){
        for(Person person1:people){
            person1.move();
        }
    }

    public void draw(){
        for (Person person1:people){
            person1.draw();
        }
    }
    public void resolveCollision(){
        for(Person person1:people){
            for(Person person2:people){
                if(person1!=person2 ){
                    person1.collisionCheck(person2);
                }
            }
        }
    }
    public void feelBetter(){
        for (Person person1:people){
            person1.feelBetter();
        }
    }
}
