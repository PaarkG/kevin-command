// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

  public static final class DriveConstants {
    public static final double kMaxSpeed = Units.feetToMeters(15.1);

    public static final double kDeadband = 0.15;

    public static final class AutoConstants {
      public static final PIDConstants kTranslationPID = new PIDConstants(0.7, 0, 0);
      public static final PIDConstants kAnglePID   = new PIDConstants(0.4, 0, 0.01);
    }
  }

  public static final class ProcessorConstants {
    public static final int kTopIntakeID = 31;
    public static final int kBottomIntakeID = 32;
    public static final int kIndexerID = 16;
    public static final int kShooterBeamBreakID = 2;
    public static final int kIntakeBeamBreakID = 7;

    public static final double kTopIntakePercentage = .5;
    public static final double kBottomIntakePercentage = .5;
    public static final double kIndexerIntakePercentage = .3;

    public static final double kTopOuttakePercentage = -.5;
    public static final double kBottomOuttakePercentage = -.5;
    public static final double kIndexerOuttakePercentage = -.3;

    public static final double kFeedPercentage = .5;
  }

  public static final class ShooterConstants {
    public static final int kTopShooterID = 33;
    public static final int kBottomShooterID = 34;
    public static final double kVelocityTolerance = 5.0;
  }

  public static final class ShooterSetpoints {
    public static final double kSubwooferVelocity = 20;
    public static final double kSourceIntakeVelocity = -20;
    public static final double kTopAmpShootVelocity = 4;
    public static final double kBottomAmpShootVelocity = 15;
    public static final double kPodiumVelocity = 45.0;
  }

  public static final class PivotConstants {
    public static final int kPivotID = 11;
    public static final double kEncoderOffset = 0.0;
    public static final double kTolerance = 1.0;
    public static final double kP = 5e-5;
    public static final double kI = 0.0;
    public static final double kD = 0.0;
    public static final double kFF = 0.000156;
    public static final double kIZone = 0.0;
    public static final double kMinAngle = 25.5;
    public static final double kMaxAngle = 130.0;
    public static final int kPivotCurrentLimit = 20;
  }

  public static final class PivotSetpoints {
    public static final double kIdleAngle = 42.0;
    public static final double kIntakeAngle = 42.0;
    public static final double kAmpAngle = 108.0;
    public static final double kSubwooferAngle = 56.0;
    public static final double kPodiumAngle = 45.0;
  }

  public static class ClimberConstants {
    public static final int kLeftID = 12;
    public static final int kRightID = 23;
    public static final double kTopRotations = 115;
    public static final double kBottomRotations = 0;
    public static final int kPortBeamBreakID = 1;
    public static final int kStarBeamBreakID = 5;
  }
}
