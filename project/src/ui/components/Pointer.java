package ui.components;

import lejos.nxt.LCD;

/*

  This class is a UI component to manage a single char pointer used to point
  which value is currently selected in menus and different kind of selectors.
  
  The pointer remembers it's position and when it's value changes (X/Y), it 
  automatically removes the old char from LCD and renders a new one in 
  the new position.

*/

public class Pointer {

    private int xPos;
    private int yPos;
    private char style;

    public Pointer(int xPos, int yPos, char style) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.style = style;
    }

    public void setY(int y) {
        LCD.clear(this.xPos, this.yPos, 1);
        this.yPos = y;
        this.render();
    }

    public void setX(int x) {
        LCD.clear(this.xPos, this.yPos, 1);
        this.xPos = x;
        this.render();
    }

    public void render() {
        LCD.drawChar(this.style, this.xPos, this.yPos);
    }

}
