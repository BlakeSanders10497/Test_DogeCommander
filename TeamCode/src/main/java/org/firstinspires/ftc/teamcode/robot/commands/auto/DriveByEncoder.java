package org.firstinspires.ftc.teamcode.robot.commands.auto;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;

public class DriveByEncoder implements Command {

    // Timer
    ElapsedTime timer;

    // Subsystem
    private Drive drive;

    // Inputs
    private int counts;
    private double power;
    private double timeout;

    // Logic variables
    private final double DEFAULT_TIMEOUT = 5.0;
    private DcMotor.RunMode prevRunMode;

    // Constructors
    public DriveByEncoder(Drive drive, int counts, double power, double timeout) {
        timer = new ElapsedTime();

        this.drive = drive;
        this.counts = counts;
        this.power = power;
        this.timeout = timeout;
    }
    public DriveByEncoder(Drive drive, int counts, double power) {
        timer = new ElapsedTime();

        this.drive = drive;
        this.counts = counts;
        this.power = power;
        this.timeout = DEFAULT_TIMEOUT;
    }

    @Override
    public void start() {
        timer.reset();

        int[] currentPos = drive.getCurrentPositions();
        prevRunMode = drive.getMode();

        drive.setTargets(
                currentPos[0] + counts,
                currentPos[1] + counts,
                currentPos[2] + counts,
                currentPos[3] + counts
        );
        drive.setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        drive.setPower(power, power);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void stop() {
        drive.setRunMode(prevRunMode);
        drive.setPower(0, 0);
    }

    @Override
    public boolean isCompleted() {
        return (!drive.driveIsBusy() || timer.seconds() > timeout); // Drive is done or timeout reached
    }

}
