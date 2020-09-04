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

package org.modelio.diagram.editor.bpmn.elements.bpmnreceivetask;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("6b47e99b-730f-417f-bb5d-1d05e3d05ab0")
public class GmBpmnReceiveTaskTypeLabel extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("225f60b0-87ec-48c4-b1c4-49e9904c9815")
    private static final int MINOR_VERSION = 0;

    @objid ("d0cddedf-7c3f-4e9e-ad3a-6417344fb1da")
    private static final int MAJOR_VERSION = 0;

    @objid ("f48d6405-e4ad-40a2-a127-cc993e9749f9")
    public GmBpmnReceiveTaskTypeLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("00179a62-6d54-47fa-9f2b-de91e15419bd")
    public GmBpmnReceiveTaskTypeLabel() {
        // empty constructor for the serialization
    }

    @objid ("972dbc05-b00c-433d-b7a7-69db7d6764bc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnTaskTypeLabel.");
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

    @objid ("b08c4c70-ecfe-484c-91b4-3e2a42822018")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnTaskTypeLabel.", GmBpmnReceiveTaskTypeLabel.MINOR_VERSION);
    }

    @objid ("b7551221-e74a-4e5e-b951-117744aca0e8")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("cb81c28a-7153-4f2d-9f40-737d185a9ccb")
    @Override
    public int getMajorVersion() {
        return GmBpmnReceiveTaskTypeLabel.MAJOR_VERSION;
    }

    @objid ("b70c9c68-a580-4e26-97fe-33f3d1542f79")
    @Override
    public List<Stereotype> filterStereotypes(List<Stereotype> stereotypes) {
        return Collections.emptyList();
    }

    @objid ("6e1c9bed-ccd2-4a3f-bf0e-bc3d5f8b04cb")
    @Override
    public List<TaggedValue> filterTags(List<TaggedValue> taggedValues) {
        return Collections.emptyList();
    }

    @objid ("05b9e910-81ec-4ca7-a378-75357b9fd37e")
    @Override
    public IEditableText getEditableText() {
        return null;
    }

    @objid ("69cfe727-914b-4c4a-9baa-9ba4a8656ebd")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        if (property == GmBpmnReceiveTaskStructuredStyleKeys.SHOWREPRESENTED) {
            if (updateMainLabelFromObModel()) {
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
            }
        }
        
        super.styleChanged(property, newValue);
    }

    @objid ("1c1e2c9a-6d5b-4968-bb40-d64eea215714")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        if (updateMainLabelFromObModel()) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        }
        
        super.styleChanged(changedStyle);
    }

    @objid ("ff2e87eb-081d-4f35-b3be-7a61e4d64960")
    @Override
    protected String computeMainLabel() {
        Boolean showLabel = getDisplayedStyle().getProperty(GmBpmnReceiveTaskStructuredStyleKeys.SHOWREPRESENTED);
        if (showLabel) {
            final BpmnReceiveTask task = (BpmnReceiveTask) getRelatedElement();
            ModelElement type = Called.getTarget(task);
            if (task.isValid() && type != null) {
                return type.getName() + "()";
            }
        }
        return "";
    }

}
