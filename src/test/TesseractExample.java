package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

public class TesseractExample {
    public static void main(String[] args) {
        // ImageIO.scanForPlugins(); // for server environment
        File imageFile = new File("test/resources/test-data/test.png");
        ITesseract instance = new Tesseract(); // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        // instance.setDatapath("<parentPath>"); // replace <parentPath> with path to parent directory of tessdata
        instance.setLanguage("chi_sim");
        
        BufferedImage readFile = null;
		try {
			readFile = ImageIO.read(imageFile);
			BufferedImage imgFile = ImageHelper.convertImageToGrayscale(readFile);
	        int endX = imgFile.getWidth();
	        int endY = imgFile.getHeight();
	        imgFile = ImageHelper.getScaledInstance(imgFile, endX * 5, endY * 5);
	        String result = instance.doOCR(imgFile);
            System.out.println(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TesseractException e) {
			e.printStackTrace();
		}
    }
}
