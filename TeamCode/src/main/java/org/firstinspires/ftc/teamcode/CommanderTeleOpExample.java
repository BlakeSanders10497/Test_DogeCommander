package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecommander.DogeCommander;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpArmControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpCapstoneControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpDriveControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpFoundationControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpGripperControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpIntakeControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpLiftControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpTapeDriveCommand;
import org.firstinspires.ftc.teamcode.robot.subsystems.Arm;
import org.firstinspires.ftc.teamcode.robot.subsystems.Capstone;
import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;
import org.firstinspires.ftc.teamcode.robot.subsystems.Foundation;
import org.firstinspires.ftc.teamcode.robot.subsystems.Gripper;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Lift;
import org.firstinspires.ftc.teamcode.robot.subsystems.TapeDrive;

@TeleOp(group = "DogeCommander")
public class CommanderTeleOpExample extends LinearOpMode implements DogeOpMode {
    @Override
    public void runOpMode() {
        DogeCommander commander = new DogeCommander(this);

        Drive drive     = new Drive (hardwareMap, false);
        Intake intake   = new Intake(hardwareMap);
        Arm arm         = new Arm   (hardwareMap);
        Foundation foundation   = new Foundation(hardwareMap);
        Gripper gripper = new Gripper(hardwareMap);
        Lift lift       = new Lift(hardwareMap);
        TapeDrive tapeDrive     = new TapeDrive(hardwareMap);
        Capstone capstone       = new Capstone(hardwareMap);

        commander.registerSubsystem(drive);
        commander.registerSubsystem(intake);
        commander.registerSubsystem(arm);
        commander.registerSubsystem(foundation);
        commander.registerSubsystem(gripper);
        commander.registerSubsystem(lift);
        commander.registerSubsystem(tapeDrive);
        commander.registerSubsystem(capstone);
        commander.init();

        waitForStart();

        commander.runCommandsParallel(
                new TeleOpDriveControl  (drive,     gamepad1),
                new TeleOpIntakeControl (intake,    gamepad2),
                new TeleOpArmControl    (arm,       gamepad2),
                new TeleOpFoundationControl(foundation, gamepad1),
                new TeleOpGripperControl(gripper, gamepad2),
                new TeleOpLiftControl   (lift, gamepad2),
                new TeleOpTapeDriveCommand  (tapeDrive, gamepad1),
                new TeleOpCapstoneControl   (capstone, gamepad2)
        );

        commander.stop();
    }
}