package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.SparkMaxAlternateEncoder.Type;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PivotConstants;

public class PivotSubsystem extends SubsystemBase {
    private CANSparkMax pivotMotor;
    private RelativeEncoder pivotEncoder;
    private SparkPIDController pivotController;
    private double setpoint;

    public PivotSubsystem() {
        pivotMotor = new CANSparkMax(PivotConstants.kPivotID, MotorType.kBrushless);
        pivotEncoder = pivotMotor.getAlternateEncoder(Type.kQuadrature, 8192);
        pivotEncoder.setPositionConversionFactor(360);
        pivotMotor.setInverted(true);
        pivotEncoder.setInverted(true);
        pivotController = pivotMotor.getPIDController();
        pivotController.setFeedbackDevice(pivotEncoder);
        pivotController.setP(PivotConstants.kP);
        pivotController.setI(PivotConstants.kI);
        pivotController.setD(PivotConstants.kD);
        pivotController.setFF(PivotConstants.kFF);
        pivotController.setIZone(PivotConstants.kIZone);
        pivotController.setOutputRange(-1, 1);
        pivotMotor.setSmartCurrentLimit(PivotConstants.kPivotCurrentLimit);
        pivotMotor.setIdleMode(IdleMode.kBrake);

        pivotController.setSmartMotionMaxVelocity(5700, 0);
        pivotController.setSmartMotionMaxAccel(5700, 0);
        pivotController.setSmartMotionAllowedClosedLoopError(PivotConstants.kTolerance, 0);
    }

    public void setAngle(double angle) {
        angle = MathUtil.clamp(angle, PivotConstants.kMinAngle, PivotConstants.kMaxAngle);
        setpoint = angle - 25.5;
        pivotController.setReference(setpoint, ControlType.kSmartMotion);
        System.out.println(angle);
    }

    public boolean atAngle() {
        if(Math.abs(pivotEncoder.getPosition() - setpoint) <= PivotConstants.kTolerance) {
            return true;
        }
        return false;
    }

    public void setIdleMode(IdleMode idleMode) {
        pivotMotor.setIdleMode(idleMode);
    }
}
