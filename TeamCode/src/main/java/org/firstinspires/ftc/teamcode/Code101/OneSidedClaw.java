package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "OneSidedClaw")
public class OneSidedClaw extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    public FtcDashboard ftcDashboard;

    private Servo claw1;
    @Override
    public void runOpMode() {
        this.ftcDashboard = FtcDashboard.getInstance();
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Initialize claw servo
        claw1 = hardwareMap.get(Servo.class, "claw1");


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            //OPEN CLAW
            if (gamepad1.a) {
                claw1.setPosition(0.75);
                telemetry.addData("claw open", "clicked");
            }
            //CLOSE CLAW
            else if (gamepad1.b) {
                claw1.setPosition(0.25);
                telemetry.addData("claw close", "clicked");
            }
            telemetry.update();
        }
    }
}
