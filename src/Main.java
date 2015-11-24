public class Main {

    public static void main(String[] args) {
        if (args.length >= 4) {
            URLReplacer.changeURL(args);
        } else {
            System.out.println("PARAMETER MISSING FROM PHP");
        }
    }
}
