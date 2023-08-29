package edu.redwoods.cis12.archimedes;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
// import java.util.Arrays;

public class ArchimedesApplicationController {
    @FXML
    private Slider sldrNumSides;
    @FXML
    private Pane drawingArea;

    public void initialize() {
        // Set up slider listener to reset selected values to integers only
        sldrNumSides.valueProperty().addListener((obs, oldval, newVal) -> sldrNumSides.setValue(newVal.intValue()));
        sldrNumSides.valueChangingProperty().addListener((observableValue, wasChanging, changing) -> {
            if (!changing) { // Sliding stopped
                drawInscribedPolygon(0, 0);
            }
        });
        drawInscribedPolygon(268.75, 232.0);
    }

    private void drawInscribedPolygon(double width, double height) {
        // Clear previous children if any
        drawingArea.getChildren().clear();
        double dpCX = Math.max(drawingArea.getWidth() / 2, width);
        double dpCY = Math.max(drawingArea.getHeight() / 2, height);
        double r = 200.0; // Radius of circle
        int n = (int)sldrNumSides.getValue(); // Number of sides of polygon
        double angle = Math.toRadians(360.0 / n);  // Start angle just above
        // Creating Archimedes Circle
        Circle circle = new Circle(dpCX, dpCY, r);
        //Creating an object of the class Polygon
        Polygon polygon = new Polygon();
        // Define points for n-sided polygon inscribed in circle.
        int pointSz = n * 2;
        double currAngle = Math.toRadians(90); // Always start at 90 degrees
        Double[] points = new Double[pointSz];
        for(int i=0; i<pointSz; i+=2) {
            points[i] = dpCX + r * Math.cos(currAngle);
            points[i+1] = dpCY + r * Math.sin(currAngle);
            currAngle += angle;
        }
        //System.out.println(Arrays.toString(points));
        polygon.getPoints().addAll(points);
        drawingArea.getChildren().add(circle);
        drawingArea.getChildren().add(polygon);
    }
}