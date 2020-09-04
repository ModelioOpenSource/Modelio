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

package org.modelio.diagram.editor.bpmn.elements.bpmnservicetask;

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
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("73843976-559d-4f5b-a8bb-6e0b92dcd799")
public class GmBpmnServiceTaskTypeLabel extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("abcd3219-07d6-499e-ac31-673544fe10d4")
    private static final int MINOR_VERSION = 0;

    @objid ("e008403e-9e07-4173-a71f-64bdfbfc7ca6")
    private static final int MAJOR_VERSION = 0;

    @objid ("c49ebb40-cfbe-42cc-8fca-a8d3b2f5e17f")
    public GmBpmnServiceTaskTypeLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("ec977607-bf5f-4040-a5b6-f5e69ce6e6ba")
    public GmBpmnServiceTaskTypeLabel() {
        // empty constructor for the serialization
    }

    @objid ("2e429327-321c-4c77-8b5d-086e50cca4b9")
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

    @objid ("04bc61eb-7903-4644-bea2-bf0282e46ade")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnTaskTypeLabel.", GmBpmnServiceTaskTypeLabel.MINOR_VERSION);
    }

    @objid ("007c9569-9210-4c0b-b798-d7549cf9c035")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("490cabfd-af45-461d-b72d-95730fe1f816")
    @Override
    public int getMajorVersion() {
        return GmBpmnServiceTaskTypeLabel.MAJOR_VERSION;
    }

    @objid ("07b5a976-576d-4d3d-b86e-bb2051cbd913")
    @Override
    public List<Stereotype> filterStereotypes(List<Stereotype> stereotypes) {
        return Collections.emptyList();
    }

    @objid ("06347e7e-ccab-4aba-a4e3-9759ef0a6c2f")
    @Override
    public List<TaggedValue> filterTags(List<TaggedValue> taggedValues) {
        return Collections.emptyList();
    }

    @objid ("734f035a-3681-457f-8692-6fcd5f16c1e3")
    @Override
    public IEditableText getEditableText() {
        return null;
    }

    @objid ("233c84ca-a62f-4053-a08c-7e47ccf517c6")
    @Override
    protected String computeMainLabel() {
        Boolean showLabel = getDisplayedStyle().getProperty(GmBpmnServiceTaskStructuredStyleKeys.SHOWREPRESENTED);
        if (showLabel) {
            final BpmnServiceTask task = (BpmnServiceTask) getRelatedElement();
            ModelElement type = Called.getTarget(task);
            if (task.isValid() && type != null) {
                return type.getName() + "()";
            }
        }
        return "";
    }

    @objid ("cc9328a4-c4fb-4818-84c9-76c14ecc26b4")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        if (property == GmBpmnServiceTaskStructuredStyleKeys.SHOWREPRESENTED) {
            if (updateMainLabelFromObModel()) {
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
            }
        }
        
        super.styleChanged(property, newValue);
    }

    @objid ("7efc3a3c-c48f-4457-a951-567acfe5ae74")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        if (updateMainLabelFromObModel()) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        }
        
        super.styleChanged(changedStyle);
    }

}
