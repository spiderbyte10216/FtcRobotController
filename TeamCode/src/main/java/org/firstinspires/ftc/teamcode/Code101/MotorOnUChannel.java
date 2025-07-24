package org.firstinspires.ftc.teamcode;

// --- IMPORTS ---
import com.acmerobotics.dashboard.FtcDashboard;                // For using FTC Dashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry; // For showing telemetry on both driver station and dashboard
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;   // Basic OpMode structure
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;         // Lets the code run as a TeleOp
import com.qualcomm.robotcore.hardware.DcMotor;                // For using motors
import com.qualcomm.robotcore.hardware.DcMotorSimple;          // For motor direction control
import com.qualcomm.robotcore.util.ElapsedTime;                // Timer

// --- TELEOP NAME ---

public class MotorOnUChannel extends LinearOpMode {

    // --- VARIABLES ---
    // Make a DcMotor called uChannel
    // Make a timer called runtime
    // Make a dashboard variable called ftcDashboard

    @Override
    public void runOpMode() {

        // --- SETUP DASHBOARD ---
        // Connect the dashboard to telemetry

        // --- SHOW "INITIALIZING" ON DRIVER STATION ---
        // Tell the driver the robot is starting up

        // --- CONNECT HARDWARE ---
        // Link uChannel to a motor from the config

        // --- SET MOTOR MODE AND DIRECTION ---
        // Set motor to run without encoders
        // Set motor direction (FORWARD or REVERSE)

        // --- SHOW "READY" ON DRIVER STATION ---
        // Tell the driver the robot is ready

        // --- WAIT FOR START BUTTON ---
        // Pause until the match starts

        // --- RESET TIMER ---
        // Reset the runtime clock

        // --- MAIN LOOP ---
        while (opModeIsActive()) {
            // --- GAMEPAD CONTROLS ---
            // If X is pressed, spin motor counter-clockwise
            // If Y is pressed, spin motor clockwise
            // Else, stop the motor

            // --- UPDATE TELEMETRY ---
            // Show info on the driver station
        }
    }
}
