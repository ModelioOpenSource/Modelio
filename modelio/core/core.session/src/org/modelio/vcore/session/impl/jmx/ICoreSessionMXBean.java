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
package org.modelio.vcore.session.impl.jmx;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.swap.jmx.ISwapMXBean;

/**
 * CoreSession JMX bean interface.
 */
@objid ("87cfc625-4935-11e2-91c9-001ec947ccaf")
public interface ICoreSessionMXBean {
    /**
     * @return the swap monitoring JMX bean
     */
    @objid ("dcab74ac-493b-11e2-91c9-001ec947ccaf")
    ISwapMXBean getSwapBean();

    /**
     * @return the repository count.
     */
    @objid ("dcab74ae-493b-11e2-91c9-001ec947ccaf")
    int getRepositoryCount();

    /**
     * @return the sesion kernel id.
     */
    @objid ("dcab74b0-493b-11e2-91c9-001ec947ccaf")
    int getKernelId();

    /**
     * Returns the number of SmObjectImpl currently in memory.
     * @return the loaded objects count.
     */
    @objid ("b2e05929-49c5-11e2-91c9-001ec947ccaf")
    int getLoadedCount();

    /**
     * Get the number of deleted SmObjectImpl in memory.
     * @return the deleted objects count.
     */
    @objid ("b2e0592b-49c5-11e2-91c9-001ec947ccaf")
    int getDeletedObjectsCount();

}
