package org.firstinspires.ftc.teamcode.summer_workshop;

public interface DriveTrain {
    void moveForward(RobotHardware hardware);
    void moveBackwards(RobotHardware hardware);
    void turnRight(RobotHardware hardware);
    void turnLeft(RobotHardware hardware);
    void stop(RobotHardware hardware);
}
