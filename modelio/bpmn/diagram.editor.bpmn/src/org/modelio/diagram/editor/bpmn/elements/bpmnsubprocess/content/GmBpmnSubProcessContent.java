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

package org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.content;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.diagrams.subprocess.GmBpmnSubProcessDiagram;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.workflow.GmWorkflow;
import org.modelio.diagram.elements.common.embeddeddiagram.GmEmbeddedDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a BPMN sub process diagram in a bpmn sub process.
 */
@objid ("1a02fd31-dff6-4934-9da1-87f1d2cf2b05")
public class GmBpmnSubProcessContent extends GmEmbeddedDiagram {
    @objid ("57261bfa-d00f-46f8-ae18-7988d41f430b")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("ae4afaf7-1112-450e-9037-f62ddcedbf93")
    private static final int MINOR_VERSION = 0;

    @objid ("3c31db86-25a5-4f2f-b182-a926c61f4aa4")
    private static final String MINOR_PREFIX = "GmBpmnSubProcessContent.";

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is used.
     * @param subProcessDiagram the unmasked diagram (can be <code>null</code>)
     * @param relatedRef a reference to the unmasked diagram (cannot be <code>null</code>).
     */
    @objid ("386898f0-294e-4d1f-a76a-f279ac4383b0")
    public GmBpmnSubProcessContent(IGmDiagram diagram, BpmnSubProcessDiagram subProcessDiagram, MRef relatedRef) {
        super(diagram, subProcessDiagram, relatedRef);
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("121f2833-1f73-4c14-b23f-6af22c666fb8")
    public GmBpmnSubProcessContent() {
        // Nothing to do.
    }

    @objid ("054dda84-7d4d-4066-a943-44361039b809")
    @Override
    public GmBpmnSubProcessDiagram getViewedDiagramModel(boolean loadIfNeeded) {
        return (GmBpmnSubProcessDiagram) super.getViewedDiagramModel(loadIfNeeded);
    }

    @objid ("59ba486a-67ae-4f67-a0c8-1a0226095a10")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmBpmnSubProcessDiagram viewedDiagramModel = getViewedDiagramModel(true);
        if (viewedDiagramModel != null) {
            return viewedDiagramModel.getWorkflow();
        }
        return null;
    }

    @objid ("34cdef67-374e-4f4e-b010-50e5bcb391c0")
    @Override
    public int getMajorVersion() {
        return GmBpmnSubProcessContent.MAJOR_VERSION;
    }

    @objid ("da1e9072-57e2-4c7c-96c6-9f47786299b5")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, GmBpmnSubProcessContent.MINOR_PREFIX);
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

    @objid ("9a298615-7a87-4716-af37-c5d53fe5048c")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("df95e457-40bb-4618-9803-2f827fb2e69a")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, GmBpmnSubProcessContent.MINOR_PREFIX, GmBpmnSubProcessContent.MINOR_VERSION);
    }

    @objid ("fbc5ef0b-9884-43f9-9c94-c3540dcc17ca")
    @Override
    public boolean isVisible() {
        return getDisplayedStyle().getProperty(GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT);
    }

    @objid ("024fb008-35c6-4f06-8fe3-3f1a71017acd")
    @Override
    protected void doSetVisible(final boolean visible) {
        getDisplayedStyle().setProperty(GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT, visible);
        super.doSetVisible(visible);
    }

    /**
     * As visible children, return the viewed diagram's workflow.
     */
    @objid ("49874bdd-51ec-469f-908e-75ff43fae826")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        List<GmNodeModel> ret = new ArrayList<>();
        GmBpmnSubProcessDiagram viewedDiagramModel = getViewedDiagramModel(true);
        if (viewedDiagramModel != null) {
            GmWorkflow workflow = viewedDiagramModel.getWorkflow();
            if (workflow != null) {
                ret.add(workflow);
            }
        }
        return ret;
    }

}
