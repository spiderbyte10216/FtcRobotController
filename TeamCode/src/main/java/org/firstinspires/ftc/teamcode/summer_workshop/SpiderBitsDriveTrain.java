package org.firstinspires.ftc.teamcode.summer_workshop;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SpiderBitsDriveTrain implements DriveTrain {
    @Override
    public void moveForward(RobotHardware hardware) {
        hardware.leftFront.setPower(0.5);
        hardware.rightFront.setPower(0.5);
        hardware.leftBack.setPower(0.5);
        hardware.rightBack.setPower(0.5);
    }
    @Override
    public void moveBackwards(RobotHardware hardware) {
        hardware.leftFront.setPower(-0.5);
        hardware.rightFront.setPower(-0.5);
        hardware.leftBack.setPower(-0.5);
        hardware.rightBack.setPower(-0.5);
    }
    @Override
    public void turnRight(RobotHardware hardware) {
        hardware.leftFront.setPower(0.5);
        hardware.rightFront.setPower(-0.5);
        hardware.leftBack.setPower(0.5);
        hardware.rightBack.setPower(-0.5);
    }
    @Override
    public void turnLeft(RobotHardware hardware) {
        hardware.leftFront.setPower(-0.5);
        hardware.rightFront.setPower(0.5);
        hardware.leftBack.setPower(-0.5);
        hardware.rightBack.setPower(0.5);
    }
    @Override
    public void stop(RobotHardware hardware) {
        hardware.leftFront.setPower(0);
        hardware.rightFront.setPower(0);
        hardware.leftBack.setPower(0);
        hardware.rightBack.setPower(0);
    }
    public void strafeRight(RobotHardware hardware) {
        hardware.leftFront.setPower(0.5);
        hardware.rightFront.setPower(-0.5);
        hardware.leftBack.setPower(-0.5);
        hardware.rightBack.setPower(0.5);
    }
    public void strafeLeft(RobotHardware hardware) {
        hardware.leftFront.setPower(-0.5);
        hardware.rightFront.setPower(0.5);
        hardware.leftBack.setPower(0.5);
        hardware.rightBack.setPower(-0.5);
    }
}
