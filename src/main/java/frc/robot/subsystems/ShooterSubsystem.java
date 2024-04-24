package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
    private TalonFX topShooterMotor, bottomShooterMotor;
    private double topVelocitySetpoint, bottomVelocitySetpoint;
    
    public ShooterSubsystem() {
        topShooterMotor = new TalonFX(ShooterConstants.kTopShooterID);
        bottomShooterMotor = new TalonFX(ShooterConstants.kBottomShooterID);
    }

    /** @param percentage
     * -1.0 to 1.0
     */
    private void setTopPercent(double percentage) {
        topShooterMotor.set(percentage);
    }

    /** @param percentage
     * -1.0 to 1.0
     */
    private void setBottomPercent(double percentage) {
        bottomShooterMotor.set(percentage);
    }

    /** @param percentage
     * -1.0 to 1.0
     */
    private void setSamePercent(double percentage) {
        setTopPercent(percentage);
        setBottomPercent(percentage);
    }

    /** @param topPercentage
     * -1.0 to 1.0
     *  @param bottomPercentage
     * -1.0 to 1.0
     */
    // private void setDifferentPercent(double topPercentage, double bottomPercentage) {
    //     setTopPercent(topPercentage);
    //     setBottomPercent(bottomPercentage);
    // }

    /** @param velocity
     * rotations per second
     */
    private void setTopVelocity(double velocity) {
        topVelocitySetpoint = velocity;
        topShooterMotor.setControl(new VelocityDutyCycle(velocity));
    }

    /** @param velocity
     * rotations per second
     */
    private void setBottomVelocity(double velocity) {
        bottomVelocitySetpoint = velocity;
        bottomShooterMotor.setControl(new VelocityDutyCycle(velocity));
    }

    /** Sets the velocity of both shooter wheels to the same speed
     *  @param velocity
     * rotations per second
     */
    public void setSameVelocity(double velocity) {
        setTopVelocity(velocity);
        setBottomVelocity(velocity);
    }

    /** Sets the velocity of the shooter wheels to separate speeds
     *  @param topVelocity
     * rotations per second
     *  @param bottomVelocity
     * rotations per second
     */
    public void setDifferentVelocity(double topVelocity, double bottomVelocity) {
        setTopVelocity(topVelocity);
        setBottomVelocity(bottomVelocity);
    }

    public void stop() {
        setSamePercent(0);
    }

    // public void setSubwooferVelocity() {
    //     setSameVelocity(ShooterSetpoints.kSubwooferVelocity);
    // }

    // public void setSourceIntakeVelocity() {
    //     setSameVelocity(ShooterSetpoints.kSourceIntakeVelocity);
    // }

    // public void setAmpShootVelocity() {
    //     setDifferentVelocity(ShooterSetpoints.kTopAmpShootVelocity, ShooterSetpoints.kBottomAmpShootVelocity);
    // }

    public boolean atSpeed() {
        return Math.abs(topShooterMotor.getVelocity().getValueAsDouble()  - topVelocitySetpoint) >= ShooterConstants.kVelocityTolerance 
        && Math.abs(bottomShooterMotor.getVelocity().getValueAsDouble() - bottomVelocitySetpoint) >= ShooterConstants.kVelocityTolerance;
    }
}
