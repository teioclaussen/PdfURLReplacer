import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;

import java.io.IOException;
import java.util.List;

public class Helper {

    public static void getURL(String oldreportid, String newreportid, String oldpdf, String newpdf) {
        PDDocument doc = null;

        try {
            doc = PDDocument.load(oldpdf);
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
                            if (oldreportid.equals(reportID)) {
                                String newURI = "http://www.lombardstreetresearch.com/lsrlink.php?T=MQ==&F=" + newreportid;
                                System.out.println("Page " + (i + 1) + ": Replacing " + oldURL + " with " + newURI);
                                uri.setURI(newURI);
                            }
                        }
                    }
                }
            }

            doc.save(newpdf);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        } finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}