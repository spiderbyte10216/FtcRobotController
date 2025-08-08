// --- IMPORTS ---
// Add package statement
// Import LinearOpMode, TeleOp, DcMotor, and any other needed classes

// --- TELEOP MODE ANNOTATION ---
// Add @TeleOp annotation with name and optional group

public class SimpleHDrive extends LinearOpMode {  // Class declaration

    // --- HARDWARE DECLARATIONS ---
    // Declare DcMotor variables for leftDrive, rightDrive, and centerDrive

    @Override
    public void runOpMode() {

        // --- HARDWARE MAP ---
        // Map the motors to their configuration names: left_drive, right_drive, center_drive
        // Reverse one or more motors as needed so forward stick moves robot forward

        // --- TELEMETRY: Initialization ---
        // Display "Initialized" or other status message
        // Call telemetry.update()

        // --- WAIT FOR START ---
        // Call waitForStart()

        // --- MAIN LOOP ---
        while (opModeIsActive()) {

            // --- READ JOYSTICK INPUT ---
            // Read left stick Y for forward/backward
            // Read left stick X for strafing left/right
            // Read right stick X for rotation (optional)

            // --- CALCULATE MOTOR POWERS ---
            // Calculate left motor power combining forward and rotation
            // Calculate right motor power combining forward and rotation
            // Calculate center motor power based on strafing input

            // --- NORMALIZE POWERS (optional) ---
            // Normalize motor powers so none exceed magnitude of 1.0

            // --- SET MOTOR POWERS ---
            // Apply calculated powers to leftDrive, rightDrive, and centerDrive

            // --- TELEMETRY: Motor Powers ---
            // Display motor power values for all three motors
            // Call telemetry.update()
        }
    }
}
