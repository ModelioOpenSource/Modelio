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

package org.modelio.diagram.editor.bpmn.elements.bpmncallactivity;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodeheader.GmBpmnNodeHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the classifier header.
 * <p>
 * Contains for the moment the class name.<br>
 * Will contain in the future:<br>
 * - its visibility <br>
 * - tagged values <br>
 * - &lt;&lt;stereotypes names>> <br>
 * - a stereotype icons bar <br>
 */
@objid ("609a5714-55b6-11e2-877f-002564c97630")
public class GmBpmnCallActivityHeader extends GmBpmnNodeHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("609a5716-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("609a5719-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor
     */
    @objid ("609a571b-55b6-11e2-877f-002564c97630")
    public GmBpmnCallActivityHeader(final IGmDiagram diagram, final MRef relatedRef, final boolean showIcon) {
        super(diagram, relatedRef, showIcon);
    }

    @objid ("609a5727-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        BpmnCallActivity callActivity = (BpmnCallActivity) getRelatedElement();
        if (callActivity != null) {
            String mlabel;
            if (!callActivity.getName().isEmpty()) {
                mlabel = callActivity.getName();
            } else {
                mlabel = "";
            }
        
            String reference = null;
            Boolean showrepresented = getDisplayedStyle().getProperty(GmBpmnCallActivityStructuredStyleKeys.SHOWREPRESENTED);
            if (showrepresented) {
                ModelElement type = Called.getTarget(callActivity);
                if (type != null) {
                    reference = type.getName();
                }
            }
        
            StringBuilder s = new StringBuilder();
            String basename = getDiagram().getModelManager().getModelServices().getElementNamer().getBaseName(callActivity.getMClass());
            if (mlabel != null && !mlabel.isEmpty()) {
                if (!mlabel.startsWith(basename) || reference == null) {
                    s.append(mlabel);
        
                    if (reference != null) {
                        s.append(": ");
                    }
                }
        
            }
            if (reference != null) {
                s.append(reference);
            }
            return s.toString();
        } else {
            return "";
        }
    }

    @objid ("609a572c-55b6-11e2-877f-002564c97630")
    protected List<Image> getRepresentedIcon() {
        List<Image> res = new ArrayList<>();
        Boolean showrepresented = getDisplayedStyle().getProperty(GmBpmnCallActivityStructuredStyleKeys.SHOWREPRESENTED);
        BpmnCallActivity element = (BpmnCallActivity) getRelatedElement();
        if (showrepresented) {
            ModelElement type = Called.getTarget(element);
            if (type != null) {
                res.add(ElementImageService.getIcon(type));
            }
        }
        return res;
    }

    /**
     * Empty c'torfor deserialization.
     */
    @objid ("609a5732-55b6-11e2-877f-002564c97630")
    public GmBpmnCallActivityHeader() {
        // empty constructor for the serialization
    }

    @objid ("609a5735-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnCallActivityHeader.");
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

    @objid ("609a573b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnCallActivityHeader.", GmBpmnCallActivityHeader.MINOR_VERSION);
    }

    @objid ("609bdd99-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("609bdd9e-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnCallActivityHeader.MAJOR_VERSION;
    }

}
