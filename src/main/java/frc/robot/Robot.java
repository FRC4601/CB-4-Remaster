//CB-4 "Dodgeball" REMASTER

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

   //Motors
   private final PWMVictorSPX frontleftMotor = new PWMVictorSPX(1);
   private final PWMVictorSPX backleftMotor = new PWMVictorSPX(2);
   private final PWMVictorSPX frontrightMotor = new PWMVictorSPX(3);
   private final PWMVictorSPX backrightMotor = new PWMVictorSPX(4);
   private final PWMVictorSPX shooterMotor = new PWMVictorSPX(5);
   private final PWMVictorSPX intakeMotor = new PWMVictorSPX(6);
   
   

   // Speed Controller Groups
   private final MotorControllerGroup leftSpeedGroup = new MotorControllerGroup(frontleftMotor, backleftMotor);
   private final MotorControllerGroup rightSpeedGroup = new MotorControllerGroup(frontrightMotor, backrightMotor);

   //drivetrain 
   DifferentialDrive drivetrain = new DifferentialDrive(leftSpeedGroup, rightSpeedGroup);

   //Controls
   private final Joystick rightstick = new Joystick(0);
   private final Joystick leftstick = new Joystick(1);
   private final XboxController xbox = new XboxController(2);

   //Pneumatics
   //private final Compressor Kyle = new Compressor(0, PneumaticsModuleType.CTREPCM);
   private final DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);
   

  @Override
  public void robotInit() {

    frontrightMotor.setInverted(true);
    backrightMotor.setInverted(true);
    
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

  //  Kyle.enableDigital();
  //  Kyle.disable();
  }

  @Override
  public void teleopPeriodic() {

  // Tank drive with a given left and right rates
    drivetrain.tankDrive(leftstick.getY(), rightstick.getY());

  // Shooter Motors Control
    if(xbox.getRawButton(6)) { // Shooter Wheel
      shooterMotor.set(0.8);
    }  else if (xbox.getRawButton(5)) {
      shooterMotor.set(-0.3);  
    }
      else {
      shooterMotor.set(0);
    }

  // Intake Motors Control  
    if(xbox.getRawButton(2)) { //Intake Wheel
      intakeMotor.set(0.7);
    } else if (xbox.getRawButton(3)) {
      intakeMotor.set(-0.7); 
    }  else {
      intakeMotor.set(0.0);
    }

  // Pneumatics Control
    if(xbox.getRawButton(7)) { //Piston Extend
      solenoid.set(DoubleSolenoid.Value.kForward);
    } else if(xbox.getRawButton(8)) { //Piston Retract
      solenoid.set(DoubleSolenoid.Value.kReverse);
   }

  }
      
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
