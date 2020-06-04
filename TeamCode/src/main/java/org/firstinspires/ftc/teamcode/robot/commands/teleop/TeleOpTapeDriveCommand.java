package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.TapeDrive;

public class TeleOpTapeDriveCommand implements Command {

    // Subsystem
    private TapeDrive tapeDrive;

    // Input
    private Gamepad gamepad;

    // Output variables
    private double tapePower;

    // Constructor
    public TeleOpTapeDriveCommand(TapeDrive tapeDrive, Gamepad gamepad) {
        this.tapeDrive = tapeDrive;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() { tapeDrive.setTapePower(0.0); }

    // Running state
    @Override
    public void periodic() {

        // Get operator input
        tapePower = gamepad.right_trigger - gamepad.left_trigger;

        tapeDrive.setTapePower(tapePower);
    }

    // End state
    @Override
    public void stop() { tapeDrive.setTapePower(0.0); }

    // Progress tracking
    @Override
    public boolean isCompleted() {
        return false;
    }
}
