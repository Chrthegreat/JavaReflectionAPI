public class Cat implements IAnimal {

    private String name;
    private int age;
    public int foo;

    public Cat (String name, int age) {
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void animalSound() {
        System.out.println("Meow");
    }

    private void heyThisIsPrivate() {
        System.out.println("Private method");
    }
    public static void thisIsStaticAndPublic() {
        System.out.println("Static method and public method");
    }

    private static void thisIsStaticAndPrivate() {
        System.out.println("Static method and private method");
    }

}