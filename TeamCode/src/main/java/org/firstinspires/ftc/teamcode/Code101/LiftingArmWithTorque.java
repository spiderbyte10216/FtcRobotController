package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "LiftingArmWithTorque")
public class LiftingArmWithTorque extends LinearOpMode {
    private DcMotor liftArm;
    private ElapsedTime runtime = new ElapsedTime();
    public FtcDashboard ftcDashboard;

    @Override
    public void runOpMode() {
        this.ftcDashboard = FtcDashboard.getInstance();
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Initialize drivetrain motors
        liftArm = hardwareMap.get(DcMotor.class, "liftArm");
        //right = hardwareMap.get(DcMotor.class, "Right");

        liftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftArm.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                liftArm.setPower(0.3);
            } else if (gamepad1.dpad_down)  {
                liftArm.setPower(-0.3);
            } else {
                liftArm.setPower(0.0);
            }

        }
    }
}
