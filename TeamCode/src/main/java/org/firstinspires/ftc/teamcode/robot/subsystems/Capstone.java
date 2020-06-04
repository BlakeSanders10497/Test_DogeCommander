package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Capstone implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components
    private Servo capstone;

    // State and interface variables
    private State state = State.STOW;

    // Constructor
    public Capstone(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // State enum definitions
    public enum State {
        STOW(1.0),
        CAP(0.0);

        private final double pos;

        State(double pos) { this.pos = pos; }
    }

    // Subsystem control (called by commands)
    public void setState(State state) { this.state = state; }

    // Subsystem initialization ( similar to hardware.init(hardwareMap) )
    @Override
    public void initHardware() {

        // Get the capstone servo
        capstone = hardwareMap.servo.get("capstone");
    }


    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {
        capstone.setPosition(state.pos);
    }
}
