/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.classifier;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.editor.statik.elements.innerclass.GmInnerClass;
import org.modelio.diagram.editor.statik.elements.innerclass.GmInnerClassesZone;
import org.modelio.diagram.editor.statik.elements.namespacinglink.GmCompositionLink;
import org.modelio.diagram.editor.statik.elements.namespacinglink.redraw.RedrawCompositionLinkEditPolicy;
import org.modelio.diagram.editor.statik.elements.naryassoc.AcceptNAssocEditPolicy;
import org.modelio.diagram.editor.statik.elements.packaze.SimpleModeOwnedElementCreationEditPolicy;
import org.modelio.diagram.editor.statik.elements.policies.SmartGeneralizationEditPolicy;
import org.modelio.diagram.elements.common.image.NonSelectableLabelledImageEditPart;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.common.resizablegroup.GmResizableGroup;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.policies.SimpleModeDeferringCreateNodePolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.Classifier;

/**
 * EditPart for classifiers in image mode.
 * <p>
 * Accepts n-ary associations.
 * 
 * @author cmarin
 */
@objid ("3436b406-55b7-11e2-877f-002564c97630")
public class ImageClassifierEditPart extends NonSelectableLabelledImageEditPart {
    @objid ("3436b40a-55b7-11e2-877f-002564c97630")
    private static final int DEFAULT_HEIGHT = 50;

    @objid ("3436b40c-55b7-11e2-877f-002564c97630")
    private static final int DEFAULT_WIDTH = 100;

    @objid ("3436b40e-55b7-11e2-877f-002564c97630")
    private static final int MARGIN = 20;

    @objid ("3436b410-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new SmartGeneralizationEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        installEditPolicy("constraint", new ConstraintLinkEditPolicy(false));
        
        GmNodeModel model = getModel();
        if (model.getRelatedElement() instanceof Classifier) {
            installEditPolicy("N-ary assoc", new AcceptNAssocEditPolicy(true));
            installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeDeferringCreateNodePolicy());
            installEditPolicy(ModelElementDropRequest.TYPE, new ClassifierElementDropEditPolicy());
        } else {
        
            installEditPolicy(ModelElementDropRequest.TYPE, new DefaultElementDropEditPolicy());
        }
        
        // Add specific policy to handle requests to redraw composition links.
        installEditPolicy("RedrawCompositionLinkEditPolicy", new RedrawCompositionLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeOwnedElementCreationEditPolicy());
    }

    @objid ("3436b413-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        GmCompositeNode model = (GmCompositeNode) getModel();
        if (model.getRepresentationMode() == RepresentationMode.STRUCTURED) {
            GmNodeModel firstChild = model.getFirstChild("");
            if (firstChild instanceof GmResizableGroup) {
                GmInnerClass inner = (GmInnerClass) ((GmResizableGroup) firstChild).getFirstChild("Inner");
                if (inner != null) {
                    for (GmNodeModel innerChild : inner.getChildren()) {
                        if (innerChild instanceof GmInnerClassesZone) {
                            // Start by cleaning all children that might have been created by the auto-unmask behaviors.
                            GmInnerClassesZone innerZone = (GmInnerClassesZone) innerChild;
                            for (GmNodeModel zoneChild : innerZone.getChildren()) {
                                zoneChild.delete();
                            }
                            // Delete composition links.
                            //List<IGmLink> linksToDelete = );
                            for (IGmLink link : new ArrayList<>(model.getStartingLinks())) {
                                if (link instanceof GmCompositionLink) {
                                    // This will provoke a nested call to #refreshFromStyle (see SimpleEditPart#propertyChange) that cannot be avoided.
                                    link.delete();
                                }
                            }
                            // new representation mode is not SIMPLE, put back body content into body BEFORE the switch.
                            for (GmNodeModel child : model.getParentNode()
                                                          .getChildren(GmCompositeNode.CONTENT_AS_SATELLITE_ROLE)) {
                                model.getParentNode().removeChild(child);
                                child.setRoleInComposition("");
                                innerZone.addChild(child);
                            }
                        }
                    }
                }
            }
            
        }
        
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
    }

}
