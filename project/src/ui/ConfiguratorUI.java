package ui;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import util.Configuration;

public class ConfiguratorUI {

	private Configuration config;
	
	public ConfiguratorUI(Configuration config) {
		this.config = config;
	}
	
	public boolean start() {
		this.displayInterface();
		Pointer pointer = new Pointer(0, 1);
		pointer.render();
		int selection = 1;
		
		while(true) {
						
			int press = Button.waitForPress();
			
			if (press == Button.ID_ESCAPE) {
				return false;
			}else if (press == Button.ID_ENTER) {
				LCD.clear();
				
				if (selection == 1) {
					//Left motor selection
					MotorSelector leftMotorSelect = new MotorSelector(this.config.getLeftMotorChar());
					this.config.setLeftMotor(leftMotorSelect.select());
				}else if (selection == 2) {
					//Right motor selection
					MotorSelector rightMotorSelect = new MotorSelector(this.config.getRightMotorChar());
					this.config.setRightMotor(rightMotorSelect.select());
				}else if (selection == 3) {
					//Line color
					LightValueSelector lineColorSelect = new LightValueSelector("Line color");
					this.config.setLineColor(lineColorSelect.select());
				}else if (selection == 4) {
					//BG color
					LightValueSelector bgColorSelect = new LightValueSelector("BG color");
					this.config.setBackgroundColor(bgColorSelect.select());
				}else if (selection == 5) {
					//Move speed
				}else if (selection == 6) {
					//Turn speed
				}else if (selection == 7) {
					//Start robot
					return true;
				}
				
				LCD.clear();
				this.displayInterface();
			}else if (press == Button.ID_LEFT) {
				selection--;
				if (selection < 1) {
					selection = 7;
				}
			}else if (press == Button.ID_RIGHT) {
				selection++;
				if (selection > 7) {
					selection = 1;
				}
			}
			
			pointer.setRow(selection);	
		}
	}
	
	private void displayInterface() {
		//Display texts
		LCD.drawString("Config", 0, 0);
		LCD.drawString("Left motor: ", 1, 1);
		LCD.drawString("Right motor: ", 1, 2);
		LCD.drawString("Line color: ", 1, 3);
		LCD.drawString("BG color: ", 1, 4);
		LCD.drawString("Move speed: ", 1, 5);
		LCD.drawString("Turn speed: ", 1, 6);
		LCD.drawString("START! ", 1, 7);
		
		//Display values
		LCD.drawChar(this.config.getLeftMotorChar(), 14, 1);
		LCD.drawChar(this.config.getRightMotorChar(), 14, 2);
		LCD.drawInt(this.config.getLineColor(), 13, 3);
		LCD.drawInt(this.config.getBackgroundColor(), 13, 4);
		LCD.drawInt(this.config.getMovementSpeed(), 13, 5);
		LCD.drawInt(this.config.getRotatingSpeed(), 13, 6);
	}
	
}