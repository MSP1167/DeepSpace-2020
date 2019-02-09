/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Commands.ManualCommandTestMast;
import frc.robot.Enumeration.MastPosition;
import frc.robot.Util.Xbox;

/**
 * Two-stage system that moves the maniuplator vertically
 */
public class SubsystemMast extends Subsystem {

  private static MastPosition storedPosition;

  private static TalonSRX firstStage;
  private static TalonSRX secondStage;

  private static Boolean loopRunning;

  @Override
  public void initDefaultCommand() {}

  public SubsystemMast() {
    storedPosition = MastPosition.HATCH_1;

    firstStage  = new TalonSRX(Constants.FIRST_STAGE_ID);
    secondStage = new TalonSRX(Constants.SECOND_STAGE_ID);

    initConfig(50, 0, true);
  }

  public void setStoredPosition(MastPosition position) {
    storedPosition = position;
    loopRunning    = false;
  }

  public MastPosition getStoredPosition() {
    return storedPosition;
  }

  public Boolean getLoopRunning() {
    return loopRunning;
  }

  public void moveFirstStageByPercent(double speed) {
    firstStage.set(ControlMode.PercentOutput, speed);
  }

  public void moveFirstStageByPosition(double inches) {

  }

  public void moveSecondStageByPercent(double speed) {
    secondStage.set(ControlMode.PercentOutput, speed);
  }

  public void moveSecondStageByPosition(double inches) {

  }

  public void moveWithJoystick(Joystick joy, double firstStageInhibitor, double secondStageInhibitor) {
    firstStage.set(ControlMode.PercentOutput, Xbox.LEFT_Y(joy) * Math.abs(firstStageInhibitor));
    secondStage.set(ControlMode.PercentOutput, Xbox.RIGHT_Y(joy) * Math.abs(secondStageInhibitor));
  }

  public Boolean[] getLimitSwitches() {
    Boolean[] array = new Boolean[4];
    // array[0] = firstStageLow;
    // array[1] = firstStageHigh;
    // array[2] = SecondStageLow;
    // array[3] = SecondStageHigh;
    return array;
  }

  public void initConfig(int ampLimit, double ramp, Boolean braking) {
    firstStage.setInverted(Constants.FIRST_STAGE_INVERT);
      firstStage.configOpenloopRamp(ramp);
      firstStage.configContinuousCurrentLimit(ampLimit);
      firstStage.setNeutralMode(braking ? NeutralMode.Brake : NeutralMode.Coast);;
    secondStage.setInverted(Constants.SECOND_STAGE_INVERT);
      secondStage.configOpenloopRamp(ramp);
      secondStage.configContinuousCurrentLimit(ampLimit);
      secondStage.setNeutralMode(braking ? NeutralMode.Brake : NeutralMode.Coast);;
  }
  public double[] getAmperage() {
    return new double[]{firstStage.getOutputCurrent(), secondStage.getOutputCurrent()};
  }

  public void zeroEncoders() {
    //TODO add code to zero them
  }

}
