// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Subsystems.SwerveModule;

import static frc.robot.Utils.*;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /** the indexes to address buttons on the controller */
    public static final class ControllerConstants {
        public static final Joystick kDriverController = new Joystick(0);
        public static final Joystick kManipulatorController = new Joystick(1);

        public static final int kLeftVertical = 1;
        public static final int kRightVertical = 5;
        public static final int kLeftHorizontal = 0;
        public static final int kRightHorizontal = 4;
        public static final int kLeftTrigger = 2;
        public static final int kRightTrigger = 3;

        public static final int kA = 1;
        public static final int kB = 2;
        public static final int kX = 3;
        public static final int kY = 4;
        public static final int kLeftBumper = 5;
        public static final int kRightBumper = 6;
        public static final int kLeftOptions = 7;
        public static final int kRightOptions = 8;
        public static final int kLeftJoystickPressed = 9;
        public static final int kRightJoystickPressed = 10;
    }

    /** Can IDs, PID values, ect. */
    public static final class DriveConstants {
        public static final int kIMUid = 2;
        public static final double[] kSwerveSteerPID = {0.5, 0.0, 0.0005};
        public static final double[] kSwerveDrivePID = {1, 0.1, 0.5};
        public static final double kSwerveDriveEncConv = 0.1;
        public static final SwerveModule LFSwerve = new SwerveModule(14, 18, 24, kSwerveSteerPID, kSwerveDrivePID, 0.5, -0.5, kSwerveDriveEncConv, 0.0);
        public static final SwerveModule RFSwerve = new SwerveModule(11, 15, 21, kSwerveSteerPID, kSwerveDrivePID, 0.5, 0.5, kSwerveDriveEncConv, 0.0);
        public static final SwerveModule LRSwerve = new SwerveModule(13, 17, 23, kSwerveSteerPID, kSwerveDrivePID, -0.5, -0.5, kSwerveDriveEncConv, 0.0);
        public static final SwerveModule RRSwerve = new SwerveModule(12, 16, 26, kSwerveSteerPID, kSwerveDrivePID, -0.5, 0.5, kSwerveDriveEncConv, 0.0);
    }
}