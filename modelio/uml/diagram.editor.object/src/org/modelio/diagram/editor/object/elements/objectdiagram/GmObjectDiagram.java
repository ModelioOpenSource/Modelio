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

package org.modelio.diagram.editor.object.elements.objectdiagram;

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
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a ActivityDiagram.
 */
@objid ("9d60337b-55b6-11e2-877f-002564c97630")
public class GmObjectDiagram extends GmAbstractDiagram {
    @objid ("9d603381-55b6-11e2-877f-002564c97630")
    private StaticDiagram element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("9d61ba1b-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("9d61ba1e-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("9d60337f-55b6-11e2-877f-002564c97630")
    private static final GmObjectDiagramStyleKeys STYLEKEYS = new GmObjectDiagramStyleKeys();

    /**
     * Initialize the diagram.
     * 
     * @param manager The model manager
     * @param theObjectDiagram the displayed diagram.
     * @param diagramRef the reference of the displayed diagram. Must reference a {@link StaticDiagram}.
     */
    @objid ("9d61ba20-55b6-11e2-877f-002564c97630")
    public GmObjectDiagram(IModelManager manager, StaticDiagram theObjectDiagram, MRef diagramRef) {
        super(manager, diagramRef);
        this.element = theObjectDiagram;
    }

    @objid ("9d61ba2f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (type == AttributeLink.class) {
            return !(this.element.getOrigin() instanceof Instance);
        }
        return true;
    }

    @objid ("9d61ba37-55b6-11e2-877f-002564c97630")
    @Override
    public boolean doCanUnmask(MObject el) {
        if (el instanceof AbstractDiagram) {
            return false;
        } else {
            return true;
        }
    }

    @objid ("9d61ba3f-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    @objid ("9d61ba49-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("9d61ba50-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmObjectDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("9d61ba5a-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmObjectDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("9d6340bb-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmObjectDiagram.");
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

    @objid ("9d6340c1-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Nothing to do.
    }

    @objid ("9d6340c4-55b6-11e2-877f-002564c97630")
    @Override
    public StaticDiagram getRepresentedElement() {
        return this.element;
    }

    @objid ("9d6340cb-55b6-11e2-877f-002564c97630")
    @Override
    public StaticDiagram getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("9d6340d2-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmObjectDiagram.", GmObjectDiagram.MINOR_VERSION);
    }

    @objid ("9d6340d8-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (StaticDiagram) resolveRef(getRepresentedRef());
    }

    @objid ("9d6340dd-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmObjectDiagram.MAJOR_VERSION;
    }

    @objid ("30e5dda3-0672-4838-b5b4-418d0c28e4bf")
    @Override
    public String getFactoryIdentifier() {
        return ObjectDiagram.MNAME;
    }

    @objid ("f11766ea-7fcf-49f6-b894-b0a15108b847")
    @Override
    public boolean canUnmaskGenericElements() {
        return true;
    }

}
