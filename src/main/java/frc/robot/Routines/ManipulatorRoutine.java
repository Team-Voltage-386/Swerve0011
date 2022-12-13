package frc.robot.Routines;


import static frc.robot.Constants.ControllerConstants.*;

public class ManipulatorRoutine extends Routine {
    public static ManipulatorRoutine inst;
    private boolean climbActive = false;
    private boolean sentUp = false;
    private boolean lastCycleTrigger = false;

    public ManipulatorRoutine() {
        if (inst == null) inst = this;

        this.execOrder = 2; // set execution order
        this.info = "Manipulator Routine: handles manipulator input"; // add title
    }

    @Override
    public void begin() {
        System.out.println("Manipulator Routine Starting");
        running = true;
    }

    @Override
    public void exec() {
    }

    @Override 
    public void end() {
        System.out.println("Manipulator Routine Ending");
    }
}
