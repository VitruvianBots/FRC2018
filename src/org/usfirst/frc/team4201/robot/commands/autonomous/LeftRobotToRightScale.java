package org.usfirst.frc.team4201.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4201.robot.commands.*;

public class LeftRobotToRightScale extends CommandGroup{
	//all measurements are in encoder counts.  See RobotMap for conversion rate.
	public LeftRobotToRightScale() {
		 addSequential(new DriveStraightWithGyroAndEncoders(24186, 1));
		 addSequential(new TurnWithGyro(90));  
		 addSequential(new DriveStraightWithGyroAndEncoders(21610, 1));
		 addSequential(new TurnWithGyro(-90));  
		 addSequential(new DriveStraightWithGyroAndEncoders(12412, 1));
		 addSequential(new TurnWithGyro(-90));  
		 addSequential(new DriveStraightWithGyroAndEncoders(673.96, 1));
		 //Drop off Block
	}
}
