/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.module.modelermodule.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

@objid ("b8ee5214-7b7e-4a32-b6b3-8faaa201050e")
public class ShellHelper {
    @objid ("e9202484-48cc-45bc-837b-c25e5dbe7c75")
    public static Shell findActiveShell() {
        if (System.getProperty("os.name").equals("Linux")) {
            return null;
        } else {
            Display display = Display.getCurrent();
            if (display == null) {
                display = Display.getDefault();
            }
            return display.getActiveShell();
        }
    }

    @objid ("767e9fcb-906e-4c7d-9425-f8a45b2a115c")
    public static void centerShell(Shell shell) {
        Composite parentShell = shell.getParent();
        Rectangle parentBounds = parentShell.getBounds();
        Rectangle shellBounds = shell.getBounds();
        int x = parentBounds.x + (parentBounds.width - shellBounds.width) / 2;
        int y = parentBounds.y + (parentBounds.height - shellBounds.height) / 2;
        shell.setLocation(x, y);
    }

}
