package frc.robot.commands.processor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PivotSetpoints;
import frc.robot.Constants.ShooterSetpoints;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ProcessorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class PivotAndShootCommand extends Command {
    public static PivotAndShootCommand getAmpShootCommand(PivotSubsystem pivot, ShooterSubsystem shooter, ProcessorSubsystem processor) {
        return new PivotAndShootCommand(pivot, shooter, processor, PivotSetpoints.kAmpAngle, ShooterSetpoints.kTopAmpShootVelocity, ShooterSetpoints.kBottomAmpShootVelocity);
    }

    public static PivotAndShootCommand getSubwooferShootCommand(PivotSubsystem pivot, ShooterSubsystem shooter, ProcessorSubsystem processor) {
        return new PivotAndShootCommand(pivot, shooter, processor, PivotSetpoints.kSubwooferAngle, ShooterSetpoints.kSubwooferVelocity);
    }

    public static PivotAndShootCommand getPodiumShootCommand(PivotSubsystem pivot, ShooterSubsystem shooter, ProcessorSubsystem processor) {
        return new PivotAndShootCommand(pivot, shooter, processor, PivotSetpoints.kPodiumAngle, ShooterSetpoints.kPodiumVelocity);
    }

    private PivotSubsystem pivot;
    private ShooterSubsystem shooter;
    private ProcessorSubsystem processor;
    private double pivotAngle, topShooterVelocity, bottomShooterVelocity;

    private PivotAndShootCommand(PivotSubsystem pivot, ShooterSubsystem shooter, ProcessorSubsystem processor, double pivotAngle, double shooterVelocity) {
        this.pivot = pivot;
        this.shooter = shooter;
        this.processor = processor;
        this.pivotAngle = pivotAngle;
        this.topShooterVelocity = shooterVelocity;
        this.bottomShooterVelocity = shooterVelocity;
        addRequirements(pivot);
        addRequirements(shooter);
        addRequirements(processor);
    }

    private PivotAndShootCommand(PivotSubsystem pivot, ShooterSubsystem shooter, ProcessorSubsystem processor, double pivotAngle, double topShooterVelocity, double bottomShooterVelocity) {
        this.pivot = pivot;
        this.shooter = shooter;
        this.processor = processor;
        this.pivotAngle = pivotAngle;
        this.topShooterVelocity = topShooterVelocity;
        this.bottomShooterVelocity = bottomShooterVelocity;
        addRequirements(pivot);
        addRequirements(shooter);
        addRequirements(processor);
    }

    @Override
    public void initialize() {
        pivot.setAngle(pivotAngle);
        shooter.setDifferentVelocity(topShooterVelocity, bottomShooterVelocity);
    }

    @Override
    public void execute() {
        if(shooter.atSpeed() && pivot.atAngle()) {
            processor.feed();
        }
    }

    @Override
    public void end(boolean interrupted) {
        pivot.setAngle(PivotSetpoints.kIdleAngle);
        shooter.stop();
        processor.stop();
    }

    @Override
    public boolean isFinished() {
        return !processor.isLoaded();
    }
}
