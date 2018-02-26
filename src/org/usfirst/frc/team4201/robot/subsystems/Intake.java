package org.usfirst.frc.team4201.robot.subsystems;

import java.util.concurrent.locks.ReentrantLock;

import org.usfirst.frc.team4201.robot.RobotMap;
import org.usfirst.frc.team4201.robot.commands.RetractWristOnContact;
import org.usfirst.frc.team4201.robot.interfaces.Shuffleboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem {
	
	public WPI_TalonSRX[] intakeMotors = {
		new WPI_TalonSRX(RobotMap.intakeLeft),	// To maintain consistency, left should always go before right
		new WPI_TalonSRX(RobotMap.intakeRight)
	};
	
	DoubleSolenoid intakePistons = new DoubleSolenoid(RobotMap.PCMOne, RobotMap.intakeForward, RobotMap.intakeReverse);
	DigitalInput bumpSwitch = new DigitalInput(0);
	
	public static boolean isCubePresent = false;
	public static ReentrantLock m_cubeLock = new ReentrantLock();
	
	public Intake() {
		super("Intake");
		
		// Set right motor to follow left motor
		
		// Set Motor Controller Feedback Device
		
		// Set Motor Controller Peak Output Voltages & Set Motors to Coast
		for(int i = 0; i < intakeMotors.length; i++){	// Changed to intakeMotors.length so it adjusts to array length
			//intakeMotors[i].configPeakOutputForward(1, 0);
			//intakeMotors[i].configPeakOutputReverse(-1, 0);
			intakeMotors[i].setNeutralMode(NeutralMode.Brake);	// Brake is probably preferred for this game, due to the 1 cube control limit
		}
		intakeMotors[1].set(ControlMode.Follower, intakeMotors[0].getDeviceID());
		intakeMotors[0].setInverted(true);
		
		intakePistons.setName("Pistons");
		intakePistons.setSubsystem("Intake");
        LiveWindow.add(intakePistons);
        
        intakeMotors[0].setName("Intake Left Motor");
        intakeMotors[0].setSubsystem("Intake");
        LiveWindow.add(intakeMotors[0]);
        
        intakeMotors[1].setName("Intake Right Motor");
        intakeMotors[1].setSubsystem("Intake");
        LiveWindow.add(intakeMotors[1]);
	}
	
	public void setIntakeMotorOutput(double intakeSpeed){
		intakeMotors[0].set(ControlMode.PercentOutput, intakeSpeed);
	}
	
	public boolean getIntakePistonStatus() {
		return intakePistons.get() == Value.kForward ? true : false;
	}
	
	public void extendIntakePistons() {
		intakePistons.set(Value.kForward);
	}
	
	public void retractIntakePistons() {
		intakePistons.set(Value.kReverse);
	}
	
	public void setMotorsToBrake(){
		for(int i = 0; i < intakeMotors.length; i++)
			intakeMotors[i].setNeutralMode(NeutralMode.Brake);
	}
	
	public void setMotorsToCoast(){
		for(int i = 0; i < intakeMotors.length; i++)
			intakeMotors[i].setNeutralMode(NeutralMode.Coast);
	}
	
	public void updateSmartDashboard(){
		// Use Shuffleboard to place things in their own tabs
		Shuffleboard.putBoolean("Intake", "Pistons", getIntakePistonStatus());
		Shuffleboard.putNumber("Intake", "Speed", intakeMotors[0].get());
		
		// Use SmartDashboard to put only the important stuff for drivers;
		SmartDashboard.putBoolean("Intake Pistons", getIntakePistonStatus());
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//setDefaultCommand(new RetractWristOnContact());
	}
}
