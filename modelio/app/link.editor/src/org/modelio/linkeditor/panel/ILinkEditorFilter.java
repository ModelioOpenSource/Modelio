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

package org.modelio.linkeditor.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("426c6d50-1a4c-4edc-88a9-f18786f02077")
public interface ILinkEditorFilter {
    /**
     * @param enabledLinkType a link metaclass which {@link #isLinkTypeEnabled(MClass)} returns <code>true</code> for.
     * @param st a stereotype. <code>null</code> for the 'naked' metaclass.
     * @return <code>true</code> if the stereotyped link is not filtered.
     */
    @objid ("37b91e5a-2e0f-442b-8a6f-435925211564")
    boolean accept(MClass enabledLinkType, Stereotype st);

    /**
     * @param mc a link metaclass.
     * @return true if link of type 'mc' may be acceptable(final decision depends on the stereotype in accept()). false if no such link is acceptable whatever the stereotype
     */
    @objid ("d9cd27b4-dc71-4ba0-9ae6-2bb326d80e0c")
    boolean isLinkTypeEnabled(MClass mc);

}
