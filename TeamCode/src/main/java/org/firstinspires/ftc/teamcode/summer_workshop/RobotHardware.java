package org.firstinspires.ftc.teamcode.summer_workshop;

import com.qualcomm.robotcore.hardware.DcMotor;

public class RobotHardware {

    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;

    public RobotHardware(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack){
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        this.rightFront = rightFront;
        this.rightBack = rightBack;

    }
}
