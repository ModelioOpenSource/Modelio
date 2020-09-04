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

package org.modelio.diagram.editor.bpmn.elements.participant.content;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processdesign.GmBpmnProcessDesignDiagram;
import org.modelio.diagram.editor.bpmn.elements.workflow.GmWorkflow;
import org.modelio.diagram.elements.common.embeddeddiagram.GmEmbeddedDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a BPMN process design diagram in a bpmn participant.
 */
@objid ("cf8582bc-5da5-4dff-966e-41c5606aaee1")
public class GmBpmnParticipantContent extends GmEmbeddedDiagram {
    @objid ("e7ff174f-c215-4e04-85ed-27fd9aae7074")
    private static final int MAJOR_VERSION = 0;

    @objid ("295f1701-aef3-4d28-a8e7-df538b220060")
    private static final String MINOR_PREFIX = "GmBpmnParticipantContent.";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f6fe873e-3348-4ee1-aec0-5c14ed6019e2")
    private static final int MINOR_VERSION = 0;

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is used.
     * @param processDiagram the unmasked diagram (can be <code>null</code>)
     * @param relatedRef a reference to the unmasked diagram (cannot be <code>null</code>).
     */
    @objid ("27e8bbe2-374a-4fa9-aa3b-57920a600e77")
    public GmBpmnParticipantContent(IGmDiagram diagram, BpmnProcessDesignDiagram processDiagram, MRef relatedRef) {
        super(diagram, processDiagram, relatedRef);
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("00f6413f-4a61-41c8-acdf-18bad37bdbcb")
    public GmBpmnParticipantContent() {
        // Nothing to do.
    }

    @objid ("c7f9a053-edd4-4c5d-aa9a-40d738ed9c61")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmBpmnProcessDesignDiagram viewedDiagramModel = getViewedDiagramModel(true);
        if (viewedDiagramModel != null) {
            return viewedDiagramModel.getWorkflow();
        }
        return null;
    }

    @objid ("31e112bb-db00-434c-9b8f-e6037f3dec4c")
    @Override
    public int getMajorVersion() {
        return GmBpmnParticipantContent.MAJOR_VERSION;
    }

    @objid ("18b9ecb3-5bd0-4ccc-abe4-82e8b67dc008")
    @Override
    public GmBpmnProcessDesignDiagram getViewedDiagramModel(boolean loadIfNeeded) {
        return (GmBpmnProcessDesignDiagram) super.getViewedDiagramModel(loadIfNeeded);
    }

    /**
     * As visible children, return the viewed diagram's workflow.
     */
    @objid ("ee427c5b-e3c5-44b9-8891-48b1b8ed8467")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        List<GmNodeModel> ret = new ArrayList<>();
        GmWorkflow workflow = getViewedWorkflow(true);
        if (workflow != null) {
            ret.add(workflow);
        }
        return ret;
    }

    @objid ("7a3fb74c-8562-45bd-bd9b-d01522662108")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, GmBpmnParticipantContent.MINOR_PREFIX);
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        default:
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
    }

    @objid ("92b7c92f-54c3-4618-8efa-12d9fe84af72")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, GmBpmnParticipantContent.MINOR_PREFIX, GmBpmnParticipantContent.MINOR_VERSION);
    }

    @objid ("f0eda903-0dcf-4ac7-99d3-427dc269caa1")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("7fe28645-296e-4c00-9fdc-0500a516f5a7")
    public GmWorkflow getViewedWorkflow(boolean loadIfNeeded) {
        GmBpmnProcessDesignDiagram viewedDiagramModel = getViewedDiagramModel(loadIfNeeded);
        if (viewedDiagramModel != null) {
            return viewedDiagramModel.getWorkflow();
        }
        return null;
    }

    @objid ("9f74a174-4d4a-4de0-a0bd-4e08478b2ea4")
    @Override
    public boolean isViewToCenter() {
        GmWorkflow wf = getViewedWorkflow(true);
        if (wf == null) {
            return true;
        }
        
        if (wf.isEmbeddedWithLanes()) {
            return false;
        }
        return super.isViewToCenter();
    }

}
