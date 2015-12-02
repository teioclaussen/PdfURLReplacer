import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        if (args.length >= 4) {
            String originalPdf = args[0];
            String targetPdf = args[1];
            Map<String, String> urls = new HashMap<>();
            for (int i = 0; i < args.length; i += 2) {
                urls.put(args[i], args[i + 1]);
            }
            URLReplacer.changeURL(originalPdf, targetPdf, urls);
        } else {
            System.out.println("PARAMETER MISSING FROM PHP");
        }
    }
}
