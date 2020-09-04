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

package org.modelio.diagram.editor.activity.elements.partition.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.factories.StandardEditPartFactory;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * <p>
 * Specialisation of the default model element header: uses a "special" style that returns a darker shade of the partition background colour.
 * </p>
 * <p>
 * Also this class is needed so that the {@link StandardEditPartFactory} instantiate the correct EditPart to have the correct selection behaviours (very specific to partition: only header is "click-able" but clicking on it selects the whole partition).
 * </p>
 * 
 * @author fpoyer
 */
@objid ("2b0636d9-55b6-11e2-877f-002564c97630")
public class GmPartitionHeader extends GmDefaultModelElementHeader {
    @objid ("2b0636dd-55b6-11e2-877f-002564c97630")
    private boolean vertical = true;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2b065de9-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2b065dec-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Returns a specialization of the standard {@link ProxyStyle} that returns a darker shade for the background color.
     */
    @objid ("2b0684fa-55b6-11e2-877f-002564c97630")
    @Override
    protected IStyle createStyle(IGmDiagram gmAbstractDiagram) {
        return new GmPartitionHeaderStyle(gmAbstractDiagram.getPersistedStyle());
    }

    /**
     * C'tor without args for deserialization.
     */
    @objid ("2b06ac0d-55b6-11e2-877f-002564c97630")
    public GmPartitionHeader() {
        // Nothing to do.
    }

    /**
     * C'tor.
     * 
     * @param diagram the owning diagram.
     * @param relatedRef represented element reference, must not be null.
     */
    @objid ("2b06d31a-55b6-11e2-877f-002564c97630")
    public GmPartitionHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("2b06fa2f-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPartitionHeader.");
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

    @objid ("2b07213d-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty("isVertical", this.vertical ? Boolean.TRUE : Boolean.FALSE);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPartitionHeader.", GmPartitionHeader.MINOR_VERSION);
    }

    /**
     * @return true if the header is vertical, false for horizontal.
     */
    @objid ("2b07484c-55b6-11e2-877f-002564c97630")
    public boolean isVertical() {
        return this.vertical;
    }

    /**
     * Set orientation of the header
     * 
     * @param vertical true for vertical, false for horizontal.
     */
    @objid ("2b076f5a-55b6-11e2-877f-002564c97630")
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    @objid ("2b076f5e-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        ActivityPartition partition = (ActivityPartition) getRelatedElement();
        if (partition != null && partition.isValid()) {
            setShowMetaclassIcon(partition.getRepresented() != null);
        } else {
            setShowMetaclassIcon(false);
        }
        
        super.refreshFromObModel();
    }

    @objid ("2b07966b-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        ActivityPartition partition = (ActivityPartition) getRelatedElement();
        ModelElement represented = partition.getRepresented();
        if (represented != null) {
            return ElementImageService.getIcon(represented);
        } else {
            return ElementImageService.getIcon(partition);
        }
    }

    @objid ("2b07bd79-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        ActivityPartition partition = (ActivityPartition) getRelatedElement();
        return PartitionSymbolProvider.computeSimpleLabel(partition);
    }

    @objid ("2b07e48c-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.vertical = ((Boolean) in.readProperty("isVertical")).booleanValue();
    }

    @objid ("2b080b9c-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmPartitionHeader.MAJOR_VERSION;
    }

    /**
     * Specialization of {@link ProxyStyle} that returns a darker shade for the background color.
     * </p>
     * <p>
     * This class <strong>MUST</strong> be static or deserialization will fail.
     * </p>
     * 
     * @author fpoyer
     */
    @objid ("2b0832aa-55b6-11e2-877f-002564c97630")
    public static class GmPartitionHeaderStyle extends ProxyStyle {
        /**
         * Returns a darker shade for the background color.
         */
        @objid ("2b0859bb-55b6-11e2-877f-002564c97630")
        @Override
        public Color getColor(StyleKey propertyKey) {
            // if (propertyKey.equals(GmPartitionStructuredStyleKeys.FILLCOLOR)) {
            // final Color containerBgColor = super.getColor(propertyKey);
            // return FigureUtilities.darker(containerBgColor);
            // }
            return super.getColor(propertyKey);
        }

        /**
         * Empty c'tor needed for deserialization.
         */
        @objid ("2b0880cd-55b6-11e2-877f-002564c97630")
        public GmPartitionHeaderStyle() {
            super();
        }

        /**
         * C'tor.
         * 
         * @param cascadedStyle the style this style should cascade on.
         */
        @objid ("2b08a7d9-55b6-11e2-877f-002564c97630")
        public GmPartitionHeaderStyle(IStyle cascadedStyle) {
            super(cascadedStyle);
        }

    }

}
