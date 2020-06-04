package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;

public class TeleOpDriveControl implements Command {

    // Subsystem
    private Drive drive;

    // Input
    private Gamepad gamepad;

    // Output variables
    private double mecDrive;
    private double mecStrafe;
    private double mecTurn;

    // Constructor
    public TeleOpDriveControl(Drive drive, Gamepad gamepad) {
        this.drive = drive;
        this.gamepad = gamepad;
    }

    // Initial state
    @Override
    public void start() {
        drive.setPower(0, 0);
    }

    // Running state
    @Override
    public void periodic() {

        // Mecanum drive
        mecDrive    = -gamepad.left_stick_y;
        mecStrafe   = gamepad.left_stick_x;
        mecTurn     = -gamepad.right_stick_x;
        drive.setMecanumPower(mecDrive, mecStrafe, mecTurn);
    }

    // End state
    @Override
    public void stop() {
        drive.setPower(0, 0);
    }

    // Progress tracking
    @Override
    public boolean isCompleted() {
        return false;
    }
}
