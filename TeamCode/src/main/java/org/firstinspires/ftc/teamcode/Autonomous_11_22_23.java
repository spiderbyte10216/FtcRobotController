package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.List;

public class Autonomous_11_22_23 {
    public static class WebcamQRCodeDetection {

        {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        }

        public static void main(String[] args) {
            VideoCapture cam = new VideoCapture();
            cam.open(0); // Use 0 for the default camera

            if (!cam.isOpened()) {
                System.out.println("Error: Unable to find capture device");
                return;
            }

            Mat raw = new Mat();
            cam.read(raw);

            if (raw.empty()) {
                System.out.println("Capture Failed");
            } else {
                System.out.println("Capture Not failed");
            }

            while (true) {
                Mat hsv = new Mat();
                Imgproc.cvtColor(raw, hsv, Imgproc.COLOR_BGR2HSV);

                Scalar low = new Scalar(115, 125, 13);
                Scalar high = new Scalar(150, 235, 211);

                Mat mask = new Mat();
                Core.inRange(hsv, low, high, mask);

                Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(9, 9));
                Imgproc.morphologyEx(mask, mask, Imgproc.MORPH_OPEN, kernel);

                kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(15, 15));
                Imgproc.morphologyEx(mask, mask, Imgproc.MORPH_CLOSE, kernel);

                Mat hierarchy = new Mat();
                Mat clean = mask.clone();
                List<MatOfPoint> contours;
                contours = null;
                Imgproc.findContours(clean, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

                for (int i = 0; i < contours.size(); i++) {
                    // Get rotated rectangle from contour
                    RotatedRect rotRect = Imgproc.minAreaRect(new MatOfPoint2f(contours.get(i).toArray()));
                    Point[] vertices = new Point[4];
                    rotRect.points(vertices);
                    for (int j = 0; j < 4; j++) {
                        Imgproc.line(raw, vertices[j], vertices[(j + 1) % 4], new Scalar(0, 0, 0), 2);
                    }
                }

                // Display the image or do something else with it
                // ...

                // Release Mat objects
                hsv.release();
                mask.release();
                hierarchy.release();
                clean.release();

                boolean conditionToBreakLoop;
                conditionToBreakLoop = false;
                if (conditionToBreakLoop) {
                    break;
                }
            }

            cam.release();
            System.exit(0);
        }
    }

}
