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

package org.modelio.diagram.editor.communication.elements.communicationdiagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a CommunicationDiagram.
 */
@objid ("7a299d30-55b6-11e2-877f-002564c97630")
public class GmCommunicationDiagram extends GmAbstractDiagram {
    @objid ("7a299d36-55b6-11e2-877f-002564c97630")
    private CommunicationDiagram element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7a299d39-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("7a299d3c-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("0551512c-599a-11e2-ae45-002564c97630")
    private static final GmCommunicationDiagramStyleKeys STYLEKEYS = new GmCommunicationDiagramStyleKeys();

    /**
     * Initialize the diagram.
     * @param manager The model manager
     * @param theCommunicationDiagram the displayed diagram.
     * @param diagramRef the reference of the displayed diagram. Must reference a {@link CommunicationDiagram}.
     */
    @objid ("7a299d3e-55b6-11e2-877f-002564c97630")
    public GmCommunicationDiagram(IModelManager manager, CommunicationDiagram theCommunicationDiagram, MRef diagramRef) {
        super(manager, diagramRef);
        this.element = theCommunicationDiagram;
    }

    @objid ("7a299d4d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return CommunicationNode.class.isAssignableFrom(type);
    }

    @objid ("7a299d55-55b6-11e2-877f-002564c97630")
    @Override
    public boolean doCanUnmask(MObject el) {
        if ((el instanceof Dependency) ||
                (el instanceof Note) ||
                (el instanceof Constraint) ||
                (el instanceof Document)) {
            return true;
        }
        
        if ((el instanceof CommunicationChannel)) {
            return canUnmask(el.getCompositionOwner());
        }
        return (el instanceof CommunicationNode) && el.getCompositionOwner().equals(getRelatedElement().getOrigin());
    }

    @objid ("7a299d5d-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    @objid ("7a2b23c2-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("7a2b23c9-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmCommunicationDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("7a2b23d3-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmCommunicationDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("7a2b23dc-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmCommunicationDiagram.");
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

    @objid ("7a2b23e2-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Nothing to do.
    }

    @objid ("7a2b23e5-55b6-11e2-877f-002564c97630")
    @Override
    public CommunicationDiagram getRepresentedElement() {
        return this.element;
    }

    @objid ("7a2b23ec-55b6-11e2-877f-002564c97630")
    @Override
    public CommunicationDiagram getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("7a2b23f3-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmCommunicationDiagram.", GmCommunicationDiagram.MINOR_VERSION);
    }

    @objid ("7a2b23f9-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (CommunicationDiagram) resolveRef(getRepresentedRef());
    }

    @objid ("7a2caa59-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmCommunicationDiagram.MAJOR_VERSION;
    }

    @objid ("c2d7af7b-4afb-4224-974b-d05b26f5fa34")
    @Override
    public String getFactoryIdentifier() {
        return CommunicationDiagram.MNAME;
    }

    @objid ("d0979e03-6ee0-49f4-9a23-2f444d1cbbe7")
    @Override
    public boolean canUnmaskGenericElements() {
        return true;
    }

}
