/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.core.ui.swt.verify;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.modelio.ui.UIColor;

/**
 * Sub class of {@link VerifyListener} delegating the validation to an {@link ITextVerifier}.
 * <br/>
 * When the entered text is invalid, makes the given {@link Text} blink with an orange background.
 */
@objid ("3964efa2-1d39-495f-bb20-b6721ae8d6f6")
public class TextBlinkVerifyListener implements VerifyListener {
    @objid ("24f1cb90-1494-4ff9-90e0-16b9c4b3777f")
    public ITextVerifier validator;

    @objid ("7a6e92f2-e230-4f00-ba70-1f6d81b511ec")
    private Text text;

    @objid ("bdb3f443-f3cf-439e-a74e-405dbabd5f5a")
    public TextBlinkVerifyListener(Text text, ITextVerifier validator) {
        this.text = text;
        this.validator = validator;
    }

    @objid ("019772db-91de-4c14-b052-c86a1f9752f7")
    @Override
    public void verifyText(VerifyEvent e) {
        if (!this.validator.isValid(this.text.getText(), e)) {
            // Deny invalid characters
            e.doit = false;
        
            // Make the text blink
            new Blinker(this.text).blink();
        }
    }

    @objid ("85d7b53b-3d74-47a6-8784-ca960ca8b55c")
    private static class Blinker {
        @objid ("ffd9a9a3-faa7-454d-b81f-9f482bab434d")
        private Control control;

        @objid ("ba8777fd-5df9-4651-a277-b2ce4a21ebec")
        public Blinker(Control control) {
            this.control = control;
        }

        /**
         * Make the given control blink once with an orange background.
         */
        @objid ("be273f13-5443-4401-a4e7-dd3bbd7d0fdd")
        public void blink() {
            Thread blinker = new Thread() {
                @Override
                public void run() {
                    try {
                        if (Blinker.this.control.isDisposed()) {
                            return;
                        }
                        Blinker.this.control.getDisplay().asyncExec(new Runnable() {
                            @Override
                            public void run() {
                                Blinker.this.control.setBackground(UIColor.ORANGE);
                            }
                        });
            
                        Thread.sleep(250);
            
                        if (Blinker.this.control.isDisposed()) {
                            return;
                        }
                        Blinker.this.control.getDisplay().asyncExec(new Runnable() {
                            @Override
                            public void run() {
                                Blinker.this.control.setBackground(null);
                            }
                        });
                    } catch (InterruptedException e) {
                        // Ignore interrupt
                    }
                }
            };
            blinker.start();
        }

    }

}
