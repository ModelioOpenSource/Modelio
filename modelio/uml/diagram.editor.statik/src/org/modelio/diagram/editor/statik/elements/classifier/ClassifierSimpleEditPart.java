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

package org.modelio.diagram.editor.statik.elements.classifier;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.SWT;
import org.modelio.diagram.editor.statik.elements.innerclass.GmInnerClass;
import org.modelio.diagram.editor.statik.elements.innerclass.GmInnerClassesZone;
import org.modelio.diagram.editor.statik.elements.namespacinglink.GmCompositionLink;
import org.modelio.diagram.editor.statik.elements.namespacinglink.redraw.RedrawCompositionLinkEditPolicy;
import org.modelio.diagram.editor.statik.elements.naryassoc.AcceptNAssocEditPolicy;
import org.modelio.diagram.editor.statik.elements.packaze.SimpleModeOwnedElementCreationEditPolicy;
import org.modelio.diagram.elements.common.resizablegroup.GmResizableGroup;
import org.modelio.diagram.elements.common.simple.NonSelectableSimpleEditPart;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.ui.CoreFontRegistry;

/**
 * Overloading of the {@link NonSelectableSimpleEditPart} to handle satellite children transfer back into body when
 * leaving simple mode.
 * 
 * @author fpoyer
 */
@objid ("3433a6d7-55b7-11e2-877f-002564c97630")
public class ClassifierSimpleEditPart extends NonSelectableSimpleEditPart {
    @objid ("3433a6db-55b7-11e2-877f-002564c97630")
     static final int DEFAULT_HEIGHT = 50;

    @objid ("3433a6dd-55b7-11e2-877f-002564c97630")
     static final int DEFAULT_WIDTH = 100;

    /**
     * Used in {@link #refreshFromStyle(IFigure, IStyle)} to avoid unnecessary nested calls.
     */
    @objid ("3433a6df-55b7-11e2-877f-002564c97630")
    private boolean alreadyRefreshing = false;

    @objid ("3433a6e1-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        // Shield against unavoidable nested calls (caused by the deletion of composition links).
        if (!this.alreadyRefreshing) {
            this.alreadyRefreshing = true;
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
        
                // Switch the font to Italic when the classifier is Abstract
                if (model.getRelatedElement() instanceof Classifier) {
                    final Classifier classifier = (Classifier) model.getRelatedElement();
                    if (classifier.isIsAbstract()) {
                        IPenOptionsSupport headerFigure = (IPenOptionsSupport) aFigure;
                        if (headerFigure.getTextFont() != null) {
                            headerFigure.setTextFont(CoreFontRegistry.getModifiedFont(headerFigure.getTextFont(), SWT.ITALIC, 1));
                        }
                    }
                }
            }
            this.alreadyRefreshing = false;
        }
    }

    @objid ("3433a6ea-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // Add specific policy to handle requests to redraw composition links.
        installEditPolicy("RedrawCompositionLinkEditPolicy", new RedrawCompositionLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeOwnedElementCreationEditPolicy());
        
        // FIXME : Some Gm that don't represent a Classifier use this edit part
        // only to have the separation line between the zones.
        GmCompositeNode model = (GmCompositeNode) getModel();
        if (model.getRelatedElement() instanceof Classifier) {
            installEditPolicy("N-ary assoc", new AcceptNAssocEditPolicy(true));
            installEditPolicy(ModelElementDropRequest.TYPE, new ClassifierElementDropEditPolicy());
        }
    }

}
