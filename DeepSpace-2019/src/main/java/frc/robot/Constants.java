/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * A place to store magic numbers
 */
public class Constants {
    
    /**
     * Drive values
     */
    public static final double
        BACKUP_RAMP         = 0,
        MAX_ALLOWABLE_AO    = .2, // percent output
        MAX_ALLOWABLE_ERROR = .25, // inches
        DOWNSHIFT_RPM       = 900,
        UPSHIFT_RPM         = 1000;

    /**
     * Per values
     */
    public static final double
        ENCODER_TICKS_PER_ROTATION = 42,
        ROTATIONS_PER_INCH = 1 / (4.67 * 6 *  Math.PI); // calculated with a cimple box and 42 TPR

    /**
     * PID backup values
     */
    public static final double
        BACKUP_POSITION_kP = 0,
        BACKUP_POSITION_kI = 0,
        BACKUP_POSITION_kD = 0;

    /**
     * Inverts
     */
    public static final Boolean
        LEFT_DRIVE_INVERT  = false,
        RIGHT_DRIVE_INVERT = true;

    /**
     * Safety Values
     */
    public static final int
        DANGER_AMPERAGE = 55;
        
    /**
     * Solenoid IDS
     */
    public static final int
        DOWNSHIFT_ID  = 7,
        UPSHIFT_ID = 6;
        
    /**
     * Spark IDs
     */
    public static final int
        LEFT_MASTER_ID  = 0,
        LEFT_SLAVE_ID   = 1,
        RIGHT_MASTER_ID = 2,
        RIGHT_SLAVE_ID  = 3;

    /**
     * Talon IDS
     */
    public static final int
        CLIMBER_ID = 4;

    /**
     * Vision Values
     */
    public static final int
        CAM_HEIGHT      = 1080,
        CAM_WIDTH       = 1920,
        ACAM_ID         = 0,
        BCAM_ID         = 1,
        CCAM_ID         = 2,
        BACKUP_EXPOSURE = 35;
}
