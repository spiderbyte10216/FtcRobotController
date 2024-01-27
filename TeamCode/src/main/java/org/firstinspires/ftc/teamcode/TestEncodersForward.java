package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;

@Autonomous(name="Test Encoders Forward", group= "Robot")
public class TestEncodersForward extends LinearOpMode{
    private DcMotor rightFront;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor liftMotor = null;
    private Servo rightClawOpen;
    private Servo leftClawOpen;
    private Servo servoFlip;

    @Override
    public void runOpMode() {

        rightFront = hardwareMap.get(DcMotor.class, "frontRight");
        leftFront = hardwareMap.get(DcMotor.class, "frontLeft");
        leftBack = hardwareMap.get(DcMotor.class, "backLeft");
        rightBack = hardwareMap.get(DcMotor.class, "backRight");
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");

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

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightClawOpen = hardwareMap.get(Servo.class, "rightClawOpen");
        leftClawOpen = hardwareMap.get(Servo.class, "leftClawOpen");
        servoFlip = hardwareMap.get(Servo.class, "servoFlip");



        waitForStart();

        MecanumDrive auto = new MecanumDrive(leftFront, rightFront, leftBack, rightBack, runtime, telemetry, this);


        while (this.opModeIsActive()) {
            servoFlip.setPosition(0.4);
            sleep(1000);
            rightClawOpen.setPosition(0.2);



//            auto.strafeRight(1000,0.15);
//            auto.strafeLeft(1000,0.15);
        }





        }



}