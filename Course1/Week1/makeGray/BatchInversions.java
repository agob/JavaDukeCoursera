
/**
 * Write a description of ImageInversion here.
 * program to create new images that are photographic negatives 
 * (or inverted images) of selected images and save these new images 
 * with filenames that are related to the original 
 * images, such as adding “inverted-” in front of the old filename. 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int invRed = 255 - inPixel.getRed();
            int invBlue = 255 - inPixel.getBlue();
            int invGreen = 255 - inPixel.getGreen();
            //set pixel's red to average
            pixel.setRed(invRed);
            //pixel.setRed(255 - inPixel.getRed());
            //set pixel's green to average
            pixel.setGreen(invBlue);
            //set pixel's blue to average
            pixel.setBlue(invGreen);
        }
        //outImage is your answer
        return outImage;
    }

    public void testInverted(){
        ImageResource ir = new ImageResource();
        ImageResource inv = makeInversion(ir);
        inv.draw();
        
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource ir = new ImageResource();
            ImageResource inv = makeInversion(ir);
            String fname = ir.getFileName();
            String newName = "inverted-" + fname;
            inv.setFileName(newName);
            inv.save();
            //gray.draw();

    }
    }

    
}
