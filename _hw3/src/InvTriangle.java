import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class InvTriangle {
    private double leftX, leftY, rightX, rightY, bottomX, bottomY;

    public InvTriangle(double leftX, double leftY, double rightX,
            double rightY, double bottomX, double bottomY) {
        this.leftX = leftX; this.leftY = leftY;
        this.rightX = rightX; this.rightY = rightY;
        this.bottomX = bottomX; this.bottomY = bottomY;
    }

    public void draw(GraphicsContext gc, Color color) {
        gc.setFill(color);
        gc.fillPolygon(
            new double[]{leftX, rightX, bottomX},
            new double[]{leftY, rightY, bottomY},
            3
        );
    }

    public double smallestDimension() {
        return Math.min(bottomY - leftY, rightX - leftX);
    }

    //Returns triables of next smallest size in fractal
    public InvTriangle[] fractalize() {
        InvTriangle[] three = new InvTriangle[3];

        three[0] = new InvTriangle(
            leftX - (bottomX - leftX) / 2, bottomY - (bottomY - leftY) / 2,
            leftX + (bottomX - leftX) / 2, bottomY - (bottomY - leftY) / 2,
            leftX, bottomY
        );

        three[1] = new InvTriangle(
            rightX - (rightX - bottomX) / 2, bottomY - (bottomY - rightY) / 2,
            rightX + (rightX - bottomX) / 2, bottomY - (bottomY - rightY) / 2,
            rightX, bottomY
        );

        three[2] = new InvTriangle(
            bottomX - (bottomX - leftX) / 2, leftY - (bottomY - leftY) / 2,
            bottomX + (rightX - bottomX) / 2, rightY - (bottomY - rightY) / 2,
            bottomX, leftY
        );

        return three;
    }

    public String toString() {
        return "InvTriangle[("
            + leftX + ", " + leftY + ") ("
            + rightX + ", " + rightY + ") ("
            + bottomX + ", " + bottomY + ")]";
    }
}
