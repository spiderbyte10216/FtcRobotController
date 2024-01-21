package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.I2cAddr;

/*
 * This OpMode illustrates how to use the Modern Robotics Range Sensor.
 *
 * The OpMode assumes that the range sensor is configured with a name of "sensor_range".
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 *
 * @see <a href="http://modernroboticsinc.com/range-sensor">MR Range Sensor</a>
 */
@TeleOp(name = "Sensor: MR range sensor", group = "Sensor")
public class SensorMRRangeSensorTest extends LinearOpMode {

    ModernRoboticsI2cRangeSensor rangeSensor;

    @Override public void runOpMode() {

        // get a reference to our compass
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Distance Sensor");
        rangeSensor.setI2cAddress(new I2cAddr(0x14));

        // wait for the start button to be pressed
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("STATUS",rangeSensor.status());
            telemetry.addData("FIRMWARE",rangeSensor.read8(ModernRoboticsI2cRangeSensor.Register.FIRMWARE_REV));
            telemetry.addData("MANUFACTURER ID",rangeSensor.read8(ModernRoboticsI2cRangeSensor.Register.MANUFACTURE_CODE));
            telemetry.addData("SENSOR ID",rangeSensor.read8(ModernRoboticsI2cRangeSensor.Register.SENSOR_ID));
            telemetry.addData("ULTRASONIC",rangeSensor.read8(ModernRoboticsI2cRangeSensor.Register.ULTRASONIC));
            telemetry.addData("OPTICAL",rangeSensor.read8(ModernRoboticsI2cRangeSensor.Register.OPTICAL));
//            telemetry.addData("raw ultrasonic", rangeSensor.rawUltrasonic());
//            telemetry.addData("raw optical", rangeSensor.rawOptical());
//            telemetry.addData("cm optical", "%.2f cm", rangeSensor.cmOptical());
//            telemetry.addData("cm", "%.2f cm", rangeSensor.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
    }
}

