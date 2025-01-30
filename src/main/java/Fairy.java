public class Fairy {
    private static final String name = "Fairy";

    private static void line() {
        System.out.println("——————————————————————————————————————————————————————————————————————");
    }

    private static void greet() {
        line();
        System.out.println("Hello! This is " + name + ", your personal assistant.");
        System.out.println("What can I do for you today?\n");
    }

    private static void exit() {
        line();
        System.out.println("Goodbye. Hope to see you again soon!\n");
        line();
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}
