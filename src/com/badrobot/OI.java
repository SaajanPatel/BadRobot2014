
package com.badrobot;

import com.badrobot.commands.AutoGather;
import com.badrobot.commands.DriveStraightForward;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    public static DriverStation driverStation;
    
    public static boolean CONSOLE_OUTPUT_ENABLED = true;
    
    public static XboxController primaryController, secondaryController;
    
    //Buttons:
    public Button driveStraight = new Button() 
    {
        public boolean get() 
        {
            return(primaryController.isRBButtonPressed());//RB Button drives forward
        }
    };
    /*
    public Button autoGather = new Button()
    {
        public boolean get()
        {
            if (!isSingleControllerMode())
            {
                return secondaryController.isStartButtonPressed();
            }
            else
            {
                return primaryController.isStartButtonPressed();
            }
        }
    };
    */
    public void init()
    {
        driverStation = DriverStation.getInstance();
        primaryController = new XboxController(RobotMap.driverStation_ControllerPort1);
        secondaryController = new XboxController(RobotMap.driverStation_ControllerPort2);
        
        if (!isSingleControllerMode())
        {
            driveStraight.whenPressed(new DriveStraightForward());
        }
        
        //autoGather.whenPressed(new AutoGather());
    }
    
    public static boolean getDigitalInput(int channel){
        return driverStation.getDigitalIn(channel);
    }
    
    public static boolean isSingleControllerMode()
    {
        return driverStation.getDigitalIn(1);
    }
}

