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

package org.modelio.diagram.editor.statik.elements.datatype;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.statik.elements.templatecontainer.GmTemplateContainer;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link DataType}.
 * 
 * @author cma
 */
@objid ("34bcfb2c-55b7-11e2-877f-002564c97630")
public class GmDataType extends GmTemplateContainer {
    @objid ("34bcfb2e-55b7-11e2-877f-002564c97630")
    private DataType element;

    /**
     * Current version of this Gm.
     */
    @objid ("34be819a-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("34be819d-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("34be819f-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("34bcfb31-55b7-11e2-877f-002564c97630")
     static final DataTypeStructuredStyleKeys STRUCTURED_KEYS = new DataTypeStructuredStyleKeys();

    @objid ("34bcfb33-55b7-11e2-877f-002564c97630")
     static final DataTypeSimpleStyleKeys SIMPLE_KEYS = new DataTypeSimpleStyleKeys();

    @objid ("34bcfb35-55b7-11e2-877f-002564c97630")
     static final DataTypeImageStyleKeys IMAGE_KEYS = new DataTypeImageStyleKeys();

    @objid ("8f121c05-a621-4be6-bdbf-a98937e06d97")
     static final DataTypeUserImageStyleKeys USERIMAGE_KEYS = new DataTypeUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the class is unmasked.
     * @param el the unmasked class.
     * @param ref a reference to the unmasked class.
     */
    @objid ("34be81a1-55b7-11e2-877f-002564c97630")
    public GmDataType(IGmDiagram diagram, DataType el, MRef ref) {
        super(diagram, new GmDataTypePrimaryNode(diagram, ref), ref);
        this.element = el;
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmDataType.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("34be81ad-55b7-11e2-877f-002564c97630")
    public GmDataType() {
        // Nothing specific to do.
    }

    @objid ("34be81b0-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (Port.class.isAssignableFrom(type));
    }

    @objid ("34be81b8-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (Port.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner().equals(this.element));
    }

    @objid ("34be81c0-55b7-11e2-877f-002564c97630")
    @Override
    public DataType getRepresentedElement() {
        return this.element;
    }

    @objid ("34be81c7-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = GmDataType.STRUCTURED_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = GmDataType.SIMPLE_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = GmDataType.IMAGE_KEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("34be81d1-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
            case IMAGE:
                return GmDataType.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmDataType.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmDataType.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmDataType.STRUCTURED_KEYS.getStyleKeys();
            default:
                return Collections.emptyList();
        }
    }

    @objid ("34c0083c-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmDataType.");
        switch (readVersion) {
            case 0: {
                read_0(in);
                break;
            }
            case 1: {
                read_1(in);
                break;
            }
            default: {
                assert (false) : "version number not covered!";
                // reading as last handled version: 1
                read_1(in);
                break;
            }
        }
    }

    @objid ("34c00842-55b7-11e2-877f-002564c97630")
    @Override
    public DataType getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("34c00849-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmDataType.", GmDataType.MINOR_VERSION);
    }

    @objid ("34c0084f-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (DataType) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmDataType.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("34c00854-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmDataType.MAJOR_VERSION;
    }

    @objid ("34c00859-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (DataType) resolveRef(getRepresentedRef());
    }

    @objid ("34c0085f-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmDataType.IMAGE_LABEL_ROLE);
                ret.remove(imageModeHeader);
                break;
            }
            case IMAGE:
            default: {
                break;
            }
        
            }
        }
        return ret;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("34c00868-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                                                || GmDataType.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("34c0087a-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("03bc6780-1599-4cb0-8b8a-6df94a4dd9bb")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmDataType.IMAGE_LABEL_ROLE);
    }

    @objid ("c6de2c52-2a95-4a46-af33-9a8a8875536e")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return DataTypeSymbolViewModelProvider.create(getPersistedStyle(), this);
    }

}
