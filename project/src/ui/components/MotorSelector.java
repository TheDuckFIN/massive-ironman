package ui.components;

import lejos.nxt.Button;
import lejos.nxt.LCD;

/*

  This is a UI component used to select between motors A, B and C. Used in
  general configuration UI to select left and right motors.

*/

public class MotorSelector {

    private Pointer pointer;
    private int selection;

    public MotorSelector(char current) {
        this.pointer = new Pointer(6, (int)current - 62, '>'); //A=65, 65-62=3. B -> 4, C -> 5 etc.
        this.selection = (int)current - 62;
    }

    public char select() {
        this.render();
        this.pointer.render();

        while (true) {
            int key = Button.waitForPress();

            if (key == Button.ID_ENTER) {
                //Because selection A is 3, 3+62 = 65 which is ascii value for character A... B is 4 and so on...
                return (char)(this.selection + 62);
            }else if (key == Button.ID_LEFT) {
                this.selection--;
                if (this.selection < 3) this.selection = 5;
            }else if (key == Button.ID_RIGHT) {
                this.selection++;
                if (this.selection > 5) this.selection = 3;
            }

            this.pointer.setY(this.selection);
        }
    }

    public void render() {
        LCD.drawString("Select motor:", 2, 1);
        LCD.drawChar('A', 7, 3);
        LCD.drawChar('B', 7, 4);
        LCD.drawChar('C', 7, 5);
    }

}
