package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gripper implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components
    private Servo wrist;
    private Servo grip;

    // State and interface variables
    public static final double WRIST_SCALAR = 0.01;
//    public static final double WRIST_PLACING  = 0.20;
//    public static final double WRIST_GRABBING = 1.00;
//    public static final double WRIST_STARTING = 0.30;
//    public static final double WRIST_STORING  = 0.37;

//    public static final double GRIPPER_CLOSED = 0.0;
//    public static final double GRIPPER_OPEN = 1.0;

    private WristState wristState;
    private GripState gripState;

    // Constructor
    public Gripper(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // State enum definitions
    public enum WristState {
        PLACE(0.20),
        GRAB(1.00),
        START(0.30),
        STORE(0.37);

        private final double pos;

        WristState(double pos) { this.pos = pos; }
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
    }
    public void setGripState(GripState gripState) {
        this.gripState = gripState;
    }

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
        wrist.setPosition(wristState.pos);
        grip.setPosition(gripState.pos);
    }
}
