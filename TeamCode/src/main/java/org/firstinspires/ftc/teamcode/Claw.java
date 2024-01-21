package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
    Servo rightClawOpen;
    Servo leftClawOpen;
    Servo servoFlip;
    ElapsedTime runtime;
    Telemetry telemetry;
    Claw(Servo rightClawOpen, Servo leftClawOpen, Servo servoFlip, ElapsedTime runtime, Telemetry telemetry){
        this.rightClawOpen = rightClawOpen;
        this.leftClawOpen = leftClawOpen;
        this.servoFlip = servoFlip;
        this.runtime = runtime;
        this.telemetry = telemetry;


    }

    public void setRightClawOpen(){
        rightClawOpen.setPosition(0.7);


    }

    public void setLeftClawOpen(){
        leftClawOpen.setPosition(0.7);

    }

    public void closeBothClaws(){
        leftClawOpen.setPosition(1);
        rightClawOpen.setPosition(0.4);
    }

    public void setServoFlipUp(){
        servoFlip.setPosition(0.2);
    }

    public void setServoFlipDownBackboard(){
        rightClawOpen.setPosition(0.4);
        leftClawOpen.setPosition(1);
        servoFlip.setPosition(0.7);
    }

    public void setServoFlipParallel(){
        servoFlip.setPosition(0.4);
    }
}