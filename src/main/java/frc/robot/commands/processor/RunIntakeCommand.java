package frc.robot.commands.processor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ProcessorSubsystem;

public class RunIntakeCommand extends Command {
    private ProcessorSubsystem processor;

    public RunIntakeCommand(ProcessorSubsystem processor) {
        this.processor = processor;
        addRequirements(processor);
    }

    @Override
    public void initialize() {
        if(!processor.isLoaded()) {
            processor.intake();
        }
    }

    @Override
    public void execute() {}

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
