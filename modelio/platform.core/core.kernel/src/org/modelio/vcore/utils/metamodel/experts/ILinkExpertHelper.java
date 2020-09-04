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

package org.modelio.vcore.utils.metamodel.experts;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("05a315ae-8cbe-4169-b544-26714ae0573c")
public interface ILinkExpertHelper {
    @objid ("97f7c877-f612-4043-a37b-9e2d2be812c6")
    boolean canLink(MClass linkMetaclass, MClass from, MClass to);

    @objid ("f2664e8e-b6d8-4c8c-a192-2446b996a667")
    boolean canLink(MClass linkMetaclass, MObject from, MObject to);

    @objid ("cba51170-1eea-4d79-995d-f59c432327dc")
    boolean canSource(MClass linkMetaclass, MClass from);

    @objid ("0da2c301-18f4-4c95-ba6d-76282cbd6b61")
    boolean canSource(MObject linkElement, MObject from);

    @objid ("abacfa15-688f-4d60-aa8b-cad7e165245c")
    boolean canTarget(MClass linkMetaclass, MClass to);

    @objid ("5978195f-3c30-4b23-83ea-7843a1095649")
    boolean canTarget(MObject linkElement, MObject to);

    /**
     * Get a model link source
     * 
     * @param aLink a model link.
     * @return the link source
     */
    @objid ("2d56f939-d910-4d31-b815-0ea32ce7cbdc")
    MObject getSource(MObject aLink);

    /**
     * Get a model link target.
     * 
     * @param aLink a model link.
     * @return the link target.
     */
    @objid ("27622648-6534-4b44-a196-01ec0305c4bb")
    MObject getTarget(MObject aLink);

    /**
     * Change a model link source.
     * 
     * @param link a model link.
     * @param oldSource the old source.
     * @param newSource the new source.
     * @throws java.lang.IllegalArgumentException if the new destination is illegal or the link is not a model link.
     */
    @objid ("fd428f48-04fb-46ca-a385-6deed17be35d")
    void setSource(MObject link, MObject oldSource, MObject newSource) throws IllegalArgumentException;

    /**
     * Change a model link target.
     * 
     * @param link a model link.
     * @param oldTarget the old target.
     * @param newTarget the new target.
     * @throws java.lang.IllegalArgumentException if the new destination is illegal or the link is not a model link.
     */
    @objid ("99932bc7-fd26-4404-9a76-a67b8fece59c")
    void setTarget(MObject link, MObject oldTarget, MObject newTarget) throws IllegalArgumentException;

}
