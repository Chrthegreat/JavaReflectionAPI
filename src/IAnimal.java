public interface IAnimal {

    public void setName(String name);
    public void setAge(int age);
    public String getName();
    public int getAge();

    public void animalSound();

    public static void sleep() {
        System.out.println("Sleeping");
    }


}
