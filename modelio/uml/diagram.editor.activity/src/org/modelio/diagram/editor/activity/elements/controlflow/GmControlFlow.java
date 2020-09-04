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

package org.modelio.diagram.editor.activity.elements.controlflow;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.elements.activityedgelabels.GmActivityEdgeGuard;
import org.modelio.diagram.editor.activity.elements.activityedgelabels.GmActivityEdgeWeight;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInfoFlowsGroup;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInformationFlowArrow;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link ControlFlow}.
 * 
 * @author sbe
 */
@objid ("2a1e47ea-55b6-11e2-877f-002564c97630")
public class GmControlFlow extends GmLink {
    @objid ("2a1e47ee-55b6-11e2-877f-002564c97630")
    private ControlFlow element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2a1e47f3-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2a1e47f6-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d47c01e9-6fe0-4a79-8e65-fbec39afe8f2")
    public static final String ROLE_GUARD = GmActivityEdgeGuard.ROLE_DEFAULT;

    @objid ("ee865eb0-bb3e-47cb-ac57-b86e4641539a")
    public static final String ROLE_WEIGHT = GmActivityEdgeWeight.ROLE_DEFAULT;

    @objid ("626bfd05-cc0a-486d-bfdd-cc63dcb6d9fb")
    public static final String ROLE_INFOFLOW_ARROW = GmInformationFlowArrow.DEFAULT_ROLE;

    @objid ("9a1661c8-b272-4f1f-8bc8-e41e7299243b")
    public static final String ROLE_INFOFLOW_GROUP = GmInfoFlowsGroup.DEFAULT_ROLE;

    @objid ("30556fb2-58a2-11e2-9574-002564c97630")
    private static final GmControlStyleKeys styleKeyProvider = new GmControlStyleKeys();

    /**
     * Initialize a control flow graphic model.
     * 
     * @param diagram The owning diagram
     * @param controlflow The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("2a1e47f8-55b6-11e2-877f-002564c97630")
    public GmControlFlow(IGmDiagram diagram, ControlFlow controlflow, MRef ref) {
        super(diagram, ref);
        
        this.element = controlflow;
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, new GmDefaultModelElementLabel(diagram, ref));
        addExtension(ExtensionLocation.TargetNW, GmControlFlow.ROLE_GUARD, new GmActivityEdgeGuard(diagram, ref));
        addExtension(ExtensionLocation.TargetSE, GmControlFlow.ROLE_WEIGHT, new GmActivityEdgeWeight(diagram, ref));
        
        // Information flows
        GmFractionalConnectionLocator constraint = new GmFractionalConnectionLocator(0.75, 0, -10);
        addExtension(new GmInfoFlowsGroup(diagram, ref), GmControlFlow.ROLE_INFOFLOW_GROUP, constraint);
        
        constraint = new GmFractionalConnectionLocator(0.75, 0, 0, true);
        addExtension(new GmInformationFlowArrow(diagram, ref), GmControlFlow.ROLE_INFOFLOW_ARROW, constraint);
    }

    /**
     * For deserialization only.
     */
    @objid ("2a1e4804-55b6-11e2-877f-002564c97630")
    public GmControlFlow() {
        // Nothing to do.
    }

    @objid ("2a1e4807-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmControlFlow.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("2a1fce82-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmControlFlow.styleKeyProvider.getStyleKeys();
    }

    @objid ("2a1fce8b-55b6-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (ControlFlow) resolveRef(this.getRepresentedRef());
    }

    @objid ("2a1fce91-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getSource();
    }

    @objid ("2a1fce98-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getTarget();
    }

    @objid ("2a1fce9f-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2a1fcea6-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2a1fcead-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmControlFlow.", GmControlFlow.MINOR_VERSION);
    }

    @objid ("2a1fceb3-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmControlFlow.MAJOR_VERSION;
    }

    @objid ("cb6843d7-ddfa-4319-9d10-dd0fe375f3aa")
    @Override
    protected void read_GmLinkV0_roles() {
        for (GmNodeModel n : getExtensions()) {
            if (n instanceof GmDefaultModelElementLabel) {
                n.setRoleInComposition(IGmLink.ROLE_MAIN_LABEL);
            } else if (n instanceof GmActivityEdgeGuard) {
                n.setRoleInComposition(GmControlFlow.ROLE_GUARD);
            } else if (n instanceof GmActivityEdgeWeight) {
                n.setRoleInComposition(GmControlFlow.ROLE_WEIGHT);
            } else if (n instanceof GmInfoFlowsGroup) {
                n.setRoleInComposition(GmControlFlow.ROLE_INFOFLOW_GROUP);
            } else if (n instanceof GmInformationFlowArrow) {
                n.setRoleInComposition(GmControlFlow.ROLE_INFOFLOW_ARROW);
            }
        
        }
    }

}
