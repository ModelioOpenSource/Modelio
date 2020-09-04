/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.gproject.gproject;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;

/**
 * Manages project event listeners.
 */
@objid ("3d53b0fa-08b3-11e2-b193-001ec947ccaf")
public class GProjectMonitorSupport {
    @objid ("6181ee55-08b6-11e2-b193-001ec947ccaf")
    private List<IProjectMonitor> monitors = new ArrayList<>();

    /**
     * @param m a project monitor.
     */
    @objid ("6181ee58-08b6-11e2-b193-001ec947ccaf")
    public void addMonitor(IProjectMonitor m) {
        this.monitors.add(m);
    }

    /**
     * @param m a project monitor.
     */
    @objid ("6181ee5b-08b6-11e2-b193-001ec947ccaf")
    public void removeMonitor(IProjectMonitor m) {
        this.monitors.remove(m);
    }

    /**
     * Fire a project event on all projects monitors.
     * @param ev a project event.
     */
    @objid ("6181ee5e-08b6-11e2-b193-001ec947ccaf")
    public void fireMonitors(GProjectEvent ev) {
        if (this.monitors.isEmpty())
            FallBackMonitor.instance.handleProjectEvent(ev);
        else for (IProjectMonitor m : this.monitors) {
            m.handleProjectEvent(ev);
        }
    }

    /**
     * Default monitor used when no monitor is attached to a project.
     * <p>
     * Logs all project events.
     */
    @objid ("19b74eb9-0be5-11e2-bed6-001ec947ccaf")
    private static class FallBackMonitor implements IProjectMonitor {
        @objid ("19b74ebb-0be5-11e2-bed6-001ec947ccaf")
        public static final FallBackMonitor instance = new FallBackMonitor();

        @objid ("19b74ebd-0be5-11e2-bed6-001ec947ccaf")
        @Override
        public void handleProjectEvent(GProjectEvent ev) {
            switch (ev.type) {
            case FRAGMENT_DOWN:
                if (ev.throwable != null)
                    Log.error(ev.throwable);
                else
                    Log.error(ev.message);
                break;
            case WARNING:
                if (ev.throwable != null)
                    Log.warning(ev.throwable);
                else
                    Log.warning(String.valueOf(ev.message));
                break;
            default:
                break;
            }
        }

    }

}
