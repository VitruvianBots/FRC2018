package org.usfirst.frc.team4201.robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4201.robot.commands.*;

public class AutoRightStartSwitchFocus extends CommandGroup{
	
	public AutoRightStartSwitchFocus() {
		addParallel(new SetArmElevatorSetpoints(-45, 6.55));
		if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
			//addParallel(new AutoSetArmElevatorSetpoints(52, 12));
			addSequential(new PathFinderRead("rightStartToRightSwitch", true));

			addSequential(new AutoSetWristRelativeSetpoint(0));
			addSequential(new SetIntakePistonsOpen());
			addSequential(new Delay(1));
			addSequential(new AutoReleaseWristSetpoint());
			addSequential(new SetIntakePistonsClose());
		} else {
			//addParallel(new AutoSetArmElevatorSetpoints(52, 12));
			addSequential(new PathFinderRead("driveStraight", true));
		}
	}
}