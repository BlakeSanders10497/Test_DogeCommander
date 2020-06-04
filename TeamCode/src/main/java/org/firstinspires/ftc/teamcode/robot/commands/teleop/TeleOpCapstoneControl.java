package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Capstone;

public class TeleOpCapstoneControl implements Command {

    // Subsystem
    private Capstone capstone;

    // Input
    private Gamepad gamepad;

    // Output variables
    private boolean capping;
    private boolean stowing;

    // Constructor
    public TeleOpCapstoneControl(Capstone capstone, Gamepad gamepad) {
        this.capstone = capstone;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() {
        capstone.setState(Capstone.State.STOW);
    }

    // Running state
    @Override
    public void periodic() {

        // Get operator input
        capping = gamepad.dpad_down;
        stowing = gamepad.dpad_up;

        // Provide output
        if(capping) capstone.setState(Capstone.State.CAP);
        if(stowing) capstone.setState(Capstone.State.STOW);
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
