package com.team2073.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.Joystick;
import com.team2073.common.util.*;

public class SimpleSubsystem implements AsyncPeriodicRunnable {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    private final Joystick controller = appCTX.getController();
    private final CANSparkMax motor =  appCTX.getMotor();
    public Timer timer = new Timer();

    private SimpleSubsystemState currentState = SimpleSubsystemState.AXIS;

    private double output = 0;

    public SimpleSubsystem()  { autoRegisterWithPeriodicRunner(); }

    @Override
    public void onPeriodicAsync() {
        output = getAxisOutput();
        switch (currentState) {
            case AXIS:
                output = getAxisOutput();
                break;
            case HALF_POWER:
                output = 0.5;
                break;
            case PULSE:
                timer.start();
                if (timer.hasWaited(1000)) {
                    output = 0.25;
                    if (timer.hasWaited(2000)) {
                        timer.stop();
                    }
                }
                break;

            case STOP:
                output = 0;
                break;
            default:

        }

        if (output < 0.2 && output > -0.2) {
            output = 0;
        } else if (output > 0.8) {
            output = 0.8;
        } else if (output < -0.8) {
            output = -0.8;
        }
        System.out.println(output);
        motor.set(output);
    }

    private double getAxisOutput() {
        return -controller.getRawAxis(1);
    }

    private double getButtonOutput() {
        return controller.getRawAxis(3);
    }
    public void setCurrentState(SimpleSubsystemState currentState) {
        this.currentState = currentState;
    }


    public enum SimpleSubsystemState {
        AXIS,
        PULSE,
        HALF_POWER,
        STOP
    }
}
