package frc.robot.Routines;

import static frc.robot.Constants.ControllerConstants.*;
import static frc.robot.Constants.DriveConstants.*;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Utils;
import frc.robot.Subsystems.Drivetrain;

public class DriverRoutine extends Routine {

    /** variable to hold instance or "active" object */
    public static DriverRoutine inst;

    private boolean highGear = false;
    private double finalDrive = 0;
    private double finalTurn = 0;
    private double integralTurnAdjust = 0;
    private double lastTurn = 0;


    public DriverRoutine() {
        // instance this code to make sure it only exists once
        if (inst == null) inst = this;

        this.execOrder = 1; // set execution order
        this.info = "Driver Routine: handles driver input"; // give title
    }

    @Override
    public void begin() {
        System.out.println("Driver Routine Starting");
        running = true; // be sure to set running true
    }

    @Override
    public void exec() {

    }

    @Override
    public void end() {
        System.out.println("Driver Routine Ending");
        running = false; // be sure to set runnning false
    }
}
