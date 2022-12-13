package frc.robot.Subsystems;

import static frc.robot.Constants.DriveConstants.*;

import com.ctre.phoenix.sensors.Pigeon2;


/** A swervedrive drivetrain */
public class Drivetrain {

        public static double xDriveTarget = 0;
        public static double yDriveTarget = 0;
        public static double rotationTarget = 0;

        private static double xPos = 0;
        private static double yPos = 0;
        private static double angle = 0;

        private static double ypr[] = new double[3];

        public static Pigeon2 IMU = new Pigeon2(kIMUid);

        private static SwerveModule[] modules = {RFSwerve, RRSwerve, LRSwerve, LFSwerve};


        public static void init() { // zero everything out for safe start
                xDriveTarget = 0;
                yDriveTarget = 0;
                IMU.getYawPitchRoll(ypr);
                rotationTarget = getRawHeading();

                
        }


        public static void update() {
                updateOdometry();



                for (SwerveModule swerve : modules) {
                        double angleRad = Math.toRadians(angle); // get angle in radians

                        // fist add translation
                        double x = xDriveTarget;
                        double y = yDriveTarget;
                        
                        // then add rotation
                        double r = ((2*Math.PI*swerve.distFromCenter)/360)*rotationTarget; // rotation speed (should be m/s of module I think)
                        double rAngle = swerve.angleFromCenter + angle + 90;
                        x += r*Math.cos(Math.toRadians(rAngle));
                        y += r*Math.sin(Math.toRadians(rAngle));

                        // apply vectors to the rotation of the robot (making it field-oriented)
                        double xFin = (xDriveTarget * Math.cos(angleRad)) + (yDriveTarget * Math.sin(angleRad)); // calculate x and y for 
                        double yFin = (xDriveTarget * Math.cos(angleRad + (Math.PI/2))) + (yDriveTarget * Math.sin(angleRad + (Math.PI/2)));

                        //extract angle and power
                        swerve.targetSteer = Math.toDegrees(Math.atan2(yFin, xFin));
                        swerve.targetDrive = Math.sqrt(Math.pow(xFin, 2) + Math.pow(yFin, 2));

                        swerve.drive();
                }
        }

        public static double getRawHeading() {
                double y = ypr[0];
                while (y < 0) y += 360;
                while (y > 360) y -= 360;
                return y;
        }

        private static void updateOdometry() {
                IMU.getYawPitchRoll(ypr);
                angle = getRawHeading();
        }

}
