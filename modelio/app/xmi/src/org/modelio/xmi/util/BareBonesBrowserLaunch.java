/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.xmi.util;

import java.lang.reflect.Method;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.modelio.xmi.plugin.Xmi;

@objid ("f6daf6e7-c7d6-4161-ad4b-bc2608d1ed38")
public class BareBonesBrowserLaunch {
    @objid ("39658753-9a9f-4708-93cc-86f3bd21f67a")
    public static void openURL(String url, Shell shell) {
        String osName = System.getProperty("os.name");
        try {
            if (url == null) {
                throw new Exception(Xmi.I18N
                        .getString("warning.helpDocNotFound.voidURL"));
            } else if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec(
                        "rundll32 url.dll,FileProtocolHandler " + url);
            } else if (osName.startsWith("Mac OS")) {
                Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL = fileMgr.getDeclaredMethod("openURL",
                        new Class[] { String.class });
                openURL.invoke(null, new Object[] { url });
            } else { // assume Unix or Linux
                String[] browsers = { "firefox", "opera", "konqueror",
                        "epiphany", "mozilla", "netscape" };
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++) {
                    if (Runtime.getRuntime().exec(
                            new String[] { "which", browsers[count] })
                            .waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                
                if (browser == null)
                    throw new Exception(
                            Xmi.I18N
                                    .getString("warning.helpDocNotFound.browserNotFound"));
                else
                    Runtime.getRuntime().exec(new String[] { browser, url });
            }
        } catch (Exception e) {
            displayErrorMessage(e, shell);
        }
    }

    @objid ("b9e8333e-8c3c-4c8e-bc35-dc5cdccd3017")
    private static void displayErrorMessage(final Exception e, final Shell shell) {
        MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING);
                
        String errorMsg = e.getLocalizedMessage();
        if (errorMsg == null || "".compareTo(errorMsg) == 0) {
            errorMsg = Xmi.I18N
                    .getString("warning.helpDocNotFound.browserLaunchingError");
        }
                
        messageBox.setText(Xmi.I18N.getString("warning.helpDocNotFound.title"));
        messageBox.setMessage(errorMsg);
        messageBox.open();
    }

}
