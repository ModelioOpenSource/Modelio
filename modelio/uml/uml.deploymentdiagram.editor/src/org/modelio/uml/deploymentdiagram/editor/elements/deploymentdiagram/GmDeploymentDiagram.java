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
package org.modelio.uml.deploymentdiagram.editor.elements.deploymentdiagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a ActivityDiagram.
 */
@objid ("97250c3a-55b6-11e2-877f-002564c97630")
public class GmDeploymentDiagram extends GmAbstractDiagram {
    @objid ("97250c40-55b6-11e2-877f-002564c97630")
    private DeploymentDiagram element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("972692bb-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("972692be-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("43539bf3-5beb-11e2-9e33-00137282c51b")
    private static final GmDeploymentDiagramStyleKeys STYLEKEYS = new GmDeploymentDiagramStyleKeys();

    /**
     * Initialize the diagram.
     * @param manager The model manager
     * @param theDeploymentDiagram the displayed diagram.
     * @param diagramRef the reference of the displayed diagram. Must reference a {@link IStaticDiagram}.
     */
    @objid ("972692c0-55b6-11e2-877f-002564c97630")
    public  GmDeploymentDiagram(IModelManager manager, DeploymentDiagram theDeploymentDiagram, MRef diagramRef) {
        super(manager, diagramRef);
        this.element = theDeploymentDiagram;
        
    }

    @objid ("972692cf-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("972692d7-55b6-11e2-877f-002564c97630")
    @Override
    public boolean doCanUnmask(MObject el) {
        return true;
    }

    @objid ("972692df-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    @objid ("972692e9-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("972692f0-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmDeploymentDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("972692fa-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmDeploymentDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("9728195a-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDeploymentDiagram.");
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

    @objid ("97281960-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Nothing to do.
    }

    @objid ("97281963-55b6-11e2-877f-002564c97630")
    @Override
    public DeploymentDiagram getRepresentedElement() {
        return this.element;
    }

    @objid ("9728196a-55b6-11e2-877f-002564c97630")
    @Override
    public DeploymentDiagram getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("97281971-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmDeploymentDiagram.", GmDeploymentDiagram.MINOR_VERSION);
        
    }

    @objid ("97281977-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (DeploymentDiagram) this.resolveRef(this.getRepresentedRef());
        
    }

    @objid ("9728197c-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmDeploymentDiagram.MAJOR_VERSION;
    }

    @objid ("1f5b5526-6e0c-4320-8313-59ace6399fb4")
    @Override
    public String getFactoryIdentifier() {
        return DeploymentDiagram.MNAME;
    }

    @objid ("524fcec4-0878-4c97-baa9-584c9254a7cf")
    @Override
    public boolean canUnmaskGenericElements() {
        return true;
    }

}
