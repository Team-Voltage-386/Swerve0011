package frc.robot.Subsystems.Components;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;

public class SwerveModule {

    public CANSparkMax steerMotor = null;
    public CANSparkMax driveMotor = null;
    public CANCoder enc = null;
    private PIDController steerPID = null;
    private PIDController drivePID = null;
    public final double x;
    public final double y;
    public final double encOffs;

    public double targetSteer = 0;
    public double targetDrive = 0;


    
    /**
     * Create a swerve module
     * @param steerID the CAN ID of the steering motor
     * @param driveID the CAN ID of the drive motor
     * @param encID the CAN ID of the cancoder
     * @param pid {P,I,D} as a double array
     * @param drivePID {P,I,D} as a double array
     * @param X x position of module relative to robot center (right positive)
     * @param Y y position of module relative to robot center (forward positive)
     * @param DRVCONV drive encoder conversion value
     * @param ENCOS absolute encoder offset (for centering modules)
     */
    public SwerveModule(int steerID, int driveID, int encID, double[] steerPIDvalue, double[] drivePIDvalue, double X, double Y, double DRVCONV, double ENCOS) {

        steerMotor = new CANSparkMax(steerID, MotorType.kBrushless); // create motors and set conversion
        driveMotor = new CANSparkMax(driveID, MotorType.kBrushless);
        driveMotor.getEncoder().setPositionConversionFactor(DRVCONV);
        driveMotor.getEncoder().setVelocityConversionFactor(DRVCONV);

        enc = new CANCoder(encID); // this is the steering encoder

        steerPID = new PIDController(steerPIDvalue[0], steerPIDvalue[1], steerPIDvalue[2]); // create PID loops
        drivePID = new PIDController(drivePIDvalue[0], drivePIDvalue[1], drivePIDvalue[2]);

        x = X; // set the module position values and encoder offsets
        y = Y;
        encOffs = ENCOS;
        
    }

    public void update() {

    }


}
