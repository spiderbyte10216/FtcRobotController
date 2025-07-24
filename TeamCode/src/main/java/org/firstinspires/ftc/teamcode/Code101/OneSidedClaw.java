package org.firstinspires.ftc.teamcode;

// --- IMPORTS ---
import com.acmerobotics.dashboard.FtcDashboard;             // For using FTC Dashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry; // For showing telemetry on both driver station and dashboard
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; // Basic OpMode structure
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;       // Lets the code run as a TeleOp
import com.qualcomm.robotcore.util.ElapsedTime;              // Timer
import com.qualcomm.robotcore.hardware.Servo;                // For controlling servos

// --- TELEOP NAME ---

public class OneSidedClaw extends LinearOpMode {

    // --- VARIABLES ---
    // Make a timer called runtime
    // Make a dashboard variable called ftcDashboard
    // Make a servo called claw1

    @Override
    public void runOpMode() {

        // --- SETUP DASHBOARD ---
        // Connect the dashboard to telemetry

        // --- SHOW "INITIALIZING" ON DRIVER STATION ---
        // Tell the driver the robot is starting up

        // --- CONNECT HARDWARE ---
        // Link claw1 to the servo from the config

        // --- SHOW "READY" ON DRIVER STATION ---
        // Tell the driver the robot is ready

        // --- WAIT FOR START BUTTON ---
        // Pause until the match starts

        // --- RESET TIMER ---
        // Reset the runtime clock

        // --- MAIN LOOP ---
        while (opModeIsActive()) {
            // --- GAMEPAD CONTROLS ---
            // If A is pressed, open the claw
            // If B is pressed, close the claw

            // --- UPDATE TELEMETRY ---
            // Show info on the driver station
        }
    }
}
