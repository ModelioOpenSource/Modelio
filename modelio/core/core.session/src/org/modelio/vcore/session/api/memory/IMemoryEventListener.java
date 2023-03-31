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
package org.modelio.vcore.session.api.memory;

import java.lang.management.MemoryUsage;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Memory event listener.
 * <p>
 * These listeners are activated by the {@link IMemoryManager} when freeing memory.
 * <p>
 * Be warned that <b>the SWT Display thread may be blocked</b> by the
 * memory manager when this method is called.
 * Please interact <b>asynchronously</b> with the SWT display to avoid
 * dead locks !
 */
@objid ("e25297da-ba18-41cf-a9db-d45d75fa4f05")
public interface IMemoryEventListener {
    /**
     * Called when the memory manager begins swapping model objects.
     * <p>
     * Be warned that <b>the SWT Display thread may be blocked</b> by the
     * memory manager when this method is called.
     * Please interact <b>asynchronously</b> with the SWT display to avoid
     * dead locks !
     */
    @objid ("6e375350-f4f2-44a8-86f4-d4a328480b7c")
    void onFreeMemoryStart(MemoryUsage memState);

    /**
     * Called when the memory manager ends swapping model objects.
     * @param swappedObjects the count of model objects that were freed.
     */
    @objid ("6b917eca-57b6-465e-b027-c492047a6f10")
    void onFreeMemoryEnd(int swappedObjects, MemoryUsage memState);
}

