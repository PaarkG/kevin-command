package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
    private CANSparkMax leftMotor;
    private RelativeEncoder leftEncoder;
    private SparkPIDController leftController;
    private CANSparkMax rightMotor;
    private RelativeEncoder rightEncoder;
    private SparkPIDController rightController;

    public ClimberSubsystem() {
        leftMotor = new CANSparkMax(ClimberConstants.kLeftID, MotorType.kBrushless);
        leftEncoder = leftMotor.getEncoder();
        leftController = leftMotor.getPIDController();
        leftController.setFeedbackDevice(leftEncoder);
        rightMotor = new CANSparkMax(ClimberConstants.kRightID, MotorType.kBrushless);
        rightEncoder = rightMotor.getEncoder();
        rightController = rightMotor.getPIDController();
        rightController.setFeedbackDevice(rightEncoder);
    }

    private void setPosition(double position) {
        leftController.setReference(position, ControlType.kPosition);
        rightController.setReference(position, ControlType.kPosition);
    }

    public Command raise() {
        return run(() -> setPosition(ClimberConstants.kTopRotations));
    }

    public Command lower() {
        return run(() -> setPosition(ClimberConstants.kBottomRotations));
    }
}