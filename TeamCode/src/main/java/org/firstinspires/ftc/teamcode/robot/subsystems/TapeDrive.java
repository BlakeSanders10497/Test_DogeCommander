package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TapeDrive implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components
    private DcMotor tape;

    // State and interface variables
    private double tapePower;

    // Constructor
    public TapeDrive(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // Subsystem control (called by commands)
    public void setTapePower(double power) {
        this.tapePower = power;
    }

    // Subsystem initialization ( similar to hardware.init(hardwareMap) )
    @Override
    public void initHardware() {
        tape = hardwareMap.dcMotor.get("tape_measure");
    }


    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {
        tape.setPower(tapePower);
    }
}
