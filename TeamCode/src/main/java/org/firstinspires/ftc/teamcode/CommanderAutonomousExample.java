package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecommander.DogeCommander;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.commands.auto.ArmByEncoder;
import org.firstinspires.ftc.teamcode.robot.commands.auto.DriveByEncoder;
import org.firstinspires.ftc.teamcode.robot.commands.auto.GripperGripStone;
import org.firstinspires.ftc.teamcode.robot.commands.auto.GripperPrepGripStone;
import org.firstinspires.ftc.teamcode.robot.commands.auto.RunIntakeForTime;
import org.firstinspires.ftc.teamcode.robot.subsystems.Arm;
import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;
import org.firstinspires.ftc.teamcode.robot.subsystems.Gripper;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;

@Autonomous(group = "DogeCommander")
public class CommanderAutonomousExample extends LinearOpMode implements DogeOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DogeCommander commander = new DogeCommander(this);

        Drive drive = new Drive(hardwareMap, true);
        Intake intake = new Intake(hardwareMap);
        Arm arm = new Arm(hardwareMap);
        Gripper gripper = new Gripper(hardwareMap);

        commander.registerSubsystem(drive);
        commander.registerSubsystem(intake);
        commander.registerSubsystem(arm);
        commander.registerSubsystem(gripper);
        commander.init();

        waitForStart();

        commander.runCommandsParallel(
                new DriveByEncoder(drive, 3000, 0.0, 0.6, 5.0),
                new RunIntakeForTime(intake, 3.0, Intake.State.INTAKE),
                new ArmByEncoder(arm, -1200, 0.4, 3.0),
                new GripperPrepGripStone(gripper, 1.0)
        );

        commander.runCommand(new ArmByEncoder(arm, 1000, 0.4, 3.0));
        commander.runCommand(new GripperGripStone(gripper, 0.0));
        commander.stop();
    }
}