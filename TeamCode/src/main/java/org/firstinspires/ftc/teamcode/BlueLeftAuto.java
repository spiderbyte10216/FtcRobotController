package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.List;

@Autonomous(name="BlueLeftAuto", group= "Robot")
public class BlueLeftAuto extends LinearOpMode {

    /* Declare OpMode members. */
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;

    private DcMotor leftLift = null;
    private DcMotor rightLift = null;

    private Servo servoFlip = null;
    private Servo clawOpen = null;
    private ElapsedTime runtime = new ElapsedTime();
    private enum states {
        CHECK_SPIKES,
        PLACE_PIXEL_CENTER,
        PLACE_PIXEL_RIGHT,
        PLACE_PIXEL_LEFT,
        BACK_INTO_WALL,
        GO_TO_BACKBOARD,
        CHECK_DISTANCE,
        STRAFE_SCAN,
        CENTER_APRIL_TAGS,
        PLACE_PIXEL_ON_BACKDROP,
        PARK_TO_THE_RIGHT,
        STOP
    }
    private states state;

    @Override
    public void runOpMode() {
        state = states.CHECK_SPIKES;
        rightFront = hardwareMap.get(DcMotor.class, "frontRight");
        leftFront = hardwareMap.get(DcMotor.class, "frontLeft");
        leftBack = hardwareMap.get(DcMotor.class, "backLeft");
        rightBack = hardwareMap.get(DcMotor.class, "backRight");


        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);


        //attachments
        //Add lifts + servo motors to Configuration

        leftLift = hardwareMap.get(DcMotor.class, "leftLift");
        rightLift = hardwareMap.get(DcMotor.class, "rightLift");

        clawOpen = hardwareMap.get(Servo.class, "clawOpen");
        servoFlip = hardwareMap.get(Servo.class, "servoFlip");
        clawOpen.setPosition(0.1);
        servoFlip.setPosition(0.5);

        waitForStart();

        MecanumDrive auto = new MecanumDrive(leftFront, rightFront, leftBack, rightBack, runtime, telemetry);
        CSVisionProcessor.StartingPosition startingPosition = null;

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        OpenCvWebcam camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        CSVisionProcessor visionProcessor = new CSVisionProcessor(telemetry, camera);
        AprilTagProcessor aprilTagProcessor =  new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .build();
        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(visionProcessor)
                .setCameraResolution(new Size(1280, 720))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .build();

        int moveToBackboard = 10000;

        while (state != states.STOP) {
            switch (state) {
                case CHECK_SPIKES:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }


                    visionPortal.setProcessorEnabled(aprilTagProcessor, false);
                    visionPortal.setProcessorEnabled(visionProcessor, true);

                    while (visionProcessor.getPosition() == null) {
                        telemetry.addLine("starting pos is null");
                        telemetry.update();
                        if(autoOver()) {
                            state = states.STOP;
                            break;
                        }
                    }
                    telemetry.addData("Position: ", visionProcessor.getPosition());
                    telemetry.update();
                    startingPosition = visionProcessor.getPosition();
                    if(startingPosition == CSVisionProcessor.StartingPosition.LEFT) {
                        state = states.PLACE_PIXEL_LEFT;
                    } else if (startingPosition == CSVisionProcessor.StartingPosition.RIGHT) {
                        state = states.PLACE_PIXEL_RIGHT;
                    } else {
                        state = states.PLACE_PIXEL_CENTER;
                    }
                    visionPortal.stopStreaming();
                    break;

                case PLACE_PIXEL_LEFT:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }
                    auto.strafeLeft(3500,0.5);



                    state = states.BACK_INTO_WALL;
                    break;

                case PLACE_PIXEL_CENTER:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }
                    state = states.BACK_INTO_WALL;
                    break;

                case PLACE_PIXEL_RIGHT:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }
                    state = states.BACK_INTO_WALL;
                    break;

                case BACK_INTO_WALL:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }
                    auto.moveBackward(1000,0.5);
                    state = states.GO_TO_BACKBOARD;
                    break;
                case GO_TO_BACKBOARD:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }
                    auto.moveForward(500, 0.5);
                    auto.turnCounterClockwise(500,0.5);
                    auto.moveForward(1000,0.5);
                    state = states.CHECK_DISTANCE;
                    break;
                case CHECK_DISTANCE:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }
                    //check distance from backdrop
                    //if too close move backwards
                    //if too far move forwards
                    state = states.STRAFE_SCAN;
                    break;
                case STRAFE_SCAN:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }

                    visionPortal.setProcessorEnabled(aprilTagProcessor, true);
                    visionPortal.setProcessorEnabled(visionProcessor, false);

                    int aprilTagId;
                    if (startingPosition == CSVisionProcessor.StartingPosition.LEFT) {
                        aprilTagId = 0;
                    } else if (startingPosition == CSVisionProcessor.StartingPosition.CENTER) {
                        aprilTagId = 1;
                    } else {
                        aprilTagId = 2;
                    }
                    List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
                    telemetry.addData("# AprilTags Detected", currentDetections.size());

                    Point position = null;
                    // Step through the list of detections and display info for each one.
                    for (AprilTagDetection detection : currentDetections) {
                        if (detection.id == aprilTagId) {
                            position = detection.center;
                            break;
                        }
                    }
                    if (position != null) {
                        if (position.x > 640) {
                            auto.strafeRight(500, 0.5);
                        } else if (position.x < 640) {
                            auto.strafeLeft(500,0.5);
                        }
                    }

                    auto.moveForward(500,0.5);
                    moveToBackboard -= 500;
                    if (moveToBackboard < 0) {
                        state = states.PLACE_PIXEL_ON_BACKDROP;
                    }

                    break;
                case PLACE_PIXEL_ON_BACKDROP:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }
                    auto.moveForward(500,0.5);
                    //raise lift
                    //place claw against backboard
                    //open claw to place pixel
                    state = states.PARK_TO_THE_RIGHT;
                    break;
                case PARK_TO_THE_RIGHT:
                    if(autoOver()) {
                        state = states.STOP;
                        break;
                    }
                    auto.strafeRight(1000,0.5);
                    auto.moveForward(500,0.5);
                    state = states.STOP;
                    break;
                case STOP:

                    break;
            }
        }
    }

    private boolean autoOver() {
        return opModeIsActive();
    }
}