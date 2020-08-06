package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.disnodeteam.dogecommander.DogeCommander;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.subsystems.River;

@TeleOp(name = "LED Controls", group = "Testing")
public class CommanderLEDControls extends LinearOpMode implements DogeOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addLine("Initializing robot");
        telemetry.update();

        DogeCommander commander = new DogeCommander(this);

        River ledRiver = new River(hardwareMap);

        commander.registerSubsystem(ledRiver);
        commander.init();

        telemetry.addLine("Ready");
        telemetry.update();
        waitForStart();
        telemetry.addLine("Running");
        telemetry.update();

        ledRiver.apply();
        Thread.sleep(1000);

        ledRiver.setColor(Color.BLUE).apply();
        Thread.sleep(1000);

        ledRiver.setColor(Color.GREEN).apply();
        Thread.sleep(1000);

        ledRiver.setColor(Color.MAGENTA).apply();
        Thread.sleep(1000);

        telemetry.addLine("Finished");
        telemetry.update();
        Thread.sleep(500);

        while(opModeIsActive()) {
            telemetry.addLine("Looping");
            telemetry.update();


            if(gamepad1.a) ledRiver.setColor(Color.GREEN).apply();
            if(gamepad1.b) ledRiver.setColor(Color.RED).apply();
            if(gamepad1.x) ledRiver.setColor(Color.BLUE).apply();
            if(gamepad1.y) ledRiver.setColor(Color.YELLOW).apply();


        }

    }
}
