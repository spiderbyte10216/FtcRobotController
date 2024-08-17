package org.firstinspires.ftc.teamcode.summer_workshop;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {

    //TODO - change this
    public DcMotor leftFront = null;
    public DcMotor leftBack = null;
    public DcMotor rightFront = null;
    public DcMotor rightBack = null;

    public RobotHardware(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack){
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        this.rightFront = rightFront;
        this.rightBack = rightBack;

    }
}
