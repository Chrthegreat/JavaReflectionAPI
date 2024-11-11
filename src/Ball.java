public class Ball {
    private int weight;
    private int diameter;

    public Ball(int weight, int diameter) {
        this.weight = weight;
        this.diameter = diameter;
    }

    public int getWeight() {
        return weight;
    }
    public int getDiameter() {
        return diameter;
    }

    public void setWeight(int weight) {
        this.weight = weight;

    }
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
    public int Sizeofball() {
        return weight * diameter;
    }

}
