package com.team2073.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2073.robot.Subsystems.SimpleSubsystem;
import edu.wpi.first.wpilibj.Joystick;


import static com.team2073.robot.AppConstants.Ports.*;


public class ApplicationContext {
    private static ApplicationContext instance;
    private static CANSparkMax motor;
    private static Joystick controller;
    private static SimpleSubsystem simpleSubsystem;


    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public CANSparkMax getMotor() {
        if (motor == null) {
            motor = new CANSparkMax(MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        }
        return motor;
    }

    public static Joystick getController() {
        if (controller == null) {
            controller = new Joystick(JOYSTICK_PORT);
        }
        return controller;
    }


    public SimpleSubsystem getSimpleSubsystem(){
        if(simpleSubsystem == null){
            simpleSubsystem = new SimpleSubsystem();
        }
        return simpleSubsystem;
    }
}
