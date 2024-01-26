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

    //private DcMotor leftLift = null;
    //private DcMotor rightLift = null;
    private DcMotor liftMotor = null;

    private Servo servoFlip = null;
    private Servo rightClawOpen = null;
    private Servo leftClawOpen = null;
    private ElapsedTime runtime = new ElapsedTime();

    private CSVisionProcessor visionProcessor;

    private VisionPortal visionPortal;

    private AprilTagProcessor aprilTagProcessor;
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

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);


        //attachments
        //Add lifts + servo motors to Configuration

        //leftLift = hardwareMap.get(DcMotor.class, "leftLift");
        //rightLift = hardwareMap.get(DcMotor.class, "rightLift");
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");

        rightClawOpen = hardwareMap.get(Servo.class, "rightClawOpen");
        leftClawOpen = hardwareMap.get(Servo.class, "leftClawOpen");
        servoFlip = hardwareMap.get(Servo.class, "servoFlip");
//        rightClawOpen.setPosition(0.1);
//        leftClawOpen.setPosition(0.1);
//        servoFlip.setPosition(0.5);



        MecanumDrive auto = new MecanumDrive(leftFront, rightFront, leftBack, rightBack, runtime, telemetry, this);
        Lift lift = new Lift(liftMotor, runtime, telemetry);
        Claw claw = new Claw(rightClawOpen, leftClawOpen, servoFlip, runtime, telemetry);
        CSVisionProcessor.StartingPosition startingPosition = null;

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        OpenCvWebcam camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        visionProcessor = new CSVisionProcessor(telemetry, camera);
        aprilTagProcessor =  new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .build();
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(visionProcessor)
                .addProcessor(aprilTagProcessor)
                .setCameraResolution(new Size(1280, 720))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .build();

        waitForStart();
        int moveToBackboard = 10000;

        while (state != states.STOP && opModeIsActive()) {
            switch (state) {
                case CHECK_SPIKES:
                    if(!opModeIsActive()) {
                        state = states.STOP;
                        break;
                    }


                    visionPortal.setProcessorEnabled(aprilTagProcessor, false);
                    visionPortal.setProcessorEnabled(visionProcessor, true);
                    while (visionProcessor.getPosition() == null && opModeIsActive()) {
                        telemetry.addLine("starting pos is null");
                        telemetry.update();
                        if(!opModeIsActive()) {
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
                    if(visionPortal.getCameraState() == VisionPortal.CameraState.STREAMING){
                        visionPortal.stopStreaming();
                    }
                    break;

                case PLACE_PIXEL_LEFT:
                    telemetry.addData("State: ", state);
                    telemetry.update();
                    if(!opModeIsActive()) {
                        state = states.STOP;
                        break;
                    }

                    servoFlip.setPosition(0.4);
                    lift.slightlyLift();
                    auto.moveForward(500,0.5);
                    lift.flatStartLift();
                    claw.setRightClawOpen();
                    claw.closeBothClaws();
                    lift.slightlyLift();

                    state = states.GO_TO_BACKBOARD;
                    break;

                case PLACE_PIXEL_CENTER:
                    telemetry.addData("State: ", state);
                    telemetry.update();
                    if(!opModeIsActive()) {
                        state = states.STOP;
                        break;
                    }

                    servoFlip.setPosition(0.4);
                    lift.slightlyLift();
                    auto.strafeRight(500,0.5);
                    auto.moveForward(1000,0.5);
                    lift.flatStartLift();
                    claw.setRightClawOpen();
                    claw.closeBothClaws();
                    lift.slightlyLift();
                    state = states.GO_TO_BACKBOARD;
                    break;

                case PLACE_PIXEL_RIGHT:
                    telemetry.addData("State: ", state);
                    telemetry.update();
                    if(!opModeIsActive()) {
                        state = states.STOP;
                        break;
                    }

                    servoFlip.setPosition(0.4);
                    lift.slightlyLift();
                    auto.strafeRight(1000,0.5);
                    auto.moveForward(500,0.5);
                    lift.flatStartLift();
                    claw.setRightClawOpen();
                    claw.closeBothClaws();
                    lift.slightlyLift();

                    state = states.GO_TO_BACKBOARD;
                    break;

//                case BACK_INTO_WALL:
//                    if(!opModeIsActive()) {
//                        state = states.STOP;
//                        break;
//                    }
//                    auto.moveBackward(1000,0.5);
//                    state = states.GO_TO_BACKBOARD;
//                    break;

                case GO_TO_BACKBOARD:
                    telemetry.addData("State: ", state);
                    telemetry.update();
                    if(!opModeIsActive()) {
                        state = states.STOP;
                        break;
                    }


                    auto.moveBackward(3000,0.5);
                    auto.strafeLeft(2000,0.5);
                    auto.turnCounterClockwise(1500,0.5);
                    auto.strafeRight(500,0.5);


                    state = states.STRAFE_SCAN;
                    break;
//                case CHECK_DISTANCE:
//                    if(!opModeIsActive()) {
//                        state = states.STOP;
//                        break;
//                    }
//                    //check distance from backdrop
//                    //if too close move backwards
//                    //if too far move forwards
//                    state = states.STRAFE_SCAN;
//                    break;
                case STRAFE_SCAN:
                    telemetry.addData("State: ", state);
                    telemetry.update();
                    if(!opModeIsActive()) {
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

                    state = states.PLACE_PIXEL_ON_BACKDROP;

//                    auto.moveForward(500,0.5);
//                    moveToBackboard -= 500;
//                    if (moveToBackboard < 0) {
//                        state = states.PLACE_PIXEL_ON_BACKDROP;
//                    }

                    break;
                case PLACE_PIXEL_ON_BACKDROP:
                    telemetry.addData("State: ", state);
                    telemetry.update();
                    if(!opModeIsActive()) {
                        state = states.STOP;
                        break;
                    }
                    auto.turnCounterClockwise(2000,0.5);
                    lift.backboardLift();
                    claw.setLeftClawOpen();
                    claw.closeBothClaws();
                    //raise lift
                    //place claw against backboard
                    //open claw to place pixel
                    state = states.PARK_TO_THE_RIGHT;
                    break;
                case PARK_TO_THE_RIGHT:
                    telemetry.addData("State: ", state);
                    telemetry.update();
                    if(!opModeIsActive()) {
                        state = states.STOP;
                        break;
                    }
                    lift.slightlyLift();
                    auto.moveBackward(500,0.5);
                    auto.strafeRight(2000,0.5);
                    auto.moveForward(1500,0.5);
                    state = states.STOP;
                    break;
                case STOP:

                    break;
            }
        }
    }
}