// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.processor.PivotAndShootCommand;
import frc.robot.commands.processor.RunIntakeCommand;
import frc.robot.commands.processor.RunOuttakeCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ProcessorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import java.io.File;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final SwerveSubsystem swerve = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
  private final ProcessorSubsystem processor = new ProcessorSubsystem();
  private final PivotSubsystem pivot = new PivotSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();

  private final CommandXboxController driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  public RobotContainer() {
    configureBindings();

    Command driveFieldOriented = swerve.driveCommand(
      () -> -MathUtil.applyDeadband(driverController.getLeftY(), DriveConstants.kDeadband),
      () -> -MathUtil.applyDeadband(driverController.getLeftX(), DriveConstants.kDeadband),
      () -> -MathUtil.applyDeadband(driverController.getRightX(), DriveConstants.kDeadband));

    swerve.setDefaultCommand(driveFieldOriented);
    processor.setDefaultCommand(processor.run(() -> processor.stop()));
    shooter.setDefaultCommand(shooter.run(() -> shooter.stop()));
  }  

  private void configureBindings() {
    driverController.y().onTrue(Commands.runOnce(swerve::zeroGyro, swerve));
    
    operatorController.rightTrigger().whileTrue(new RunIntakeCommand(processor, pivot));
    operatorController.leftTrigger().whileTrue(new RunOuttakeCommand(processor, pivot));

    operatorController.povUp().onTrue(climber.raise());
    operatorController.povDown().onTrue(climber.lower());

    operatorController.a().onTrue(PivotAndShootCommand.getAmpShootCommand(pivot, shooter, processor));
    operatorController.x().onTrue(PivotAndShootCommand.getSubwooferShootCommand(pivot, shooter, processor));
    operatorController.b().onTrue(PivotAndShootCommand.getPodiumShootCommand(pivot, shooter, processor));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
