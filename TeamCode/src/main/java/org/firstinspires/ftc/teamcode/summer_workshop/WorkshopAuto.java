package org.firstinspires.ftc.teamcode.summer_workshop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;

@Autonomous(name="Summer Workshop 2024", group="Robot")
public class WorkshopAuto extends LinearOpMode {

    @Override
    public void runOpMode() {

        // Initialize the drive system variables.
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "left_front");
        DcMotor leftBack = hardwareMap.get(DcMotor.class, "left_back");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "right_front");
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "right_back");

        RobotHardware robotHardware = new RobotHardware(leftFront,leftBack,rightFront,rightBack);
        DriveTrain driveTrain = new SpiderBitsDriveTrain(); // Mechanum drive train

//        NormalizedColorSensor colorSensor = hardwareMap.get(NormalizedColorSensor.class, "color_sensor");
//        WorkshopColorSensor workshopColorSensor = new WorkshopColorSensor(colorSensor);

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "webcam");
        WorkshopAprilTag workshopAprilTag = new WorkshopAprilTag(webcamName);

        // Wait for driver to press PLAY)
        while (opModeInInit()) {
            // Send telemetry message to signify robot waiting;
            telemetry.addData("Status", "Ready");
            telemetry.update();
        }

        while(opModeIsActive()) {
            telemetry.addData("Status", "Active");
            telemetry.update();
            AprilTagPoseFtc aprilTagPosition = workshopAprilTag.getPosition(telemetry);
            if(aprilTagPosition != null){
                telemetry.addData("Position", aprilTagPosition.x);
                if(aprilTagPosition.x < (workshopAprilTag.getScreenWidth() / 2)){
                    driveTrain.turnLeft(robotHardware);
                }
                if(aprilTagPosition.x > (workshopAprilTag.getScreenWidth() / 2)){
                    driveTrain.turnRight(robotHardware);
                }
                sleep(100);
                driveTrain.moveForward(robotHardware);
                sleep(100);
            } else {
                driveTrain.stop(robotHardware);
            }
        }
    }
}
