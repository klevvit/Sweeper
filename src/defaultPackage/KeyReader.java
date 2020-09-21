package defaultPackage;

import menuListeners.FlagHelper;
import menuListeners.OpenHelper;
import menuListeners.SolveHelper;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyReader implements KeyListener {


    // true if key is pressed now; false otherwise
    private boolean isAlt;
    private boolean isShift;
    private boolean isF;
    private boolean isO;
    private boolean isS;

    private char lastKey;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keyCheck(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCheck(e.getKeyCode(), false);

    }

    private void keyCheck(int keyCode, boolean val) {
        switch (keyCode) {
            case 18:
                if (isAlt != val) {
                    isAlt = val;
                    // System.out.println("Alt " + (!val?"un":"") +
                    // "pressed");
                    if (val)
                        bindingCheck();
                }
                break;
            case 16:
                if (isShift != val) {
                    isShift = val;
                    // System.out.println("Shift " + (!val?"un":"") +
                    // "pressed");
                    if (val)
                        bindingCheck();
                }
                break;
            case 70:
                if (isF != val) {
                    isF = val;
                    // System.out.println("F " + (!val?"un":"") +
                    // "pressed");
                    if (val) {
                        lastKey = 'f';
                        bindingCheck();
                    }
                }
                break;
            case 79:
                if (isO != val) {
                    isO = val;
                    // System.out.println("O " + (!val?"un":"") +
                    // "pressed");
                    if (val) {
                        lastKey = 'o';
                        bindingCheck();
                    }
                }
                break;
            case 83:
                if (isS != val) {
                    isS = val;
                    // System.out.println("S " + (!val?"un":"") +
                    // "pressed");
                    if (val) {
                        lastKey = 's';
                        bindingCheck();
                    }
                }
                break;
        }
    }

    private void bindingCheck() {
        if (isAlt)
            switch (lastKey) {
                case 'f':
                    if (isF)
                        new FlagHelper().reputFlags();
                    break;
                case 'o':
                    if (isO)
                        new OpenHelper().go();
                    break;
                case 's':
                    if (isS)
                        new SolveHelper().go();
                    break;
            }

    }


}
