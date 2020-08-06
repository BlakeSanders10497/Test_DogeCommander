package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TemplateSubsystem implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components

    // State and interface variables

    // Constructor
    public TemplateSubsystem(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // State enum definition
    public enum State {

    }

    // Subsystem control (called by commands)

    // Subsystem initialization ( similar to hardware.init(hardwareMap) )
    @Override
    public void initHardware() {

    }


    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {
    }
}
