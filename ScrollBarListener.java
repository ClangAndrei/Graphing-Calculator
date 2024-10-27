package CompSciFinalProject.src;

import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScrollBarListener implements MouseListener {

    Window win;

    public ScrollBarListener(Window win1) {
        win = win1;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        win.scaler = ((Scrollbar) win.p.getComponent(0)).getValue();
        //win.graph_xSquared(win.text.getText(), Integer.parseInt(win.leftDomainTxt.getText()), Integer.parseInt(win.rightDomainTxt.getText()));
        win.settle();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
