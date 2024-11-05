package edu.project4.generation;

import edu.project4.geometry.Point;
import edu.project4.geometry.Rectangle;
import edu.project4.image.FractalImage;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.Random;

public class FractalGenerator {
    private static final double X_MAX = 1.777;
    private static final double X_MIN = -1.777;
    private static final double Y_MAX = 1;
    private static final double Y_MIN = -1;
    private static final int OFFSET = -20;
    private final int width;
    private final int height;
    private final Random random = new Random();
    private final int samplesCount;
    private final int iterationsCount;
    private final List<AffineTransformation> affines;
    private final List<Transformation> variations;
    private final FractalImage image;

    public FractalGenerator(
        Rectangle frame, int samplesCount, int iterationsCount,
        List<AffineTransformation> affines, List<Transformation> variations
    ) {
        this.width = frame.width();
        this.height = frame.height();
        this.iterationsCount = iterationsCount;
        this.samplesCount = samplesCount;
        this.affines = affines;
        this.variations = variations;
        image = new FractalImage(frame);
    }

    private Point applyThroughVariations(Point point) {
        Point resPoint = variations.get(0).apply(point);
        for (int i = 1; i < variations.size(); i++) {
            resPoint = variations.get(i).apply(resPoint);
        }
        return resPoint;
    }

    public FractalImage generate(int threadsCount) {
        //ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        for (int i = 0; i < samplesCount; i++) {
            //executor.execute(() -> {
            Point randomPoint = new Point(
                random.nextDouble() * (X_MAX - X_MIN) + X_MIN,
                random.nextDouble() * (Y_MAX - Y_MIN) + Y_MIN
            );

            for (int j = OFFSET; j < iterationsCount; j++) {
                AffineTransformation affine = affines.get(random.nextInt(affines.size()));
                Point transformedPoint = applyThroughVariations(affine.apply(randomPoint));
                double x = transformedPoint.x();
                double y = transformedPoint.y();

                if (j >= 0 && x >= X_MIN && x <= X_MAX && y >= Y_MIN && y <= Y_MAX) {
                    int newX = width - (int) (((X_MAX - x) / (X_MAX - X_MIN)) * width);
                    int newY = height - (int) (((Y_MAX - y) / (Y_MAX - Y_MIN)) * height);

                    if (newX < width && newY < height) {
                        Color color = affine.getColor();
                        image.renderPixel(newX, newY, color);
                    }
                }
            }
            //});
        }
        //executor.shutdown();
        return image;
    }
}
