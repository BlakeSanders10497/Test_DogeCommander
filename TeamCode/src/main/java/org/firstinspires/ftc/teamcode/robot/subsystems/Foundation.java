package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Foundation implements Subsystem {

    // Hardware map
    private HardwareMap hardwareMap;

    // Components
    private Servo foundLeft;
    private Servo foundRight;

    // State and interface variables
    private State state = State.OPEN;

    // Constructor
    public Foundation(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // State enum definitions
    public enum State {
        OPEN(1.0, 0.3),
        GRIP(0.3, 1.0);

        private final double leftPos, rightPos;

        State(double leftPos, double rightPos) {
            this.leftPos    = leftPos;
            this.rightPos   = rightPos;
        }
    }

    // Subsystem control (called by commands)
    public void setState(State state) {
        this.state = state;
    }
    public void toggleState() {
        if(state.equals(State.OPEN)) setState(State.GRIP);
        else if(state.equals(State.GRIP)) setState(State.OPEN);
    }

    // Subsystem initialization ( similar to hardware.init(hardwareMap) )
    @Override
    public void initHardware() {

        // Get the foundation servos
        foundLeft = hardwareMap.servo.get("found_left");
        foundRight = hardwareMap.servo.get("found_right");
    }


    // Periodic method (called by DogeCommander)
    @Override
    public void periodic() {
        foundLeft.  setPosition(state.leftPos);
        foundRight. setPosition(state.rightPos);
    }
}
