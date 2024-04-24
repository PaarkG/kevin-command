package frc.robot.commands.processor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ProcessorSubsystem;

public class RunOuttakeCommand extends Command {
    private ProcessorSubsystem processor;

    public RunOuttakeCommand(ProcessorSubsystem processor) {
        this.processor = processor;
        addRequirements(processor);
    }

    @Override
    public void initialize() {
        processor.outtake();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        processor.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
