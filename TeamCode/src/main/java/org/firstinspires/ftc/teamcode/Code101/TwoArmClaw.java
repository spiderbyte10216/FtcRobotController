package org.firstinspires.ftc.teamcode;

// --- IMPORTS ---
import com.acmerobotics.dashboard.FtcDashboard;                // For using FTC Dashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry; // For showing telemetry on both driver station and dashboard
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;   // Basic OpMode structure
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;         // Lets the code run as a TeleOp
import com.qualcomm.robotcore.hardware.DcMotor;                // For motors (not used here, but often needed)
import com.qualcomm.robotcore.hardware.DcMotorSimple;          // For motor direction control
import com.qualcomm.robotcore.util.ElapsedTime;                // Timer
import com.qualcomm.robotcore.hardware.Servo;                  // For controlling servos

// --- TELEOP NAME ---

public class TwoArmClaw extends LinearOpMode {

    // --- VARIABLES ---
    // Make a timer called runtime
    // Make a dashboard variable called ftcDashboard
    // Make two servos called claw20 and claw21

    @Override
    public void runOpMode() {

        // --- SETUP DASHBOARD ---
        // Connect the dashboard to telemetry

        // --- SHOW "INITIALIZING" ON DRIVER STATION ---
        // Tell the driver the robot is starting up

        // --- CONNECT HARDWARE ---
        // Link claw20 and claw21 to servos from the config

        // --- SHOW "READY" ON DRIVER STATION ---
        // Tell the driver the robot is ready

        // --- WAIT FOR START BUTTON ---
        // Pause until the match starts

        // --- RESET TIMER ---
        // Reset the runtime clock

        // --- MAIN LOOP ---
        while (opModeIsActive()) {
            // --- GAMEPAD CONTROLS ---
            // If right bumper is pressed, open both claws
            // If left bumper is pressed, close both claws

            // --- UPDATE TELEMETRY ---
            // Show info on the driver station
        }
    }
}
