package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TwoArmClaw")
public class TwoArmClaw extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    public FtcDashboard ftcDashboard;

    private Servo claw20;
    private Servo claw21;

    @Override
    public void runOpMode() {
        this.ftcDashboard = FtcDashboard.getInstance();
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Initialize claw servos
        claw20 = hardwareMap.get(Servo.class, "claw20");
        claw21 = hardwareMap.get(Servo.class, "claw21");


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            telemetry.addData("Loop running", true);
            if (gamepad1.right_bumper){
                claw20.setPosition(0.0);
                claw21.setPosition(1.0);
                telemetry.addData("Claw open","Clicked");
           }
            if (gamepad1.left_bumper) {
                claw20.setPosition(0.5);
                claw21.setPosition(0.5);
                telemetry.addData("Claw close","Clicked");
            }
            telemetry.update();
        }
    }
}
