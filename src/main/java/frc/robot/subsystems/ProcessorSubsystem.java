package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.sensors.BeamBreak;
import frc.robot.Constants.ProcessorConstants;

public class ProcessorSubsystem extends SubsystemBase {
    private CANSparkMax topIntakeMotor, bottomIntakeMotor, indexerMotor;
    private BeamBreak shooterBeamBreak;
    private BeamBreak intakeBeamBreak;

    public ProcessorSubsystem() {
        topIntakeMotor = new CANSparkMax(ProcessorConstants.kTopIntakeID, MotorType.kBrushless);
        bottomIntakeMotor = new CANSparkMax(ProcessorConstants.kBottomIntakeID, MotorType.kBrushless);
        indexerMotor = new CANSparkMax(ProcessorConstants.kIndexerID, MotorType.kBrushless);
        shooterBeamBreak = new BeamBreak(ProcessorConstants.kShooterBeamBreakID);
        intakeBeamBreak = new BeamBreak(ProcessorConstants.kIntakeBeamBreakID);
    }

    private void setTopPercent(double percentage) {
        topIntakeMotor.set(percentage);
    }

    private void setBottomPercent(double percentage) {
        bottomIntakeMotor.set(percentage);
    }

    private void setIndexerMotor(double percentage) {
        indexerMotor.set(percentage);
    }

    public void intake() {
        setTopPercent(ProcessorConstants.kTopIntakePercentage);
        setBottomPercent(ProcessorConstants.kBottomIntakePercentage);
        setIndexerMotor(ProcessorConstants.kIndexerIntakePercentage);
    }

    public void outtake() {
        setTopPercent(ProcessorConstants.kTopOuttakePercentage);
        setBottomPercent(ProcessorConstants.kBottomOuttakePercentage);
        setIndexerMotor(ProcessorConstants.kIndexerOuttakePercentage);
    }

    public void stop() {
        setTopPercent(0);
        setBottomPercent(0);
        setIndexerMotor(0);
    }

    public void feed() {
        setIndexerMotor(ProcessorConstants.kFeedPercentage);
    }

    public void stopIndexer() {
        setIndexerMotor(0);
    }

    public boolean isLoaded() {
        return shooterBeamBreak.isBroken();
    }

    public boolean intakeIsLoaded() {
        return intakeBeamBreak.isBroken();
    }
}
