/**
 * JavaFX Template code modified from http://docs.oracle.com/javase/8/javafx/graphics-tutorial/canvas.htm
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class FractalCanvas extends Application {
    public static final double XSIZE = 500;
    public static final double YSIZE = XSIZE * Math.sqrt(2) / 2;

    public static final int THRESHOLD = 4;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Fractal");
        Group root = new Group();
        Canvas canvas = new Canvas(XSIZE,YSIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        fractal(gc);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void fractal(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillPolygon(
                new double[]{0, XSIZE/2, XSIZE},
                new double[]{YSIZE, 0, YSIZE},
                3
        );

        List<InvTriangle> triangles = new LinkedList<InvTriangle>() {{
            add(new InvTriangle(
                XSIZE/4, YSIZE/2,
                XSIZE*3/4, YSIZE/2,
                XSIZE/2, YSIZE
            ));
        }};

        while (triangles.get(0).smallestDimension() > 4) {
            List<InvTriangle> newTriangles = new LinkedList<InvTriangle>();

            for (InvTriangle tri : triangles) {
                tri.draw(gc, Color.WHITE);
                newTriangles.addAll(Arrays.asList(tri.fractalize()));
            }

            triangles = newTriangles;
        }
    }
}

