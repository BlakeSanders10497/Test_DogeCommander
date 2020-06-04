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

        // Provide output
        if(wristStore)  gripper.setWristState(Gripper.WristState.STORE);
        if(wristGrab)   gripper.setWristState(Gripper.WristState.GRAB);

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
