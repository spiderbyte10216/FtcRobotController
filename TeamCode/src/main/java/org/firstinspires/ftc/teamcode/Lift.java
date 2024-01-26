package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {
    DcMotor liftMotor;
    ElapsedTime runtime;
    Telemetry telemetry;
    Lift(DcMotor liftMotor, ElapsedTime runtime, Telemetry telemetry){
        this.liftMotor = liftMotor;
        this.runtime = runtime;
        this.telemetry = telemetry;

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void slightlyLift(){
        liftMotor.setTargetPosition(150);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(0.35);


    }

    public void backboardLift(){
        liftMotor.setTargetPosition(1500);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(0.35);

    }

    public void flatStartLift(){
        liftMotor.setTargetPosition(0);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(0.35);
    }
}