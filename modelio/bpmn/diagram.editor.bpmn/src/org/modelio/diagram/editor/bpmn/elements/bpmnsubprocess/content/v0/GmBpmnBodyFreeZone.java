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

package org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.content.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.GmBpmnSubProcess;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessStructuredStyleKeys;
import org.modelio.diagram.elements.common.freezone.GmBodyFreeZone;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the contents of a bpmn sub process.
 * <p>
 * This gm has been replaced with {@link GmBpmnSubProcess}.
 * </p>
 */
@objid ("61c3e310-55b6-11e2-877f-002564c97630")
public class GmBpmnBodyFreeZone extends GmBodyFreeZone {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("61c3e313-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    /**
     * Current major version of this Gm. See {@link GmBpmnSubProcessMigrator}.
     */
    @objid ("61c3e316-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 1;

    @objid ("61c3e318-55b6-11e2-877f-002564c97630")
    public GmBpmnBodyFreeZone(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("61c3e322-55b6-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final IStyle style) {
        // The visibility may have changed so fires a notification.
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("61c5697e-55b6-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        if (property == GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT) {
            // The visibility changed so fires a notification.
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("61c56987-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getDisplayedStyle().getProperty(GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT);
    }

    @objid ("61c5698c-55b6-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(final boolean visible) {
        getDisplayedStyle().setProperty(GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT, visible);
    }

    @objid ("61c56991-55b6-11e2-877f-002564c97630")
    public GmBpmnBodyFreeZone() {
    }

    @objid ("61c56993-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return BpmnBaseElement.class.isAssignableFrom(type) &&
                                !BpmnBoundaryEvent.class.isAssignableFrom(type);
    }

    @objid ("61c5699c-55b6-11e2-877f-002564c97630")
    @Override
    public void removeChild(final GmNodeModel child) {
        super.removeChild(child);
    }

    @objid ("61c569a3-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnBodyFreeZone.");
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

    @objid ("61c569a9-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnBodyFreeZone.", GmBpmnBodyFreeZone.MINOR_VERSION);
    }

    @objid ("61c569af-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("61c569b4-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnBodyFreeZone.MAJOR_VERSION;
    }

}
