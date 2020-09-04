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

package org.modelio.app.ui.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;

/**
 * SWT global exception handler setup.
 * <p>
 * Set our global exception handler on a display.
 * @since 4.0
 */
@objid ("9ece8b56-1a0c-4dd2-a58a-8d88225cab4f")
public class DefaultSWTExceptionHandler {
    /**
     * Set our global exception handler on a display.
     * 
     * @param display the display to setup.
     */
    @objid ("1f2858ed-83fc-43ea-b86a-360579756aa5")
    public static void setup(Display display) {
        display.setErrorHandler(e -> handle(e));
        display.setRuntimeExceptionHandler(e -> handle(e));
    }

    @objid ("aac5424a-b9ee-43e6-8c0b-48af2b788b97")
    private static <E extends Throwable> void handle(final E e) throws E {
        AppUi.LOG.error("Unhandled exception caught by SWT: %s",e);
        // SWT should already log the stack trace, this should not be needed
        AppUi.LOG.debug(e);
        throw e;
    }

}
