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
package org.modelio.metamodel.impl.expert.standard.links;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("005da692-d07d-1098-bcec-001ec947cd2a")
public interface ILinkExpert {
    @objid ("005daf84-d07d-1098-bcec-001ec947cd2a")
    boolean canLink(MClass linkMetaclass, MClass from, MClass to);

    @objid ("005dd338-d07d-1098-bcec-001ec947cd2a")
    boolean canLink(MClass linkMetaclass, MObject from, MObject to);

    @objid ("005dee4a-d07d-1098-bcec-001ec947cd2a")
    boolean canSource(MClass linkMetaclass, MClass from);

    @objid ("005e00ba-d07d-1098-bcec-001ec947cd2a")
    boolean canSource(MObject linkElement, MObject from);

    @objid ("005e1226-d07d-1098-bcec-001ec947cd2a")
    boolean canTarget(MClass linkMetaclass, MClass to);

    @objid ("005e266c-d07d-1098-bcec-001ec947cd2a")
    boolean canTarget(MObject linkElement, MObject to);

}
