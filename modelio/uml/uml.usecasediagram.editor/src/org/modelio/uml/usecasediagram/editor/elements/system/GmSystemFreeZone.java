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

package org.modelio.uml.usecasediagram.editor.elements.system;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e4ec421-55b7-11e2-877f-002564c97630")
public final class GmSystemFreeZone extends GmFreeZone {
    @objid ("5e504a7c-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("5e504a7f-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5e504a81-55b7-11e2-877f-002564c97630")
    public GmSystemFreeZone() {
        // Nothing to do.
    }

    @objid ("5e504a84-55b7-11e2-877f-002564c97630")
    public GmSystemFreeZone(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("5e504a8f-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        // Parent node should know.
        return getParent().canCreate(type);
    }

    @objid ("5e504a98-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        // Parent node should know.
        return getParent().canUnmask(el);
    }

    @objid ("5e504aa1-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getParent() != null && getParent().getRepresentationMode() == RepresentationMode.STRUCTURED;
    }

    @objid ("5e504aa6-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(final boolean visible) {
        if (visible) {
            StyleKey key = getStyleKey(MetaKey.REPMODE);
            if (key != null)
                getParent().getDisplayedStyle().setProperty(key, RepresentationMode.STRUCTURED);
        }
    }

    @objid ("5e504aab-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(final GmNodeModel node) {
        final MObject childEl = node.getRelatedElement();
        return childEl == null || canUnmask(childEl);
    }

    @objid ("5e504ab4-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmSystemFreeZone.");
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

    @objid ("5e504aba-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmSystemFreeZone.", GmSystemFreeZone.MINOR_VERSION);
    }

    @objid ("5e51d11d-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(final GmNodeModel child) {
        super.addChild(child);
        fireVisibilityChanged();
    }

    @objid ("5e51d124-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(final GmNodeModel child, final int index) {
        super.addChild(child, index);
        fireVisibilityChanged();
    }

    @objid ("5e51d12d-55b7-11e2-877f-002564c97630")
    @Override
    public void removeChild(final GmNodeModel child) {
        super.removeChild(child);
        fireVisibilityChanged();
    }

    @objid ("5e51d134-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("5e51d139-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
