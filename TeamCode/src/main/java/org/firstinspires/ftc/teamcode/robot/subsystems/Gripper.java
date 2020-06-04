package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Gripper implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components
    private Servo wrist;
    private Servo grip;

    // State and interface variables
    private double wristPos;

    private WristState wristState   = WristState.START;
    private GripState gripState     = GripState.OPEN;

    // Constructor
    public Gripper(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // State enum definitions
    public enum WristState {
        PLACE   (0.20),
        GRAB    (1.00),
        START   (0.30),
        STORE   (0.37),
        MANUAL  (-1.00);

        private final double pos;

        WristState(double pos) {
            this.pos = pos;
        }
    }
    public enum GripState {
        OPEN(1.00),
        GRIP(0.00);

        private final double pos;

        GripState(double pos) { this.pos = pos; }
    }

    // Subsystem control (called by commands)
    public void setWristState(WristState wristState) {
        this.wristState = wristState;
        if(!wristState.equals(WristState.MANUAL)) wristPos = wristState.pos;
    }
    public void setGripState(GripState gripState) {
        this.gripState = gripState;
    }

    public void setWristPos(double pos) { this.wristPos = Range.clip(pos, 0.0, 1.0); }

    // Getters
    public double getWristPos() { return wristPos; }

    // Subsystem initialization ( similar to hardware.init(hardwareMap) )
    @Override
    public void initHardware() {

        // Get wrist and grip servos
        wrist   = hardwareMap.servo.get("wrist");
        grip    = hardwareMap.servo.get("gripper");
    }


    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {
        wrist.setPosition(wristPos);
        grip.setPosition(gripState.pos);
    }
}
