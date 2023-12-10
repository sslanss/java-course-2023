package edu.project4.image;

import edu.project4.geometry.Rectangle;
import java.awt.Color;

public class FractalImage {
    private final Pixel[][]pixels;
    public FractalImage(Rectangle frame){
        int width = frame.width();
        int height = frame.height();
        pixels = new Pixel[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y] = new Pixel();
            }
        }
    }

    public void renderPixel (int x, int y, Color color){
        if (pixels[x][y].counter == 0) {
            pixels[x][y].red = color.getRed();
            pixels[x][y].green = color.getGreen();
            pixels[x][y].blue = color.getBlue();
        } else {
            pixels[x][y].red = (pixels[x][y].red +
                color.getRed()) / 2;
            pixels[x][y].green = (pixels[x][y].green +
                color.getGreen()) / 2;
            pixels[x][y].blue = (pixels[x][y].blue +
                color.getBlue()) / 2;
        }
        pixels[x][y].counter++;
    }

}
