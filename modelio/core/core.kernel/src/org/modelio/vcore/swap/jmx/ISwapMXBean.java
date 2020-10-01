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

package org.modelio.vcore.swap.jmx;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.ISwap;

/**
 * JMX Bean spec for ISwap.
 */
@objid ("87434c25-492f-11e2-91c9-001ec947ccaf")
public interface ISwapMXBean {
    /**
     * Get the commit to swap frequency.
     * 
     * @return the commit to swap frequency
     */
    @objid ("dcbe874b-493b-11e2-91c9-001ec947ccaf")
    int getCommitFrequence();

    /**
     * @param value the commit to swap frequency
     */
    @objid ("dcbe874e-493b-11e2-91c9-001ec947ccaf")
    void setCommitFrequence(int value);

    /**
     * Get the count of calls to {@link ISwap#swap(org.modelio.vcore.smkernel.SmObjectData)}
     * 
     * @return the swap count
     */
    @objid ("dcbe8751-493b-11e2-91c9-001ec947ccaf")
    long getSwapCount();

    /**
     * Get the count of calls to {@link ISwap#restore(java.util.UUID)}
     * 
     * @return the restore count.
     */
    @objid ("dcbe8754-493b-11e2-91c9-001ec947ccaf")
    long getRestoreCount();

}
