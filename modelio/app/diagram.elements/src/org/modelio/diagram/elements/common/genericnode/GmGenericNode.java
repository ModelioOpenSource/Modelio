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

package org.modelio.diagram.elements.common.genericnode;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Generic GM that can be used to display any {@link ModelElement} in a diagram.
 */
@objid ("0b86785c-8eef-4f3e-a4b4-2f73bf0ca413")
public class GmGenericNode extends GmCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("6443f70f-222b-4d9c-a21c-79eb97152cd3")
    private static final int MINOR_VERSION = 0;

    @objid ("e17776da-c4c0-432d-8387-f4abb7a04b98")
    private static final int MAJOR_VERSION = 0;

    @objid ("2ac4e015-d31d-41d5-afc0-bc150b9f1c31")
    private static final String HEADER = "Header";

    @objid ("be8e8ff1-c073-43bf-9d66-e9a4f533e6d5")
    private static final GmGenericNodeStyleKeys styleKeyProvider = new GmGenericNodeStyleKeys();

    @objid ("f6e84604-1733-49aa-b750-9de0d5c77d0e")
    private ModelElement elt;

    @objid ("200622e9-2564-4200-ae49-f1e2e5c3f5a1")
    private GmModelElementHeader header;

    /**
     * Constructor for deserialization only.
     */
    @objid ("f4f77505-9dde-4b21-a5f1-5034131a2c6e")
    public GmGenericNode() {
        super();
    }

    /**
     * Initializes the node.
     * @param diagram The diagram owning the node.
     * @param elt the represented model element.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("a98f17e2-1bde-4447-875b-7a1ec7684a52")
    public GmGenericNode(IGmDiagram diagram, ModelElement elt, MRef relatedRef) {
        super(diagram, relatedRef);
        this.elt = elt;
        
        this.header = new GmDefaultModelElementHeader(diagram, relatedRef);
        this.header.setRoleInComposition(GmGenericNode.HEADER);
        this.header.setShowMetaclassIcon(true);
        
        super.addChild(this.header);
    }

    @objid ("37e4ab6d-7193-473c-844c-2960faa30519")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmGenericNode.styleKeyProvider.getStyleKeys();
    }

    @objid ("64311695-3816-4865-9a14-63ad53540b6d")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmGenericNode.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("4e592a43-19f4-4f71-a9d8-62292d54900b")
    @Override
    public MObject getRepresentedElement() {
        return this.elt;
    }

    @objid ("e8228916-e62c-4f9a-bfdb-d50e67c5b1b6")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("0aa18259-1836-4cda-beac-e5a386fcccd9")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(IGmObject.PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
    }

    @objid ("6e884843-e6ec-4b88-b82e-7b26938561c6")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return null;
    }

    @objid ("f7ea2a7d-16dd-4957-b100-47b78971733c")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("69e60b73-2200-40af-a370-2e7bf64b482d")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("57a8cb6a-76a9-458c-935a-7659ed627e36")
    @Override
    public int getMajorVersion() {
        return GmGenericNode.MAJOR_VERSION;
    }

    @objid ("01d462f9-1de0-426c-a478-e8780cdefcff")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmGenericNode.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : readVersion + " version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
    }

    @objid ("a1c9f5df-8483-4316-9158-175b4922119c")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmGenericNode.", GmGenericNode.MINOR_VERSION);
    }

    @objid ("98f42de9-f0a9-4619-81e5-9e7afe4cd6f2")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.elt = (ModelElement) resolveRef(getRepresentedRef());
        this.header = (GmModelElementHeader) getFirstChild(GmGenericNode.HEADER);
    }

    @objid ("45aa8165-b2bd-486a-ac96-b5cbb9588776")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return GenericNodeSymbolViewModel.create(getPersistedStyle(), this);
    }

}
