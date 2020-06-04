package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

public class TeleOpArmControl implements Command {
    
    // Subsystem
    private Subsystem subsystem;

    // Input
    private Gamepad gamepad;

    // Output variables

    // Constructor
    public TeleOpArmControl(Subsystem subsystem, Gamepad gamepad) {
        this.subsystem = subsystem;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() {

    }

    // Running state
    @Override
    public void periodic() {

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
