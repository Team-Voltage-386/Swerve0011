package frc.robot.Subsystems;

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
    public final double angleFromCenter;
    public final double distFromCenter;

    public double targetSteer = 0;
    public double targetDrive = 0;

    public int driveMult = 1;


    
    /**
     * Create a swerve module
     * @param steerID the CAN ID of the steering motor
     * @param driveID the CAN ID of the drive motor
     * @param encID the CAN ID of the cancoder
     * @param pid {P,I,D} as a double array
     * @param drivePID {P,I,D} as a double array
     * @param X x position of module relative to robot center (forward positive)
     * @param Y y position of module relative to robot center (left positive)
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

        distFromCenter = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)); // precalc math for later
        angleFromCenter = Math.toDegrees(Math.atan2(y, x));

    }

    public void drive() {
        steerMotor.set(steerPID.calculate(getSwerveHeadingError()));
        driveMotor.set(driveMult * drivePID.calculate(driveMult * driveMotor.getEncoder().getVelocity(), targetDrive));
    }

    private double getSwerveHeadingError() {
        double res = targetSteer - (enc.getAbsolutePosition() - encOffs);
        while (res < -180) res += 360; // bring error around to range from -180 to 180
        while (res > 180) res -= 360;
        if (Math.abs(res) > 90) { // if it is quicker to drive motor backwards, set drive multiplier and adjust error
            driveMult = -1;
            if (res > 0) res -= 180;
            else res += 180;
        } else driveMult = 1;
        return res;
    }
}
