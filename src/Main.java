public class Main {

    public static void main(String[] args) {
        if (args.length == 4) {
            URLReplacer.replaceUrl(args[0], args[1], args[2], args[3]);
        } else if (args.length == 6) {
            URLReplacer.replaceUrl(args[0], args[1], args[2], args[3], args[4], args[5]);
        } else if (args.length == 8) {
            URLReplacer.replaceUrl(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
        } else if (args.length == 10) {
            URLReplacer.replaceUrl(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
        } else {
            System.err.println("Parameter missing from PHP");
        }
    }
}