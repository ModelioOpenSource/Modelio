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

package org.modelio.diagram.editor.bpmn.elements.bpmnsendtask;

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
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("75353b25-d82a-4e34-aa4b-d6ad56a9052b")
public class GmBpmnSendTaskTypeLabel extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("90ca7d1e-e266-469a-a164-0ecf27abd356")
    private static final int MINOR_VERSION = 0;

    @objid ("621b4a31-7189-4082-bd76-05cc58a92453")
    private static final int MAJOR_VERSION = 0;

    @objid ("c5aa41a8-5eeb-4cac-80e7-3b49d5578f6d")
    public GmBpmnSendTaskTypeLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("51c011aa-134a-4596-aee3-96b2a11ea846")
    public GmBpmnSendTaskTypeLabel() {
        // empty constructor for the serialization
    }

    @objid ("eeabd0cf-b276-47b0-bc9e-6013387b3ec6")
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

    @objid ("a2d15207-94cb-4bc7-8d45-fb8c417ee71f")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnTaskTypeLabel.", GmBpmnSendTaskTypeLabel.MINOR_VERSION);
    }

    @objid ("5b4ad10f-907e-42d8-b486-53fb1d746c04")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("5889dba4-babe-473b-bbc4-d647331a82e3")
    @Override
    public int getMajorVersion() {
        return GmBpmnSendTaskTypeLabel.MAJOR_VERSION;
    }

    @objid ("aa554f9b-47cc-4fc2-a4a0-60d03504db2f")
    @Override
    public List<Stereotype> filterStereotypes(List<Stereotype> stereotypes) {
        return Collections.emptyList();
    }

    @objid ("0a6ae455-843d-427d-8e59-5719c553b341")
    @Override
    public List<TaggedValue> filterTags(List<TaggedValue> taggedValues) {
        return Collections.emptyList();
    }

    @objid ("38b94b5d-b90e-4dda-888f-1904625168f7")
    @Override
    public IEditableText getEditableText() {
        return null;
    }

    @objid ("93913c41-22b5-4580-b0cf-b0cd92e84b2f")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        if (property == GmBpmnSendTaskStructuredStyleKeys.SHOWREPRESENTED) {
            if (updateMainLabelFromObModel()) {
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
            }
        }
        
        super.styleChanged(property, newValue);
    }

    @objid ("10f79f58-54f2-4ef7-96aa-c55da04731bd")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        if (updateMainLabelFromObModel()) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        }
        
        super.styleChanged(changedStyle);
    }

    @objid ("4f6ee378-85bf-431c-8409-a62b92496fbc")
    @Override
    protected String computeMainLabel() {
        Boolean showLabel = getDisplayedStyle().getProperty(GmBpmnSendTaskStructuredStyleKeys.SHOWREPRESENTED);
        if (showLabel) {
            final BpmnSendTask task = (BpmnSendTask) getRelatedElement();
            ModelElement type = Called.getTarget(task);
            if (task.isValid() && type != null) {
                return type.getName() + "()";
            }
        }
        return "";
    }

}
