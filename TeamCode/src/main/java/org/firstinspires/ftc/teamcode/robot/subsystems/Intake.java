package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components
    private CRServo intakeLeft;
    private CRServo intakeRight;

    // State and interface variables
    private State state = State.STOP;

    // Constructor
    public Intake(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // State definition
    public enum State {
        INTAKE(1.0),
        SPIT_OUT(-1.0),
        STOP(0.0);

        private final double power;

        State(double power) {
            this.power = power;
        }

    }

    // Subsystem control (called by commands)
    public void setState(State state) {
        this.state = state;
    }

    // Subsystem initialization ( similar to hardware.init(hardwareMap) )
    @Override
    public void initHardware() {

        // Get CR servos
        intakeLeft  = hardwareMap.crservo.get("intake_left");
        intakeRight = hardwareMap.crservo.get("intake_right");

        // Reverse left side
        intakeLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void periodic() {
        intakeLeft. setPower(state.power);
        intakeRight.setPower(state.power);
    }
}
