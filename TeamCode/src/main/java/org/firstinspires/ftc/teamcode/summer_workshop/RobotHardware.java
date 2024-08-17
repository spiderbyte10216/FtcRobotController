package org.firstinspires.ftc.teamcode.summer_workshop;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {

    //TODO - change this
    public DcMotor motor   = null;
    public Servo   servo = null;

    public RobotHardware(DcMotor motor, Servo servo){
        this.motor = motor;
        this.servo = servo;
    }
}
