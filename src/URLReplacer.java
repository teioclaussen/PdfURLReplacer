import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class URLReplacer {

    public static void changeURL(String originalPdf, String targetPdf, Map<String, String> urls) {

        try (PDDocument doc = PDDocument.load(originalPdf)) {
            List<PDPage> allPages = doc.getDocumentCatalog().getAllPages();
            for (PDPage page : allPages) {
                // List annotations = page.getAnnotations();
                for (PDAnnotation annot : page.getAnnotations()) {
                    if (annot instanceof PDAnnotationLink) {
                        PDAnnotationLink link = (PDAnnotationLink) annot;
                        PDAction action = link.getAction();
                        if (action instanceof PDActionURI) {
                            PDActionURI uri = (PDActionURI) action;
                            String oldURL = uri.getURI();

                            for (Map.Entry<String, String> url : urls.entrySet()) {
                                if (url.getKey().equals(oldURL)) {
                                    uri.setURI(url.getValue());
                                }
                            }
                        }
                    }
                }
            }
            doc.save(targetPdf);
        } catch (IOException | COSVisitorException e) {
            e.printStackTrace();
        }
    }
}