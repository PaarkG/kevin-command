package frc.robot.commands.processor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PivotSetpoints;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ProcessorSubsystem;

public class RunIntakeCommand extends Command {
    private ProcessorSubsystem processor;
    private PivotSubsystem pivot;

    public RunIntakeCommand(ProcessorSubsystem processor, PivotSubsystem pivot) {
        this.processor = processor;
        this.pivot = pivot;
        addRequirements(processor);
    }

    @Override
    public void initialize() {
        if(!processor.isLoaded()) {
            pivot.setAngle(PivotSetpoints.kIntakeAngle);
        }
    }

    @Override
    public void execute() {
        if(pivot.atAngle()) {
            processor.intake();
        }
    }

    @Override
    public void end(boolean interrupted) {
        processor.stop();
    }

    @Override
    public boolean isFinished() {
        if(processor.isLoaded()) {
            return true;
        }
        return false;
    }
}
