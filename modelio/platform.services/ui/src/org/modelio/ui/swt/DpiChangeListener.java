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

package org.modelio.ui.swt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.internal.DPIUtil;
import org.eclipse.swt.widgets.Display;
import org.modelio.ui.plugin.UI;

/**
 * Service that polls the SWT display for DPI changes regularly.
 * <p>
 * 
 * Calls by reflection internal protected methods of {@link Display} and {@link Device}.
 * Works around {@link Display#gsettingsProc} not being called at least on Linux.
 * <p>
 * Runs on Linux 64, not tested on Windows.
 * @author cma
 * @since 3.7
 * @deprecated Experimental, not used, may have strange side effects, use at your own risks.
 */
@objid ("c31b650f-ea3c-4d3b-82ff-a9787d3d7db6")
@Deprecated
public final class DpiChangeListener {
    @objid ("463f492b-f32e-43c2-966c-6d19079f2e90")
    public static void listenDpiChanges() {
        Display display = Display.getDefault();
        listenDpiChanges(display);
    }

    @objid ("3c6a511b-1eea-461d-b4f0-6b711d0ab784")
    public static void listenDpiChanges(Display display) {
        try {
            Method getDeviceZoom = Device.class.getDeclaredMethod("getDeviceZoom");
            getDeviceZoom.setAccessible(true);
        
            Method gsettingsProc = Display.class.getDeclaredMethod("gsettingsProc", long.class, long.class, long.class );
            gsettingsProc.setAccessible(true);
        
            Runnable runnable = new Runnable() {
                @SuppressWarnings ("restriction")
                @Override
                public void run() {
                    if (display.isDisposed()) {
                        return;
                    }
        
                    try {
                        int newZoom = (int) getDeviceZoom.invoke(display);
                        int curZoom = DPIUtil.getDeviceZoom();
        
                        // DPIUtil.setDeviceZoom rounds native zoom depending on env variables.
                        // Need to run this conversion first to have comparable zoom levels.
                        DPIUtil.setDeviceZoom(newZoom);
                        newZoom = DPIUtil.getDeviceZoom();
        
                        if (newZoom != curZoom) {
                            // We could avoid calling this and call layout(true, true) on all shells instead
                            gsettingsProc.invoke(display, 0, 0, 1);
                        } 
        
                        // Schedule again
                        display.timerExec(2000, this);
                    } catch (RuntimeException | IllegalAccessException | InvocationTargetException e) {
                        UI.LOG.info(e);
                    }
                }
            };
        
            // First schedule
            display.timerExec(5000, runnable);
        
        } catch (NoSuchMethodException | SecurityException e) {
            UI.LOG.info(e);
        }
    }

}
