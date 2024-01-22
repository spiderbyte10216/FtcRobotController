package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
    static Servo rightClawOpen;
    static Servo leftClawOpen;
    static Servo servoFlip;
    ElapsedTime runtime;
    Telemetry telemetry;
    Claw(Servo rightClawOpen, Servo leftClawOpen, Servo servoFlip, ElapsedTime runtime, Telemetry telemetry){
        this.rightClawOpen = rightClawOpen;
        this.leftClawOpen = leftClawOpen;
        this.servoFlip = servoFlip;
        this.runtime = runtime;
        this.telemetry = telemetry;


    }

    public static void setRightClawOpen(){
        rightClawOpen.setPosition(0.7);


    }

    public static void setLeftClawOpen(){
        leftClawOpen.setPosition(0.7);

    }

    public static void closeBothClaws(){
        leftClawOpen.setPosition(1);
        rightClawOpen.setPosition(0.4);
    }

    public static void setServoFlipUp(){
        servoFlip.setPosition(0.2);
    }

    public static void setServoFlipDownBackboard(){
        rightClawOpen.setPosition(0.4);
        leftClawOpen.setPosition(1);
        servoFlip.setPosition(0.7);
    }

    public static void setServoFlipParallel(){
        servoFlip.setPosition(0.4);
    }
}