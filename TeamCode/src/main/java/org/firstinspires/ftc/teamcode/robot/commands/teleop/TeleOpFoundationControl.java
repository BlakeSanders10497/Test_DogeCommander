package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Foundation;

public class TeleOpFoundationControl implements Command {

    // Subsystem
    private Foundation foundation;

    // Input
    private Gamepad gamepad;

    // Output variables
    private boolean gripping;

    // Constructor
    public TeleOpFoundationControl(Foundation foundation, Gamepad gamepad) {
        this.foundation = foundation;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() {
        foundation.setState(Foundation.State.OPEN);
    }

    // Running state
    @Override
    public void periodic() {

        // Get operator input
        gripping = gamepad.a;

        // Provide output
        foundation.setState(gripping ? Foundation.State.GRIP : Foundation.State.OPEN);
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
