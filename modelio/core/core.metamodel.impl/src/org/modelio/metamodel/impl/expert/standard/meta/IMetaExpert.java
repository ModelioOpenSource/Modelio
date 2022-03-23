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
package org.modelio.metamodel.impl.expert.standard.meta;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The MetaExpert tool can answer questions about dependencies between metaclass or elements.
 * <ol>
 * <li>is the model X <>-----dep-> Y possible?</li>
 * <li>get the default composition dependency between X and Y.</li>
 * <li>is the model X -------dep-> Y possible?</li>
 * <li>get possibles dependencies between X and Y.</li>
 * </ol>
 * where X, Y are either elements or metaclasses and dep a SmDependency name.
 */
@objid ("005a1e82-e4eb-1097-bcec-001ec947cd2a")
public interface IMetaExpert {
    /**
     * Tells whether the owner can own the given composed element using the given dependency name.
     * @param dependency
     * optional: The name of the dependency on the owner used to add the composed.
     * @param owner The owner node, must not be null.
     * @param composed The composed element, must not be null
     * @return true if the composed can be added, else false.
     */
    @objid ("00427228-e50d-1097-bcec-001ec947cd2a")
    boolean canCompose(MClass owner, MClass composed, String dep);

    /**
     * Tells whether the owner can own an instance of the given metaclass using the given dependency name.
     * @param dependency
     * optional: The name of the dependency on the owner used to add the composed.
     * @param owner The owner node, must not be null.
     * @param composed The metaclass of the composed element, must not be null
     * @return true if the composed can be added, else false.
     */
    @objid ("004276f6-e50d-1097-bcec-001ec947cd2a")
    boolean canCompose(MObject owner, MClass composed, String dep);

    /**
     * Tells whether the owner can own the given composed element using the given dependency name.
     * @param dependency
     * optional: The name of the dependency on the owner used to add the composed.
     * @param owner The owner node, must not be null.
     * @param composed The composed element, must not be null
     * @return true if the composed can be added, else false.
     */
    @objid ("00427b4c-e50d-1097-bcec-001ec947cd2a")
    boolean canCompose(MObject owner, MObject composed, String dep);

    /**
     * Whether or not a dependency 'dep' can be established between the 'source' object and an instance of 'target' metaclass based
     * on their metaclass and stereotypes.
     * @param source
     * @param target
     * @param dep @return
     */
    @objid ("00427f84-e50d-1097-bcec-001ec947cd2a")
    boolean canDep(MObject source, MClass target, String dep);

    /**
     * Whether or not a dependency 'dep' exists between 'source' and 'target' metaclass.
     * @param source
     * @param target
     * @param dep @return
     */
    @objid ("004283c6-e50d-1097-bcec-001ec947cd2a")
    boolean canDep(MClass source, MClass target, String dep);

    /**
     * Whether or not a dependency 'dep' can be established between the 'source' and 'target' objects based on their metaclass and
     * stereotypes.
     * @param source
     * @param target
     * @param dep @return
     */
    @objid ("00428808-e50d-1097-bcec-001ec947cd2a")
    boolean canDep(MObject source, MObject target, String dep);

}
