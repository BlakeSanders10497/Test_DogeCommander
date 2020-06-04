package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Gripper;

public class TeleOpGripperControl implements Command {

    // Subsystem
    private Gripper gripper;

    // Input
    private Gamepad gamepad;

    // Output variables
    private boolean wristStore;
    private boolean wristGrab;
    private boolean gripGrip;

    private double wristManual;
    private final double WRIST_SCALAR = 0.000005;
    private final double WRIST_THRESHOLD = 0.05;


    // Constructor
    public TeleOpGripperControl(Gripper gripper, Gamepad gamepad) {
        this.gripper = gripper;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() {
        gripper.setWristState(Gripper.WristState.START);
        gripper.setGripState(Gripper.GripState.OPEN);
    }

    // Running state
    @Override
    public void periodic() {

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

        gripper.setGripState(gripGrip ? Gripper.GripState.GRIP : Gripper.GripState.OPEN);

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
