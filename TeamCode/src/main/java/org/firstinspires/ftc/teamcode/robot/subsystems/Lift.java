package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components
    private DcMotor pulleyLeft;
    private DcMotor pulleyRight;

    // State and interface variables
    private double pulleyPower;

    // Constructor
    public Lift(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // Subsystem control (called by commands)
    public void setLiftPower(double power) {
        this.pulleyPower = power;
    }

    // Subsystem initialization ( similar to hardware.init(hardwareMap) )
    @Override
    public void initHardware() {

        // Get pulley motors
        pulleyLeft  = hardwareMap.dcMotor.get("pulley_left");
        pulleyRight = hardwareMap.dcMotor.get("pulley_right");

        // Reverse left side
        pulleyLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set pulley motors to brake on zero power
        pulleyLeft. setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pulleyRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {
        pulleyLeft. setPower(pulleyPower);
        pulleyRight.setPower(pulleyPower);
    }
}
