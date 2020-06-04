package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Arm;

public class TeleOpArmControl implements Command {

    // Subsystem
    private Arm arm;

    // Input
    private Gamepad gamepad;

    // Output variables
    private double armPower;

    // Constructor
    public TeleOpArmControl(Arm arm, Gamepad gamepad) {
        this.arm = arm;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() {
        arm.setArmPower(0.0);
    }

    // Running state
    @Override
    public void periodic() {

        // Drive arm
        armPower = gamepad.left_stick_y;
        arm.setArmPower(armPower);
    }

    // End state
    @Override
    public void stop() {
        arm.setArmPower(0.0);
    }

    // Progress tracking
    @Override
    public boolean isCompleted() {
        return false;
    }
}
