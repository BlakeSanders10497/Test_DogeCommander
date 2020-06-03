package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;

public class TeleOpDriveControl implements Command {

    private Drive drive;
    private Gamepad gamepad;

    // Mecanum motor power variables
    private double mecDrive;
    private double mecStrafe;
    private double mecTurn;

    public TeleOpDriveControl(Drive drive, Gamepad gamepad) {
        this.drive = drive;
        this.gamepad = gamepad;
    }

    @Override
    public void start() {
        drive.setPower(0, 0);
    }

    @Override
    public void periodic() {

        mecDrive    = -gamepad.left_stick_y;
        mecStrafe   = gamepad.left_stick_x;
        mecTurn     = -gamepad.right_stick_x;

        drive.setMecanumPower(mecDrive, mecStrafe, mecTurn);
    }

    @Override
    public void stop() {
        drive.setPower(0, 0);
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
