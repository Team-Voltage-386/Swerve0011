package frc.robot.Subsystems;

import static frc.robot.Constants.DriveConstants.*;

import com.ctre.phoenix.sensors.Pigeon2;


/** A swervedrive drivetrain */
public class Drivetrain {

        public static double xDriveTarget = 0;
        public static double yDriveTarget = 0;
        public static double angleTarget = 0;

        private static double xPos = 0;
        private static double yPos = 0;

        public static Pigeon2 IMU = new Pigeon2(kIMUid);


        public static void init() {
        }


        public static void update() {

        }

}
