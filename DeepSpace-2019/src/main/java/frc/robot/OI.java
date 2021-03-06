/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Commands.ButtonCommandCalibrateCamera;
import frc.robot.Commands.ButtonCommandChangeMastPosition;
import frc.robot.Commands.ButtonCommandEat;
import frc.robot.Commands.ButtonCommandFlipCamera;
import frc.robot.Commands.ButtonCommandSetGear;
import frc.robot.Commands.ButtonCommandSpit;
import frc.robot.Commands.ButtonCommandToggleClamp;
import frc.robot.Commands.ButtonCommandToggleShift;
import frc.robot.Commands.CyborgCommandAutoShift;
import frc.robot.Commands.CyborgCommandFlip;
import frc.robot.Commands.SubmanualCommandAscend;
import frc.robot.Util.Util;
import frc.robot.Util.Xbox;

/**
 * Set up file for the operator interface
 */
public class OI {
    public static final Joystick DRIVER = new Joystick(0);
    public static final Joystick OPERATOR = new Joystick(1);
    
    /** 
     * Assigns what every SmartDash and controller button does 
     */
	public OI() {

        /**
         * Driver
         */
            Button flipCamera = new JoystickButton(DRIVER, Xbox.Y);
                flipCamera.toggleWhenPressed(new ButtonCommandFlipCamera());

            Button updateCameraConfig = new JoystickButton(DRIVER, Xbox.RSTICK);
                updateCameraConfig.whenPressed(new ButtonCommandCalibrateCamera());

            Button toggleShift = new JoystickButton(DRIVER, Xbox.X);
                toggleShift.whenPressed(new ButtonCommandToggleShift());

            Button downShift = new JoystickButton(DRIVER, Xbox.A);
                downShift.whenPressed(new ButtonCommandSetGear(1));

            Button upShift = new JoystickButton(DRIVER, Xbox.B);
                upShift.whenPressed(new ButtonCommandSetGear(2));

            Button toggleAutoShifting = new JoystickButton(DRIVER, Xbox.START);
                toggleAutoShifting.toggleWhenPressed(new CyborgCommandAutoShift());

        /**
         * Operator
         */
            Button ascend = new JoystickButton(OPERATOR, Xbox.RB);
                ascend.toggleWhenPressed(new SubmanualCommandAscend());

            Button eat = new JoystickButton(OPERATOR, Xbox.X);
                eat.whenPressed(new ButtonCommandEat());

            Button spit = new JoystickButton(OPERATOR, Xbox.A);
                spit.whenPressed(new ButtonCommandSpit());

            Button flipArm = new JoystickButton(OPERATOR, Xbox.Y);
                flipArm.toggleWhenPressed(new CyborgCommandFlip());

            Button toggleClamp = new JoystickButton(OPERATOR, Xbox.B);
                toggleClamp.toggleWhenPressed(new ButtonCommandToggleClamp());

            Button mastUp = new JoystickButton(OPERATOR, Xbox.START);
                mastUp.toggleWhenPressed(new ButtonCommandChangeMastPosition(1));

            Button mastDown = new JoystickButton(OPERATOR, Xbox.BACK);
                mastDown.toggleWhenPressed(new ButtonCommandChangeMastPosition(-1));
    }
}
