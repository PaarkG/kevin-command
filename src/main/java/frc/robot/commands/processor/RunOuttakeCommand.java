package frc.robot.commands.processor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PivotSetpoints;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ProcessorSubsystem;

public class RunOuttakeCommand extends Command {
    private ProcessorSubsystem processor;
    private PivotSubsystem pivot;

    public RunOuttakeCommand(ProcessorSubsystem processor, PivotSubsystem pivot) {
        this.processor = processor;
        this.pivot = pivot;
        addRequirements(processor);
    }

    @Override
    public void initialize() {
        pivot.setAngle(PivotSetpoints.kIntakeAngle);
    }

    @Override
    public void execute() {
        if(pivot.atAngle()) {
            processor.outtake();
        }
    }

    @Override
    public void end(boolean interrupted) {
        processor.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
