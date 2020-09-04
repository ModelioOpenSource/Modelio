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

package org.modelio.diagram.editor.sequence.elements.lifeline.header;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Container needed to add the image mode label.
 * 
 * @author fpoyer
 */
@objid ("d9439f5e-55b6-11e2-877f-002564c97630")
public class GmLifelineHeaderContainer extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d94525da-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d94525dd-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("50426adb-55c2-11e2-9337-002564c97630")
    private GmDefaultModelElementLabel imageModeLabel;

    /**
     * Deserialisation c'tor only.
     */
    @objid ("d94525df-55b6-11e2-877f-002564c97630")
    public GmLifelineHeaderContainer() {
        super();
    }

    /**
     * C'tor.
     * @param diagram the diagram in which this gm is created.
     * @param relatedRef a reference to the represented element.
     */
    @objid ("d94525e2-55b6-11e2-877f-002564c97630")
    public GmLifelineHeaderContainer(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.addChild(new GmLifelineHeader(diagram, relatedRef));
        this.imageModeLabel = new GmDefaultModelElementLabel(diagram, relatedRef);
        this.addChild(this.imageModeLabel);
    }

    /**
     * Get the stereotype image to display.
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("d94525ed-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        Lifeline lifeline = (Lifeline) getRelatedElement();
        Instance representedInstance = lifeline.getRepresented();
        
        if (representedInstance != null) {
            NameSpace base = representedInstance.getBase();
            if (base != null) {
                return ElementImageService.getImage(base);
            } else {
                return ElementImageService.getImage(representedInstance);
            }
        } else {
            return ElementImageService.getImage(lifeline);
        }
    }

    @objid ("d94525f2-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        return null;
    }

    @objid ("d94525fd-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("d9452606-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("d945260f-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(getStyleKey(MetaKey.REPMODE));
    }

    @objid ("d9452616-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("d946ac79-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLifelineHeaderContainer.");
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

    @objid ("d946ac80-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
        case USER_IMAGE:
        case IMAGE:
            ret = new ArrayList<>(1);
            ret.add(this.imageModeLabel);
            break;
        case SIMPLE:
        case STRUCTURED:
        default:
            ret = super.getVisibleChildren();
            // Remove the header used for image mode.
            ret.remove(this.imageModeLabel);
            break;
        
        }
        return ret;
    }

    @objid ("d946ac89-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmLifelineHeaderContainer.", MINOR_VERSION);
    }

    @objid ("d946ac8f-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.imageModeLabel = (GmDefaultModelElementLabel) getChildren().get(1);
    }

    @objid ("d946ac95-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
