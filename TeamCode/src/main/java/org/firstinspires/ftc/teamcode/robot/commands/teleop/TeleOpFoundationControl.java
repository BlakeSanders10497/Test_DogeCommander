package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.subsystems.Foundation;

public class TeleOpFoundationControl implements Command {

    // Timer (for toggling on a cooldown)
    private ElapsedTime time;

    // Subsystem
    private Foundation foundation;

    // Input
    private Gamepad gamepad;

    // Output variables
    private final double COOLDOWN = 1.00;   // Seconds
    private double lastToggle = -COOLDOWN;  // -COOLDOWN to make it respond on the first button press
    private double timestamp;
    private boolean ready;
    private boolean gripping;

    // Constructor
    public TeleOpFoundationControl(Foundation foundation, Gamepad gamepad) {
        time = new ElapsedTime();

        this.foundation = foundation;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() {
        time.reset();
        foundation.setState(Foundation.State.OPEN);
    }

    // Running state
    @Override
    public void periodic() {

        // Handle cooldown
        timestamp = time.seconds();
        ready = (timestamp - lastToggle > COOLDOWN);

        // Get operator input
        gripping = gamepad.a;

        // Provide output
        if(gripping && ready) {
            lastToggle = timestamp;
            foundation.toggleState();
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
