package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components
    private DcMotor flDrive;
    private DcMotor frDrive;
    private DcMotor rlDrive;
    private DcMotor rrDrive;

    // State and interface variables
    private double leftPower = 0;
    private double rightPower = 0;

    // Subsystem constructor
    public Drive(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // Subsystem control (called by commands)
    public void setPower(double leftPower, double rightPower) {
        this.leftPower = leftPower;
        this.rightPower = rightPower;
    }

    // Initialization method ( similar to hardware.init(hardwareMap); )
    @Override
    public void initHardware() {

        // Get drive motors
        flDrive = hardwareMap.dcMotor.get("fl_drive");
        frDrive = hardwareMap.dcMotor.get("fr_drive");
        rlDrive = hardwareMap.dcMotor.get("rl_drive");
        rrDrive = hardwareMap.dcMotor.get("rr_drive");

        // Reverse left side
        flDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rlDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set all drive motors to brake on zero power
        flDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rlDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rrDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {
        // Set all drive motor powers from local variables
        flDrive.setPower(leftPower);
        frDrive.setPower(rightPower);
        rlDrive.setPower(leftPower);
        rrDrive.setPower(rightPower);
    }
}
