
class Vehicle {
    int speed;
    int fuel_capacity;

    public Vehicle(int speed, int fuel_capacity) {
        this.speed = speed;
        this.fuel_capacity = fuel_capacity;
    }

    public int Range() {
        return fuel_capacity * speed;
    }
}




public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Vehicle Ferrari = new Vehicle(150, 100);
        Vehicle BMW;
        BMW = new Vehicle(150, 200);

        System.out.println(Ferrari.Range());
        System.out.println(BMW.Range());

        Ball football = new Ball(10,5);

        System.out.println("The weight of football is: " + football.getWeight());
        System.out.println("The diameter of football is: " + football.getDiameter());

        football.setWeight(20);
        football.setDiameter(10);

        System.out.println("The weight of football is: " + football.getWeight());
        System.out.println("The diameter of football is: " + football.getDiameter());

    }
}