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
package org.modelio.vcore.smkernel.mapi;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("5e399feb-6981-4e6b-a519-49d779352f62")
public interface MExpert {
    /**
     * Tells whether the owner can own the given composed element using the given dependency name.
     * @param owner The owner node, must not be null.
     * @param composed The composed element, must not be null
     * @param dep optional: The name of the dependency on the owner used to add the composed.
     * @return true if the composed can be added, else false.
     */
    @objid ("cd910b86-4a26-4bcb-87cb-2cb3c01d98c7")
    boolean canCompose(MClass owner, MClass composed, String dep);

    /**
     * Tells whether the owner can own an instance of the given metaclass using the given dependency name.
     * @param owner The owner node, must not be null.
     * @param composed The metaclass of the composed element, must not be null
     * @param dep optional: The name of the dependency on the owner used to add the composed.
     * @return true if the composed can be added, else false.
     */
    @objid ("bb96ed8b-e9c4-4db9-a2e0-d62c6dbc33dd")
    boolean canCompose(MObject owner, MClass composed, String dep);

    /**
     * Tells whether the owner can own the given composed element using the given dependency name.
     * @param owner The owner node, must not be null.
     * @param composed The composed element, must not be null
     * @param dep optional: The name of the dependency on the owner used to add the composed.
     * @return true if the composed can be added, else false.
     */
    @objid ("fa0f360a-03d8-42e7-8131-716697146594")
    boolean canCompose(MObject owner, MObject composed, String dep);

    /**
     * Whether or not a dependency 'dep' can be established between the 'source' object and an instance of 'target' metaclass based
     * on their metaclass and stereotypes.
     * @param dep the name of the dependency on the owner used to add the target.
     */
    @objid ("03b6b173-90db-4ea8-9b48-344b0ed315d3")
    boolean canDep(MObject source, MClass target, String dep);

    /**
     * Whether or not a dependency 'dep' exists between 'source' and 'target' metaclass.
     * @param dep the name of the dependency on the owner used to add the target.
     */
    @objid ("41c981c8-c910-43f7-ac16-e29dcbf88e97")
    boolean canDep(MClass source, MClass target, String dep);

    /**
     * Whether or not a dependency 'dep' can be established between the 'source' and 'target' objects based on their metaclass and
     * stereotypes.
     * @param dep the name of the dependency on the owner used to add the target.
     */
    @objid ("283901bc-e112-462a-8bf0-dcee2114f64a")
    boolean canDep(MObject source, MObject target, String dep);

    @objid ("c35533fc-02ff-4f73-b5bb-0d4de2871242")
    boolean canLink(MClass link, MClass from, MClass to);

    @objid ("9b56cd7e-2178-4c00-b35e-615da0242e7e")
    boolean canLink(MClass link, MObject from, MObject to);

    @objid ("a94c9f31-d4b7-4d12-962a-2523c80c688c")
    boolean canSource(MClass link, MClass from);

    @objid ("64bc0124-2995-4950-98e2-385675ccbb36")
    boolean canSource(MObject link, MObject from);

    @objid ("42914d5d-315d-4584-acff-ece3192f9e9f")
    boolean canTarget(MClass link, MClass to);

    @objid ("9b876a78-fbaa-48c4-bfc3-a6f7385446d8")
    boolean canTarget(MObject link, MObject to);

    @objid ("194473ff-051f-473d-bbea-930888a17be6")
    MDependency getDefaultCompositionDep(MObject owner, MObject composed);

    @objid ("c7163a2b-a613-40a8-8744-7c9982cd284e")
    MDependency getDefaultCompositionDep(MClass owner, MClass composed);

    /**
     * Get the lists of all possible dependencies between the 'source' and 'target' objects.
     */
    @objid ("86aa98a3-7c45-4a01-8783-8eabefb772d1")
    List<MDependency> getDeps(MObject source, MObject target);

    /**
     * Get a model link source
     * @param aLink a model link.
     * @return the link source
     */
    @objid ("3ce8318b-0642-40d9-9398-0c0b0cc54ac4")
    MObject getSource(MObject aLink);

    /**
     * Get a model link target.
     * @param aLink a model link.
     * @return the link target.
     */
    @objid ("ff9e817a-2eff-4274-be5a-067763d9dbc6")
    MObject getTarget(MObject aLink);

    @objid ("c25f37a3-c563-4577-ab1f-ddfdcdc339d6")
    boolean isLink(MClass metaclass);

    /**
     * Change a model link source.
     * @param link a model link.
     * @param oldSource the old source.
     * @param newSource the new source.
     * @throws IllegalArgumentException if the new destination is illegal or the link is not a model link.
     */
    @objid ("f45ea75e-3014-43e2-b4d7-1a03ba70e621")
    void setSource(MObject link, MObject oldSource, MObject newSource) throws IllegalArgumentException;

    /**
     * Change a model link target.
     * @param link a model link.
     * @param oldTarget the old target.
     * @param newTarget the new target.
     * @throws IllegalArgumentException if the new destination is illegal or the link is not a model link.
     */
    @objid ("c607b1e6-3847-4d51-96e9-1553291109e5")
    void setTarget(MObject link, MObject oldTarget, MObject newTarget) throws IllegalArgumentException;
}

