package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Lift;

public class TeleOpLiftControl implements Command {

    // Subsystem
    private Lift lift;

    // Input
    private Gamepad gamepad;

    // Output variables
    private double liftPower;
    private boolean liftInput;
    private boolean resetLift;

    private final double LIFT_INPUT_THRESHOLD = 0.01;

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
        if(gamepad.left_bumper) resetLift = true;
        liftInput = Math.abs(liftPower) > LIFT_INPUT_THRESHOLD;

        // Provide output
        lift.setLiftPower(liftPower);
        if(resetLift && !liftInput) {
            lift.setState(Lift.State.RESET);
        }
        if(!lift.getLiftIsBusy() || liftInput) {
            resetLift = false;
            lift.setState(Lift.State.MANUAL);
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
