import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;

import java.io.IOException;
import java.util.List;

public class URLReplacer {

    public static void replaceUrl(String... a) {
        String oldpdf = a[0];
        String newpdf = a[1];
        String http1a = a[2];
        String http1b = a[33];

        try (PDDocument doc = PDDocument.load(oldpdf)) {
            List allPages = doc.getDocumentCatalog().getAllPages();
            for (int i = 0; i < allPages.size(); i++) {
                PDPage page = (PDPage) allPages.get(i);
                List annotations = page.getAnnotations();
                for (int j = 0; j < annotations.size(); j++) {
                    PDAnnotation annot = (PDAnnotation) annotations.get(j);
                    if (annot instanceof PDAnnotationLink) {
                        PDAnnotationLink link = (PDAnnotationLink) annot;
                        PDAction action = link.getAction();
                        if (action instanceof PDActionURI) {
                            PDActionURI uri = (PDActionURI) action;
                            String oldURL = uri.getURI();

                            String reportID = oldURL.substring(oldURL.lastIndexOf("=") + 1, oldURL.length());
                            if (http1a.equals(reportID)) {
                                String newURI = "http://www.lombardstreetresearch.com/lsrlink.php?T=MQ==&F=" + http1b;
                                System.out.println("Page " + (i + 1) + ": Replacing " + oldURL + " with " + newURI);
                                uri.setURI(http1b);
                            }
                        }
                    }
                }
            }
            doc.save(newpdf);
        } catch (IOException | COSVisitorException e) {
            e.printStackTrace();
        }
    }
}