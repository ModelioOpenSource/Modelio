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

package org.modelio.diagram.editor.activity.elements.objectflow;

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
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link ObjectFlow}.
 * 
 * @author sbe
 */
@objid ("2ac92dda-55b6-11e2-877f-002564c97630")
public class GmObjectFlow extends GmLink {
    @objid ("2ac92dde-55b6-11e2-877f-002564c97630")
    private ObjectFlow element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2ac92de3-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2ac92de6-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("635b2f72-bccd-42ca-9978-11151c4f6133")
    public static final String ROLE_GUARD = GmActivityEdgeGuard.ROLE_DEFAULT;

    @objid ("57cea507-d572-4e9b-a7dd-b61f27e62cf1")
    public static final String ROLE_WEIGHT = GmActivityEdgeWeight.ROLE_DEFAULT;

    @objid ("399d9745-0633-46a6-ab86-7e18035bdda4")
    public static final String ROLE_INFOFLOW_ARROW = GmInformationFlowArrow.DEFAULT_ROLE;

    @objid ("5c8b2081-fe76-45c2-9211-16a3b90e728b")
    public static final String ROLE_INFOFLOW_GROUP = GmInfoFlowsGroup.DEFAULT_ROLE;

    @objid ("310a59ae-58a2-11e2-9574-002564c97630")
    private static final GmObjectFlowStyleKeys styleKeyProvider = new GmObjectFlowStyleKeys();

    /**
     * Initialize a control flow graphic model.
     * @param diagram The owning diagram
     * @param objectflow The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("2ac92de8-55b6-11e2-877f-002564c97630")
    public GmObjectFlow(IGmDiagram diagram, ObjectFlow objectflow, MRef ref) {
        super(diagram, ref);
        
        this.element = objectflow;
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, new GmDefaultModelElementLabel(diagram, ref));
        addExtension(ExtensionLocation.TargetNW, GmObjectFlow.ROLE_GUARD, new GmActivityEdgeGuard(diagram, ref));
        addExtension(ExtensionLocation.TargetSE, GmObjectFlow.ROLE_WEIGHT, new GmActivityEdgeWeight(diagram, ref));
        
        // Information flows
        GmFractionalConnectionLocator constraint = new GmFractionalConnectionLocator(0.75, 0, -10);
        addExtension(new GmInfoFlowsGroup(diagram, ref), GmObjectFlow.ROLE_INFOFLOW_GROUP, constraint);
        constraint = new GmFractionalConnectionLocator(0.75, 0, 0, true);
        addExtension(new GmInformationFlowArrow(diagram, ref), GmObjectFlow.ROLE_INFOFLOW_ARROW, constraint);
    }

    /**
     * For deserialization only.
     */
    @objid ("2ac92df4-55b6-11e2-877f-002564c97630")
    public GmObjectFlow() {
        // Nothing to do.
    }

    @objid ("2ac92df7-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getSource();
    }

    @objid ("2ac92dfe-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2ac92e05-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2ac92e0c-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmObjectFlow.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("2acab47c-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmObjectFlow.styleKeyProvider.getStyleKeys();
    }

    @objid ("2acab485-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getTarget();
    }

    @objid ("2acab48c-55b6-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (ObjectFlow) resolveRef(this.getRepresentedRef());
    }

    @objid ("2acab492-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmObjectFlow.", GmObjectFlow.MINOR_VERSION);
    }

    @objid ("2acab498-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmObjectFlow.MAJOR_VERSION;
    }

    @objid ("919aec8d-eded-466f-82d7-f1bb20f261b3")
    @Override
    protected void read_GmLinkV0_roles() {
        for (GmNodeModel n : getExtensions()) {
            if (n instanceof GmDefaultModelElementLabel) {
                n.setRoleInComposition(IGmLink.ROLE_MAIN_LABEL);
            } else if (n instanceof GmActivityEdgeGuard) {
                n.setRoleInComposition(GmObjectFlow.ROLE_GUARD);
            } else if (n instanceof GmActivityEdgeWeight) {
                n.setRoleInComposition(GmObjectFlow.ROLE_WEIGHT);
            } else if (n instanceof GmInfoFlowsGroup) {
                n.setRoleInComposition(GmObjectFlow.ROLE_INFOFLOW_GROUP);
            } else if (n instanceof GmInformationFlowArrow) {
                n.setRoleInComposition(GmObjectFlow.ROLE_INFOFLOW_ARROW);
            }
        
        }
    }

}
