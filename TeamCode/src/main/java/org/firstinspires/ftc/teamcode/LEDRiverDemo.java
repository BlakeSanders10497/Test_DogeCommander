package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.disnodeteam.dogecommander.DogeCommander;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.lynx.LynxNackException;
import com.qualcomm.hardware.lynx.commands.core.LynxI2cConfigureChannelCommand;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.LEDRiver;
import org.firstinspires.ftc.teamcode.robot.subsystems.River;

@TeleOp(name = "LEDRiver Demo")
public class LEDRiverDemo extends LinearOpMode implements DogeOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DogeCommander commander = new DogeCommander(this);

        River ledRiver = new River(hardwareMap);

        commander.registerSubsystem(ledRiver);
        commander.init();

        waitForStart();

        ledRiver.apply();
        Thread.sleep(1000);

        ledRiver.setColor(Color.BLUE).apply();
        Thread.sleep(1000);

        ledRiver.setColor(Color.GREEN).apply();
        Thread.sleep(1000);

        ledRiver.setColor(Color.MAGENTA).apply();
        Thread.sleep(1000);

        ledRiver.setMode(LEDRiver.Mode.PATTERN).setColor(Color.RED);
        ledRiver.setPattern(LEDRiver.Pattern.STROBE.builder());
        ledRiver.apply();
        Thread.sleep(5000);

        ledRiver.setPattern(LEDRiver.Pattern.HEARTBEAT.builder()).apply();
        Thread.sleep(5000);

        ledRiver.setPattern(LEDRiver.Pattern.BREATHING.builder()).apply();
        Thread.sleep(5000);

        ledRiver.setPattern(LEDRiver.Pattern.RUNNING.builder()).apply();
        Thread.sleep(5000);

        ledRiver.setPattern(LEDRiver.Pattern.BOUNCING.builder()).apply();
        Thread.sleep(5000);

        ledRiver.setPattern(LEDRiver.Pattern.THEATRE_RUNNING.builder()).apply();
        Thread.sleep(5000);

        ledRiver.setPattern(LEDRiver.Pattern.THEATRE_BOUNCING.builder()).apply();
        Thread.sleep(5000);

        ledRiver.setPattern(LEDRiver.Pattern.COLOR_WHEEL.builder()).apply();
        Thread.sleep(5000);

        ledRiver.setHide(true).apply();
        Thread.sleep(2000);

        ledRiver.setHide(false);
        ledRiver.setColorDepth(LEDRiver.ColorDepth.BIT_16);
        ledRiver.setMode(LEDRiver.Mode.INDIVIDUAL);
        long end_time = System.currentTimeMillis() + 5000;
        int shift = 0;
        while(System.currentTimeMillis() < end_time) {
            shift = (shift + 5) % 360;
            for(int i = 0; i < 60; i++) {
                ledRiver.setColor(i, Color.HSVToColor(new float[] {(i*5+shift)%360, 1, 1}));
            }
            ledRiver.apply();
        }

        Thread.sleep(1000);
        ledRiver.reset();
        Thread.sleep(3000);
    }
}