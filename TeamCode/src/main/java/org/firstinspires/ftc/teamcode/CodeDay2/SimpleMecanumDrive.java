// --- IMPORTS ---
// Add package statement
// Import LinearOpMode, TeleOp, DcMotor, and any other needed classes

// --- TELEOP MODE ANNOTATION ---
// Add @TeleOp annotation with name and optional group

public class SimpleMecanumDrive extends LinearOpMode {  // Class declaration

    // --- HARDWARE DECLARATIONS ---
    // Declare DcMotor variables for frontLeft, backLeft, frontRight, and backRight

    @Override
    public void runOpMode() {

        // --- HARDWARE MAP ---
        // Map motors to their configuration names: front_left, back_left, front_right, back_right
        // Reverse left motors so forward stick moves robot forward

        // --- TELEMETRY: Initialization ---
        // Display "Initialized" or other status message
        // Call telemetry.update()

        // --- WAIT FOR START ---
        // Call waitForStart()

        // --- MAIN LOOP ---
        while (opModeIsActive()) {

            // --- READ JOYSTICK INPUT ---
            // Read left stick Y for forward/backward (remember to negate)
            // Read left stick X for strafing left/right
            // Read right stick X for rotation

            // --- CALCULATE MOTOR POWERS ---
            // Calculate frontLeft power as forward + strafe + rotation
            // Calculate backLeft power as forward - strafe + rotation
            // Calculate frontRight power as forward - strafe - rotation
            // Calculate backRight power as forward + strafe - rotation

            // --- NORMALIZE MOTOR POWERS ---
            // Normalize motor powers so none exceed magnitude of 1.0

            // --- SET MOTOR POWERS ---
            // Apply calculated powers to frontLeft, backLeft, frontRight, and backRight motors

            // --- TELEMETRY: Motor Powers ---
            // Display power values for all four motors
            // Call telemetry.update()
        }
    }
}
