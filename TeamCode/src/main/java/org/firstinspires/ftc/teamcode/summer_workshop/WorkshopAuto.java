package org.firstinspires.ftc.teamcode.summer_workshop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;

@Autonomous(name="Summer Workshop 2024", group="Robot")
public class WorkshopAuto extends LinearOpMode {

    @Override
    public void runOpMode() {

        // Initialize the drive system variables.
        DcMotor motor  = hardwareMap.get(DcMotor.class, "motor");
        Servo servo  = hardwareMap.get(Servo.class, "servo");
        RobotHardware robotHardware = new RobotHardware(motor, servo);
        DriveTrain driveTrain; //TODO - change this

        NormalizedColorSensor colorSensor = hardwareMap.get(NormalizedColorSensor.class, "color_sensor");
        WorkshopColorSensor workshopColorSensor = new WorkshopColorSensor(colorSensor);

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "webcam");
        WorkshopAprilTag workshopAprilTag = new WorkshopAprilTag(webcamName);

        // Wait for driver to press PLAY)
        while (opModeInInit()) {

            // Send telemetry message to signify robot waiting;
            telemetry.addData("Status", "Ready");
            telemetry.update();

            //TODO - change this
            AprilTagPoseFtc aprilTagPosition = workshopAprilTag.getPosition();
            NormalizedRGBA colors = workshopColorSensor.getColors();
        }
    }
}
