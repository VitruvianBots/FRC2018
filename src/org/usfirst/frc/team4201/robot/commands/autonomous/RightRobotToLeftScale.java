package org.usfirst.frc.team4201.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4201.robot.commands.*;

public class RightRobotToLeftScale extends CommandGroup{
	//all measurements are in encoder counts.  See RobotMap for conversion rate.
	public RightRobotToLeftScale() {
		 addSequential(new DriveStraightWithGyroAndEncoders(5838, 1));
		 addSequential(new DriveTurnWithGyro2(0, -90)); 
		 addSequential(new DriveStraightWithGyroAndEncoders(5216.68, 1));
		 addSequential(new DriveTurnWithGyro2(0, 90));
		 addSequential(new DriveStraightWithGyroAndEncoders(2996, 1));
		 addSequential(new DriveTurnWithGyro2(0, 90));
		 addSequential(new DriveStraightWithGyroAndEncoders(148.68, 1));
		 //Drop off Block
	}
}