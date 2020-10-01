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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Access to the memory manager.
 * <p>
 * The memory manager swaps model objects on the disk when memory is low.
 * It supports listeners who are activated when it frees memory.
 */
@objid ("7e644839-9d36-43e8-93f4-bce7a061909d")
public interface IMemoryManager {
    /**
     * Add a memory listener.
     * 
     * @param listener a memory listener.
     */
    @objid ("888475a2-614b-4dbd-aa34-f464cae24b22")
    void addMemoryListener(IMemoryEventListener listener);

    /**
     * Remove a memory listener.
     * 
     * @param listener a memory listener.
     */
    @objid ("1a535b79-4663-43c8-acb8-f18f8efbfece")
    void removeMemoryListener(IMemoryEventListener listener);

    /**
     * @return <code>true</code> if memory support is enabled else <code>false</code>.
     */
    @objid ("9291e0af-b7c7-4be9-9a4a-6dde3c795661")
    boolean isSwapEnabled();

}
