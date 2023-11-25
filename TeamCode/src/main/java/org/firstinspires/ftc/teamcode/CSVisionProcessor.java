package org.firstinspires.ftc.teamcode;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.List;
import java.util.ArrayList;

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import org.firstinspires.ftc.robotcore.external.ExportToBlocks;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Size;
import org.opencv.core.Point;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;


public class CSVisionProcessor  extends BlocksOpModeCompanion implements VisionProcessor{
    //uncomment and determine the correct starting rectangles for your robot (If Using Java or this class directly)
    private Rect rectLeft;// =
    // ew Rect(100, 202, 80, 80);
    private Rect rectMiddle;// = new Rect(200, 202, 80, 80);

    private Rect rectRight;// = new Rect(300, 202, 80, 80);



    StartingPosition selection = StartingPosition.NONE;

    Mat submat = new Mat();
    Mat hsvMat = new Mat();

    private static CSVisionProcessor _csVision;

    @ExportToBlocks(
            comment = "Custom CenterStage Vision Processor",
            tooltip = "Auto OpMode Vision",
            parameterLabels = {}
            //parameterLabels = {"Width", "Left X", "Left Y", "Middle X", "Middle Y", "Right X", "Right Y"}
    )
    public static VisionProcessor getCSVision(
            int width,
            int leftX, int leftY,
            int middleX, int middleY,
            int rightX, int rightY
    ){
        _csVision = new CSVisionProcessor(width, leftX, leftY, middleX, middleY, rightX, rightY);
        return _csVision;
    }

    @ExportToBlocks(
            comment = "Returns the current starting position",
            tooltip = "Auto OpMode Vision Position"

    )
    public static StartingPosition getPosition(){
        return _csVision.getStartingPosition();
    }

    @ExportToBlocks(
            comment = "Returns the current starting position as an integer",
            tooltip = "Auto OpMode Vision Position"

    )
    public static int getIntPosition(){
        StartingPosition pos = _csVision.getStartingPosition();

        if(pos == StartingPosition.LEFT){
            return 1;
        }
        else if(pos == StartingPosition.CENTER){
            return 2;
        }
        else if(pos == StartingPosition.RIGHT){
            return 3;
        }

        return 0;
    }

    public CSVisionProcessor(int width, int leftX, int leftY, int middleX, int middleY, int rightX, int rightY){
        rectLeft = new Rect(leftX, leftY,width, width);
        rectMiddle = new Rect(middleX, middleY, width, width);
        rectRight = new Rect(rightX, rightY, width, width);
    }


    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.cvtColor(frame, hsvMat, Imgproc.COLOR_RGB2HSV);
//
//        double satRectLeft = getAvgSaturation(hsvMat, rectLeft);
//        double satRectMiddle = getAvgSaturation(hsvMat, rectMiddle);
//        double satRectRight = getAvgSaturation(hsvMat, rectRight);
          double ilowH = 106;
          double ilowS = 124;
          double ilowV = 0;

          double ihighH = 134;
          double ihighS = 255;
          double ihighV = 191;
          Mat threshMat = new Mat();
          Scalar lower_hsv = new Scalar(ilowH, ilowS, ilowV);
          Scalar higher_hsv = new Scalar(ihighH, ihighS, ihighV);
          Core.inRange(hsvMat, lower_hsv, higher_hsv, threshMat);

        //apply morphology
          Size kernel1 = new Size(9,9);
          Size kernel2 = new Size(15,15);
          Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, kernel1);
          Mat cleanMat = new Mat();
          Imgproc.morphologyEx(threshMat, cleanMat, Imgproc.MORPH_OPEN, kernel);
          kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, kernel2);
          Imgproc.morphologyEx(cleanMat, cleanMat, Imgproc.MORPH_CLOSE, kernel);

        // get external contours
          List<MatOfPoint> contours = new ArrayList<>();
          Mat hierarchy = new Mat();
          Imgproc.findContours(cleanMat, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//          contours = contours[0] if len(contours) == 2 else contours[1]

//        if ((satRectLeft > satRectMiddle) && (satRectLeft > satRectRight)) {
//            selection = StartingPosition.LEFT;
//
//        }else if ((satRectMiddle > satRectLeft) && (satRectMiddle > satRectRight)){
//            selection = StartingPosition.CENTER;
//
//        }else if ((satRectRight > satRectMiddle) && (satRectRight > satRectLeft)){
//            selection = StartingPosition.RIGHT;
//        }else{

            selection = StartingPosition.NONE;
//        }

        return selection;
    }

    protected double getAvgSaturation(Mat input, Rect rect) {
        submat = input.submat(rect);
        Scalar color = Core.mean(submat);
        return color.val[1];
    }

    private android.graphics.Rect makeGraphicsRect(Rect rect, float  scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);

        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);

        int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);

        return new android.graphics.Rect(left, top, right, bottom);
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext)
    {
        Paint selectedPaint = new Paint();
        selectedPaint.setColor(Color.RED);
        selectedPaint.setStyle(Paint.Style.STROKE);
        selectedPaint.setStrokeWidth(scaleCanvasDensity * 4);

        Paint nonSelected = new Paint();
        nonSelected.setStrokeWidth(scaleCanvasDensity * 4);
        nonSelected.setStyle(Paint.Style.STROKE);
        nonSelected.setColor(Color.GREEN);

        android.graphics.Rect drawRectangleLeft = makeGraphicsRect(rectLeft, scaleBmpPxToCanvasPx);
        android.graphics.Rect drawRectangleMiddle = makeGraphicsRect(rectMiddle, scaleBmpPxToCanvasPx);
        android.graphics.Rect drawRectangleRight = makeGraphicsRect(rectRight, scaleBmpPxToCanvasPx);

        selection = (StartingPosition) userContext;

        switch(selection){
            case LEFT:
                canvas.drawRect(drawRectangleLeft, selectedPaint);
                canvas.drawRect(drawRectangleMiddle, nonSelected);
                canvas.drawRect(drawRectangleRight, nonSelected);
                break;

            case RIGHT:
                canvas.drawRect(drawRectangleLeft, nonSelected);
                canvas.drawRect(drawRectangleMiddle, nonSelected);
                canvas.drawRect(drawRectangleRight, selectedPaint);
                break;
            case CENTER:
                canvas.drawRect(drawRectangleLeft, nonSelected);
                canvas.drawRect(drawRectangleMiddle, selectedPaint);
                canvas.drawRect(drawRectangleRight, nonSelected);
                break;
            case NONE:
                canvas.drawRect(drawRectangleLeft, nonSelected);
                canvas.drawRect(drawRectangleMiddle, nonSelected);
                canvas.drawRect(drawRectangleRight, nonSelected);
                break;

        }

    }

    public StartingPosition getStartingPosition(){
        return selection;
    }

    public enum StartingPosition{
        NONE,
        LEFT,
        RIGHT,
        CENTER
    }

}