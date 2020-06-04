package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Lift;

public class TeleOpLiftControl implements Command {

    // Subsystem
    private Lift lift;

    // Input
    private Gamepad gamepad;

    // Output variables
    private double liftPower;

    // Constructor
    public TeleOpLiftControl(Lift lift, Gamepad gamepad) {
        this.lift = lift;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() {
        lift.setLiftPower(0.0);
    }

    // Running state
    @Override
    public void periodic() {

        // Get operator input
        liftPower = (gamepad.left_trigger - gamepad.right_trigger);

        // Provide output
        lift.setLiftPower(liftPower);

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
