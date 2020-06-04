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
    private State state = State.MANUAL;
    private double pulleyPower;

    // Constructor
    public Lift(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // State enum definition
    public enum State {
        MANUAL  (DcMotor.RunMode.RUN_WITHOUT_ENCODER),
        RESET   (DcMotor.RunMode.RUN_TO_POSITION);

        private final DcMotor.RunMode runMode;

        State(DcMotor.RunMode runMode) { this.runMode = runMode; }
    }

    // Subsystem control (called by commands)
    public void setState(State state) { this.state = state; }
    public void setLiftPower(double power) {
        this.pulleyPower = power;
    }

    // Getters
    public boolean getLiftIsBusy() { return pulleyLeft.isBusy() && pulleyRight.isBusy(); }

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

        pulleyLeft. setTargetPosition(0);
        pulleyRight.setTargetPosition(0);

    }


    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {

        pulleyLeft.setMode  (state.runMode);
        pulleyRight.setMode (state.runMode);

        if(state.equals(State.MANUAL)) {
            pulleyLeft. setPower(pulleyPower);
            pulleyRight.setPower(pulleyPower);
        } else if(state.equals(State.RESET)) {
            pulleyLeft. setPower(1.0);
            pulleyRight.setPower(1.0);
        }
    }
}
