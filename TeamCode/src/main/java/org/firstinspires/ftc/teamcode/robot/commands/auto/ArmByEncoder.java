package org.firstinspires.ftc.teamcode.robot.commands.auto;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.subsystems.Arm;

public class ArmByEncoder implements Command {

    // Timer
    ElapsedTime timer;

    // Subsystem
    private Arm arm;

    // Inputs
    private int counts;
    private double power;
    private double timeout;

    // Logic variables
    private final double DEFAULT_TIMEOUT = 5.0;
    private DcMotor.RunMode prevRunMode;

    // Constructors
    public ArmByEncoder(Arm arm, int counts, double power, double timeout) {
        timer = new ElapsedTime();

        this.arm = arm;
        this.counts = counts;
        this.power = power;
        this.timeout = timeout;
    }
    public ArmByEncoder(Arm arm, int counts, double power) {
        timer = new ElapsedTime();

        this.arm = arm;
        this.counts = counts;
        this.power = power;
        this.timeout = DEFAULT_TIMEOUT;
    }

    @Override
    public void start() {
        timer.reset();

        int currentPos = arm.getCurrentPosition();
        prevRunMode = arm.getMode();

        arm.setTarget(currentPos + counts);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(power);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void stop() {
        arm.setMode(prevRunMode);
        arm.setPower(0);
    }

    @Override
    public boolean isCompleted() {
        return (!arm.armIsBusy() || timer.seconds() > timeout); // Drive is done or timeout reached
    }
}
