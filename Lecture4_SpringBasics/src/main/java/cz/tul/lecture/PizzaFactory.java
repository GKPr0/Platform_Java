package cz.tul.lecture;

public class PizzaFactory {

    public static final Pizza pizza = new Pizza();

    public static void main(String[] args) {
        Pizza normal = PizzaFactory.getPizza("normal");
        System.out.println(normal);
    }

    public static Pizza getPizza(String type) {
        return switch (type) {
            case "normal" -> new Pizza(true, false, false);
            case "all-inclusive" -> new Pizza(true, true, true);
            default -> new Pizza();
        };
    }


    public static class Pizza {

        private boolean ketchup;
        private boolean cheese;
        private boolean sausage;

        public Pizza() {
            // all set to false
        }

        public Pizza(boolean ketchup, boolean cheese, boolean sausage) {
            this.ketchup = ketchup;
            this.cheese = cheese;
            this.sausage = sausage;
        }

        public boolean isKetchup() {
            return ketchup;
        }

        public void setKetchup(boolean ketchup) {
            this.ketchup = ketchup;
        }

        public boolean isCheese() {
            return cheese;
        }

        public void setCheese(boolean cheese) {
            this.cheese = cheese;
        }

        public boolean isSausage() {
            return sausage;
        }

        public void setSausage(boolean sausage) {
            this.sausage = sausage;
        }

        @Override public String toString() {
            return "Pizza{" +
                    "ketchup=" + ketchup +
                    ", cheese=" + cheese +
                    ", sausage=" + sausage +
                    '}';
        }
    }


}
