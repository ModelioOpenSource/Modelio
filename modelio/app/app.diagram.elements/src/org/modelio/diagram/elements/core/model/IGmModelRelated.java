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
package org.modelio.diagram.elements.core.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Interface defining the minimal contract to be fulfilled by a Gm so that it can be shown as a ghost.
 * 
 * @author fpoyer
 */
@objid ("80827ebd-1dec-11e2-8cad-001ec947c8cc")
public interface IGmModelRelated extends IGmObject {
    /**
     * Event to be fired from <code>#refreshFromObModel()</code> so that refresh can be delegated to edit policies.
     * <p>
     * GmModel does not currently fire this property change event. You have to fire it yourself from subclasses in your <code>refreshFromObModel()</code> redefinition.
     * </p>
     * 
     * @since 3.7
     */
    @objid ("2a245401-f651-4648-a39b-f342902a74f2")
    public static final String PROP_REFRESH_FROM_OBMODEL = "REFRESH_FROM_OBMODEL";

    /**
     * Event to be fired from <code>#refreshFromObModel()</code> so that refresh can be delegated to edit policies.
     * <p>
     * GmModel does not currently fire this property change event. You have to fire it yourself from subclasses in your <code>refreshFromObModel()</code> redefinition.
     * </p>
     * 
     * @since 3.7
     */
    @objid ("cb026d6d-1461-4361-bf04-bcef53ffef48")
    public static final String PROP_OBMODEL_DELETED = "OBMODEL_DELETED";

    /**
     * @return the main label of the element, usually its name.
     */
    @objid ("80827ebf-1dec-11e2-8cad-001ec947c8cc")
    String getGhostLabel();

    /**
     * @return the metaclass of the element.
     */
    @objid ("80827ec2-1dec-11e2-8cad-001ec947c8cc")
    String getGhostMetaclass();

    /**
     * @return the Id of the element.
     */
    @objid ("80827ec5-1dec-11e2-8cad-001ec947c8cc")
    String getGhostId();

    /**
     * Get the element this {@link GmModel} is related to.
     * <p>
     * <b>Note:</b> May return <i>null</i> if the element is not resolved.
     * @return the represented element or <i>null</i> if the element is not resolved.
     */
    @objid ("80827ec8-1dec-11e2-8cad-001ec947c8cc")
    MObject getRelatedElement();

    /**
     * Get the metaclass of the element this {@link GmModel} is related to.
     * <p>
     * <strong>Note:</strong> This method never return <code>null</code> .
     * @return the metaclass this GmModel is in charge of relating, never null.
     */
    @objid ("ef12e327-80d4-44f7-bd2d-06472a677f45")
    MClass getRelatedMClass();

    /**
     * <p>
     * Get the element this {@link GmModel} represents.
     * </p>
     * <p>
     * <strong>Note:</strong> return <code>null</code> if this GmModel is not the one "in charge" or representing the
     * element. You may want to use {@link #getRelatedElement()} instead.
     * </p>
     * <p>
     * Default implementation returns <code>null</code>. Subclasses may override this method to provide an actual
     * MObject if they are the GmModel representing the element.
     * </p>
     * @return <i>null</i> or the represented element if this GmModel is in charge of representing an element.
     */
    @objid ("80827ecb-1dec-11e2-8cad-001ec947c8cc")
    MObject getRepresentedElement();

    /**
     * <p>
     * Get reference to the element this {@link GmModel} is related to.
     * </p>
     * <p>
     * <strong>Note:</strong> This method should never return <code>null</code> and is not intended to be overridden.
     * </p>
     * @return a {@link MRef reference} to the represented element if this GmModel is in charge of representing an
     * element.
     */
    @objid ("80827ece-1dec-11e2-8cad-001ec947c8cc")
    MRef getRepresentedRef();
}

