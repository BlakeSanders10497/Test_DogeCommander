package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;
import org.slf4j.helpers.SubstituteLogger;

public class TeleOpTemplateCommand implements Command {

    // Subsystem
    private Subsystem subsystem;

    // Input
    private Gamepad gamepad;

    // Output variables

    // Constructor
    public TeleOpTemplateCommand(Subsystem subsystem, Gamepad gamepad) {
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
