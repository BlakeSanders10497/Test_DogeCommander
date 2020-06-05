package org.firstinspires.ftc.teamcode.robot.commands.auto;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.subsystems.Gripper;

public class GripperGripStone implements Command {

    ElapsedTime timer;
    private final double TIMEOUT = 0.5;

    // Subsystem
    private Gripper gripper;

    // Logic variables
    private double waitTime;

    // Constructor
    public GripperGripStone(Gripper gripper, double waitTime) {
        timer = new ElapsedTime();

        this.gripper = gripper;
        this.waitTime = waitTime;
    }

    @Override
    public void start() {
        timer.reset();
    }

    @Override
    public void periodic() {
        if(timer.seconds() > waitTime) {
            gripper.setWristState(Gripper.WristState.GRAB);
            gripper.setGripState(Gripper.GripState.GRIP);
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isCompleted() {
        return timer.seconds() > waitTime + TIMEOUT;
    }
}
