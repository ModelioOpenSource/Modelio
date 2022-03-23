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
package org.modelio.uml.sequencediagram.editor.elements.executionspecification;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link ExecutionSpecification} in the diagram model.
 */
@objid ("d8eb1f32-55b6-11e2-877f-002564c97630")
public class GmExecutionSpecification extends GmCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d8eb1f3a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d8eca59b-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("b46794a8-73d7-452a-9955-3b37bba01e89")
    private static GmExecutionSpecificationStructuredStyleKeys KEYS = new GmExecutionSpecificationStructuredStyleKeys();

    @objid ("8f8142b7-17dd-4016-9ab2-74866b3c6895")
    private ExecutionSpecification element;

    /**
     * Create an execution rectangle
     * @param diagram The owning diagram
     * @param element the represented execution, may be null
     * @param ref the represented execution reference, may not be null
     */
    @objid ("d8eca59d-55b6-11e2-877f-002564c97630")
    public  GmExecutionSpecification(IGmDiagram diagram, ExecutionSpecification element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
    }

    /**
     * For deserialization only
     */
    @objid ("d8eca5a9-55b6-11e2-877f-002564c97630")
    public  GmExecutionSpecification() {
        // Nothing to do
    }

    @objid ("d8eca5ac-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (ExecutionSpecification.class.isAssignableFrom(type));
    }

    @objid ("d8eca5b4-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        if (!canCreate(el.getClass()) || !(el instanceof InteractionFragment)) {
            return false;
        }
        EList<Lifeline> ll1 = ((InteractionFragment) el).getCovered();
        EList<Lifeline> ll2 = getRelatedElement().getCovered();
        return !ll1.isEmpty() && !ll2.isEmpty() && ll1.get(0).equals(ll2.get(0));
    }

    @objid ("d8eca5bc-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        } else {
            return null;
        }
        
    }

    @objid ("d8eca5c5-55b6-11e2-877f-002564c97630")
    @Override
    public ExecutionSpecification getRelatedElement() {
        return this.element;
    }

    @objid ("d8eca5cc-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("d8eca5d3-55b6-11e2-877f-002564c97630")
    @Override
    public ExecutionSpecification getRepresentedElement() {
        return this.element;
    }

    @objid ("d8eca5da-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmExecutionSpecification.KEYS.getStyleKey(metakey);
    }

    @objid ("d8ee2c3f-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmExecutionSpecification.KEYS.getStyleKeys();
    }

    @objid ("d8ee2c48-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmExecutionSpecification.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
        
    }

    @objid ("d8ee2c4e-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        if (this.element != null) {
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, this.getLayoutData(), null);
        }
        
    }

    @objid ("d8ee2c51-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmExecutionSpecification.", GmExecutionSpecification.MINOR_VERSION);
        
    }

    @objid ("d8ee2c57-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (ExecutionSpecification) resolveRef(this.getRepresentedRef());
        
    }

    @objid ("d8ee2c5c-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmExecutionSpecification.MAJOR_VERSION;
    }

}
