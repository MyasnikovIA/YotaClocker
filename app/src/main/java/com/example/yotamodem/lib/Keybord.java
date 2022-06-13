package com.example.yotamodem.lib;

import android.app.Instrumentation;
import android.util.Log;

import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.KeyEvent.KEYCODE_TAB;

public class Keybord {

    private Instrumentation inst;

    public Keybord() {
        inst = new Instrumentation();
    }

    ;

    public void press(final int KeyCode) {
        new Thread() {
            @Override
            public void run() {
                try {
                    inst.sendKeyDownUpSync(KeyCode);
                } catch (Exception e) {
                    Log.e("Keybord", e.toString());
                }
            }
        }.start();
    }

    public void send(final String sendText) {
        new Thread() {
            @Override
            public void run() {
                try {
                    for (char ch : sendText.toCharArray()) {
                        inst.sendKeyDownUpSync(ch);
                    }
                } catch (Exception e) {
                    Log.e("Keybord", e.toString());
                }
            }
        }.start();
    }

    public static void text(final String sendText) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    for (char ch : sendText.toCharArray()) {
                        inst.sendKeyDownUpSync(ch);
                    }
                } catch (Exception e) {
                    Log.e("Keybord", e.toString());
                }
            }
        }.start();
    }

    /**
     * @param KeyCode public void onClickTab(View v) {
     *                Keybord.Key(61);
     *                }
     */
    public static void Key(final int KeyCode) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyCode);
                } catch (Exception e) {
                    Log.e("Keybord", e.toString());
                }
            }
        }.start();
    }
}
