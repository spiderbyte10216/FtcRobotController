package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "MotorOnUChannel")
public class MotorOnUChannel extends LinearOpMode {
    private DcMotor uChannel;
    private ElapsedTime runtime = new ElapsedTime();
    public FtcDashboard ftcDashboard;

    @Override
    public void runOpMode() {
        this.ftcDashboard = FtcDashboard.getInstance();
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        //initialize motor
        uChannel = hardwareMap.get(DcMotor.class, "uChannel");

        uChannel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        uChannel.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            //MOTOR SPIN COUNTER-CLOCKWISE
            if (gamepad1.x) {
                uChannel.setPower(1);
                telemetry.addData("motor spin counter-clockwise", "clicked");
            }
            //MOTOR SPIN CLOCKWISE
            else if (gamepad1.y) {
                uChannel.setPower(-1);
                telemetry.addData("motor spin clockwise", "clicked");
            }
            //MOTOR STOP
            else {
                uChannel.setPower(0);
            }
            telemetry.update();
        }
    }
}
