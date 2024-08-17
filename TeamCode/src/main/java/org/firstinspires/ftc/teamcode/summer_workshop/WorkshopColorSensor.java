package org.firstinspires.ftc.teamcode.summer_workshop;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

public class WorkshopColorSensor {
    /** The variable to store a reference to our color sensor hardware object */
    NormalizedColorSensor colorSensor;

    static final double     WHITE_THRESHOLD = 0.5;  // spans between 0.0 - 1.0 from dark to light
    static final double     APPROACH_SPEED  = 0.25;

    public WorkshopColorSensor(NormalizedColorSensor colorSensor) {
        this.colorSensor = colorSensor;

        // If necessary, turn ON the white LED (if there is no LED switch on the sensor)
        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight)colorSensor).enableLight(true);
        }

        // Some sensors allow you to set your light sensor gain for optimal sensitivity...
        // See the SensorColor sample in this folder for how to determine the optimal gain.
        // A gain of 15 causes a Rev Color Sensor V2 to produce an Alpha value of 1.0 at about 1.5" above the floor.
        colorSensor.setGain(15);
    }

    public NormalizedRGBA getColors() {
        //TODO - change this
        return null;
    }
}
