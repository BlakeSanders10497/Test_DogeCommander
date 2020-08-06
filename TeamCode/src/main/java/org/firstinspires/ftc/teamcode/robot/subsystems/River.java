package org.firstinspires.ftc.teamcode.robot.subsystems;

import android.graphics.Color;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.lynx.LynxNackException;
import com.qualcomm.hardware.lynx.commands.core.LynxI2cConfigureChannelCommand;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.robot.LEDRiver;

public class River implements Subsystem {

    // Hardware Map
    private HardwareMap hardwareMap;

    // Components
    private LEDRiver ledRiver;

    // State and interface variables
    private State state;

    // Constructor
    public River(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // State enum definition
    public enum State {

    }

    // Subsystem control (called by commands)
    public LEDRiver setHide(boolean hidden) {
        return ledRiver.setHide(hidden);
    }
    public LEDRiver setMode(LEDRiver.Mode mode) {
        return ledRiver.setMode(mode);
    }
    public LEDRiver setLEDMode(LEDRiver.LEDMode ledMode) {
        return ledRiver.setLEDMode(ledMode);
    }
    public LEDRiver setColorDepth(LEDRiver.ColorDepth colorDepth) {
        return ledRiver.setColorDepth(colorDepth);
    }
    public LEDRiver setLEDCount(int numLEDs) {
        return ledRiver.setLEDCount(numLEDs);
    }
    public LEDRiver setColor(LEDRiver.Color color) {
        return ledRiver.setColor(color);
    }
    public LEDRiver setColor(int androidColor) {
        return ledRiver.setColor(androidColor);
    }
    public LEDRiver setColor(int index, LEDRiver.Color color) {
        return ledRiver.setColor(index, color);
    }
    public LEDRiver setColor(int index, int androidColor) {
        return ledRiver.setColor(index, androidColor);
    }
    public LEDRiver setPattern(LEDRiver.Pattern pattern) {
        return ledRiver.setPattern(pattern);
    }
    public LEDRiver setBrightness(double dimmer) {
        return ledRiver.setBrightness(dimmer);
    }
    public void apply() {
        ledRiver.apply();
    }
    public void apply(boolean force) {
        ledRiver.apply(force);
    }
    public void reset() {
        ledRiver.reset();
    }
    public void defaults() {
        ledRiver.defaults();
    }
    public void save() {
        ledRiver.save();
    }



    // Subsystem initialization ( similar to hardware.init(hardwareMap) )
    @Override
    public void initHardware() {
        LynxModule revHub = hardwareMap.get(LynxModule.class, "Rev Expansion Hub 2");
        try {
            new LynxI2cConfigureChannelCommand(revHub, 1, LynxI2cConfigureChannelCommand.SpeedCode.FAST_400K).send();
        } catch (LynxNackException | InterruptedException ex) {
            ex.printStackTrace();
        }

        ledRiver = hardwareMap.get(LEDRiver.IMPL, "ledriver");
        ledRiver.setMode(LEDRiver.Mode.SOLID);
        ledRiver.setLEDMode(LEDRiver.LEDMode.RGB);
        ledRiver.setColorDepth(LEDRiver.ColorDepth.BIT_24);
        ledRiver.setColor(0, new LEDRiver.Color(255, 0, 0, 0));
        ledRiver.setColor(1, Color.GREEN);
        ledRiver.setColor(2, Color.BLACK);
    }


    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {
    }
}
