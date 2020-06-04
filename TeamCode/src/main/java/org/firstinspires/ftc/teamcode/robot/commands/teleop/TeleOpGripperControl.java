package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.subsystems.Gripper;

public class TeleOpGripperControl implements Command {

    // Timer
    private ElapsedTime time;

    // Subsystem
    private Gripper gripper;

    // Input
    private Gamepad gamepad;

    // Output variables
    private boolean wristStore;
    private boolean wristGrab;
    private double wristManual;
    private final double WRIST_SCALAR = 0.000005;
    private final double WRIST_THRESHOLD = 0.05;

    private final double COOLDOWN = 1.00;   // Seconds
    private double lastToggle = -COOLDOWN;  // -COOLDOWN to make it respond on first button press
    private double timestamp;
    private boolean ready;
    private boolean gripGrip;


    // Constructor
    public TeleOpGripperControl(Gripper gripper, Gamepad gamepad) {
        time = new ElapsedTime();

        this.gripper = gripper;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() {
        time.reset();

        gripper.setWristState(Gripper.WristState.START);
        gripper.setGripState(Gripper.GripState.OPEN);
    }

    // Running state
    @Override
    public void periodic() {

        // Handle cooldown
        timestamp = time.seconds();
        ready = (timestamp - lastToggle > COOLDOWN);

        // Get operator input
        wristStore  = gamepad.right_bumper;
        wristGrab   = gamepad.x;
        gripGrip    = gamepad.a;

        wristManual = gamepad.right_stick_y;

        // Provide output
        if(Math.abs(wristManual) > WRIST_THRESHOLD) {
            gripper.setWristState(Gripper.WristState.MANUAL);
            gripper.setWristPos(gripper.getWristPos() + (wristManual * WRIST_SCALAR));
        }
        else if(wristStore) gripper.setWristState(Gripper.WristState.STORE);
        else if(wristGrab) gripper.setWristState(Gripper.WristState.GRAB);

        if(gripGrip && ready) {
            lastToggle = timestamp;
            gripper.toggleGripState();
        }


    }

    // End state
    @Override
    public void stop() {

    }

    // Progress tracking
    @Override
    public boolean isCompleted() {
        return false;
    }
}
