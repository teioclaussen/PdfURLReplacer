public class Main {

    public static void main(String[] args) {
        if (args.length < 4) {
            System.err.println("Parameter missing from PHP");

        } else {
            Helper.getURL(args[0], args[1], args[2], args[3]);
        }

    }
}