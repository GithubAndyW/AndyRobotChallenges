package com.team2073.robot;

import com.team2073.robot.Commands.HalfPowerCommand;
import com.team2073.robot.Commands.PulseCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperatorInterface {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    public final Joystick controller  = appCTX.getController();

    private final JoystickButton a = new JoystickButton(controller, 1);
    private final JoystickButton lb = new JoystickButton(controller, 5);

    public void init() {
        a.whileHeld(new HalfPowerCommand());
        lb.whileHeld(new PulseCommand());
    }
}
