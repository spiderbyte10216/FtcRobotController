package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOp1_21_24", group = "Mecanum")
public class TeleOp1_21_24 extends LinearOpMode {
    private Servo leftClawOpen = null;
    private Servo rightClawOpen = null;
    private Servo servoFlip = null;
    private Servo actuatorFlip = null;
    private DcMotor liftMotor;
    private DcMotor rightFront;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor actuator;
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime servoTimer = new ElapsedTime();
    private Lift lift;
    private Servo drone = null;

    @Override
    public void runOpMode() {
        printMessage("Initialized");

        leftClawOpen = hardwareMap.get(Servo.class, "leftClawOpen");
        rightClawOpen = hardwareMap.get(Servo.class, "rightClawOpen");
        servoFlip = hardwareMap.get(Servo.class, "servoFlip");
        actuatorFlip = hardwareMap.get(Servo.class, "actuatorFlip");
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        rightFront = hardwareMap.get(DcMotor.class, "frontRight");
        leftFront = hardwareMap.get(DcMotor.class, "frontLeft");
        leftBack = hardwareMap.get(DcMotor.class, "backLeft");
        rightBack = hardwareMap.get(DcMotor.class, "backRight");
        actuator = hardwareMap.get(DcMotor.class, "actuator");
        drone = hardwareMap.get(Servo.class, "drone");

        lift = new Lift(liftMotor, runtime, telemetry);

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

        boolean servoDown = false;


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        double x;
        double y;
        double r;

        waitForStart();
        runtime.reset();

        //clawOpen.setPosition(0);

        while (opModeIsActive()) {
            double liftPower = 0.7;
            double actuatorPower = 0.7;
            y = gamepad1.left_stick_y;
            x = gamepad1.left_stick_x;
            r = gamepad1.right_stick_y;
            String message = "";
            message += x;
            message += ",";
            message += y;
            message += ",";
            message += r;

            printMessage(message);

            double p1, p2, p3, p4;
            double maxval;

            p1 = x + y + r;
            p2 = x + y - r;
            p3 = x - y - r;
            p4 = x - y + r;

            maxval = max(abs(p1),abs(p2));
            maxval = max(maxval,abs(p3));
            maxval = max(maxval,abs(p4));

            if (maxval > 1) {
                p1 *= (1/maxval);
                p2 *= (1/maxval);
                p3 *= (1/maxval);
                p4 *= (1/maxval);
            }


            //Joystick Controls (Does everything)
            leftFront.setPower(p1);
            rightFront.setPower(p2);
            leftBack.setPower(p3);
            rightBack.setPower(p4);

            //telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Flat val", servoFlipFlat);
            //telemetry.update();


            if (gamepad2.left_bumper && gamepad2.right_bumper){
                leftClawOpen.setPosition(0.5);
                rightClawOpen.setPosition(0.8);
            }
            else if (gamepad2.left_bumper) {
                leftClawOpen.setPosition(1);
            }
            else if (gamepad2.right_bumper) {
                rightClawOpen.setPosition(0.2);
            }

            if (gamepad2.dpad_down){
                rightClawOpen.setPosition(0.8);
                leftClawOpen.setPosition(0.5);
                servoFlip.setPosition(0.4);
                lift.flatStartLift();
            }
            else if (gamepad2.dpad_up){
                lift.backboardLift();
            }
            else if(gamepad2.dpad_right){
                lift.slightlyLift();
                servoFlip.setPosition(0.8);
            }
            else if (gamepad2.dpad_left){
                lift.slightlyLift();
            }

            if(gamepad2.x){
                rightClawOpen.setPosition(0.8);
                leftClawOpen.setPosition(0.5);
                servoTimer.reset();
                servoDown = true;
            }
            if(servoDown == true && servoTimer.milliseconds() > 250)
            {
                servoFlip.setPosition(0.8);
                servoDown = false;
            }

            if(gamepad2.y){
                servoFlip.setPosition(0.4);
                servoDown = false;
            }

            if (gamepad1.a) {
                drone.setPosition(0.4);
            }


            if ((gamepad2.left_stick_y>0.1)) {
                liftMotor.setDirection(DcMotor.Direction.REVERSE);
                liftMotor.setPower(liftPower);
                liftMotor.setPower(0);

            }
            if ((gamepad2.left_stick_y<-0.1)) {
                liftMotor.setDirection(DcMotor.Direction.FORWARD);
                liftMotor.setPower(liftPower);
                liftMotor.setPower(0);
            }
            if (gamepad1.left_bumper && gamepad1.right_bumper){
                actuator.setDirection(DcMotor.Direction.REVERSE);
                actuator.setPower(liftPower);
                actuator.setPower(0);
            }
            else if (gamepad1.left_bumper){
                actuator.setDirection(DcMotor.Direction.FORWARD);
                actuator.setPower(actuatorPower);
                actuator.setPower(0);
            }
            else if (gamepad1.right_bumper){
                actuatorFlip.setPosition(0.3);
            }

        }
    }

    public void printMessage(String message){
        telemetry.addData(message, "");
        telemetry.update();
    }

}