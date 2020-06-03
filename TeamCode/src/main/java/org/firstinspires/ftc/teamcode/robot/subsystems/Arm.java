package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.hardware.lynx.commands.core.LynxReadVersionStringResponse;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components
    private DcMotor arm;

    // State and interface variables
    private double armPower;

    // Constructor
    public Arm(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // Subsystem control (called by commands)
    public void setArmPower(double power) {
        this.armPower = power;
    }

    // Subsystem initialization ( similar to hardware.init(hardwareMap) )
    @Override
    public void initHardware() {

        // Get the arm motor
        arm = hardwareMap.dcMotor.get("arm");

        // Reverse arm motor
        arm.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set arm motor to brake on zero power
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {
        arm.setPower(armPower);
    }
}
