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
package org.modelio.app.ui;

import java.lang.management.MemoryUsage;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.vcore.session.api.memory.IMemoryEventListener;

@objid ("7da6bcb1-d32f-4eb7-883e-dd7dd4ae8cf4")
public class SwapLogMonitor implements IMemoryEventListener {
    @objid ("d93a5f5c-9c36-4ff8-b4c7-93c5734a6ffb")
    public long begin;

    @objid ("632c9703-e6ee-4521-98f4-b54b87e069f6")
    @Override
    public void onFreeMemoryStart(MemoryUsage memState) {
        AppUi.LOG.debug("SWAP BEGIN: used=%dK committed=%dK max=%dK ", memState.getUsed()/1000, memState.getCommitted()/1000, memState.getMax()/1000); 
        this.begin = System.currentTimeMillis();
        
    }

    @objid ("aed78bf8-2ff3-430d-ba2a-de94f62e3568")
    @Override
    public void onFreeMemoryEnd(int swappedObjects, MemoryUsage memState) {
        long end = System.currentTimeMillis();
        AppUi.LOG.debug("SWAP: %d swapped objects in %d ms, (%d objects/s)", swappedObjects, (end-this.begin), (swappedObjects *1000)/(end-this.begin+1));
        AppUi.LOG.debug("SWAP END: memory usage used=%dK committed=%dK max=%dK ", memState.getUsed()/1000, memState.getCommitted()/1000, memState.getMax()/1000);
        
    }

}
