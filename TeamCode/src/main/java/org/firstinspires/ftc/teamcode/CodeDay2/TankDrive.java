// --- IMPORTS ---
// Add package statement
// Import LinearOpMode, TeleOp, DcMotor, and any other needed classes

// --- TELEOP MODE ANNOTATION ---
// Add @TeleOp annotation with name and optional group

public class SimpleTankDrive extends LinearOpMode {  // Class declaration

    // --- HARDWARE DECLARATIONS ---
    // Declare DcMotor variables for left and right drive motors

    @Override
    public void runOpMode() {

        // --- HARDWARE MAP ---
        // Map the motors to their configuration names
        // Reverse one motor so that forward stick makes both wheels go forward

        // --- TELEMETRY: Initialization ---
        // Display "Initialized" or other status message
        // Call telemetry.update()

        // --- WAIT FOR START ---
        // Call waitForStart()

        // --- MAIN LOOP ---
        while (opModeIsActive()) {
            // --- READ JOYSTICK INPUT ---
            // Get left joystick Y value for left motor power
            // Get right joystick Y value for right motor power
            // Reverse joystick values if needed

            // --- SET MOTOR POWERS ---
            // Apply joystick values to left and right motors

            // --- TELEMETRY: Motor Powers ---
            // Display motor power values for both motors
            // Call telemetry.update()
        }
    }
}
