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

package org.modelio.diagram.elements.common.ghostnode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultDeleteNodeEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.vcore.session.UnknownMetaclassException;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Universal edit part for "ghost" mode of any ModelElement It provides a ghost rectangle figure with a centered label
 * 
 * @author fpoyer
 */
@objid ("7e48acb6-1dec-11e2-8cad-001ec947c8cc")
public class GhostNodeEditPart extends AbstractNodeEditPart {
    @objid ("7e48acb8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        // create the figure
        final GhostNodeFigure aFigure = new GhostNodeFigure();
        
        // set style independent properties
        aFigure.setSize(100, 50);
        
        // set style dependent properties
        // this.refreshFromStyle(aFigure, this.getModelStyle());
        
        // return the figure
        return aFigure;
    }

    @objid ("7e48acbf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
    }

    @objid ("7e48acc6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DefaultDeleteNodeEditPolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
    }

    @objid ("7e48acc9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final IGmModelRelated model = getModel();
        final GhostNodeFigure aFigure = (GhostNodeFigure) getFigure();
        
        String cause = "";
        MObject el;
        try {
            el = model.getDiagram().getModelManager().getModelingSession().getModel().findByRef(model.getRepresentedRef());
        } catch (UnknownMetaclassException e) {
            el = null;
        }
        if (el == null) {
            cause = "missing ";
        } else if (el.isDeleted()) {
            // note : isShell() and isDeleted() return true for dead objects.
            cause = "deleted ";
        } else if (el.isShell()) {
            cause = "shell ";
        } else {
            cause = "broken ";
        }
        
        IFigure parent = aFigure.getParent();
        if (parent != null) {
            parent.setConstraint(aFigure, ((GmAbstractObject) model).getLayoutData());
        }
        
        aFigure.setMetaclassName("<<" + cause + model.getGhostMetaclass() + ">>");
        aFigure.setName(model.getGhostLabel());
        aFigure.setId(model.getGhostId());
    }

    @objid ("7e4b0f0f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<Object> getModelChildren() {
        return Collections.emptyList();
    }

    @objid ("7e4b0f16-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<IGmLink> getModelSourceConnections() {
        return this.getModelSourceConnections(getModel());
    }

    @objid ("7e4b0f1d-1dec-11e2-8cad-001ec947c8cc")
    private List<IGmLink> getModelSourceConnections(GmNodeModel model) {
        List<IGmLink> ret = new ArrayList<>();
        ret.addAll(model.getStartingLinks());
        if (model instanceof GmCompositeNode) {
            for (GmNodeModel child : ((GmCompositeNode) model).getChildren()) {
                ret.addAll(this.getModelSourceConnections(child));
            }
        }
        return ret;
    }

    @objid ("7e4b0f24-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<IGmLink> getModelTargetConnections() {
        return this.getModelTargetConnections(getModel());
    }

    @objid ("7e4b0f2b-1dec-11e2-8cad-001ec947c8cc")
    private List<IGmLink> getModelTargetConnections(GmNodeModel model) {
        List<IGmLink> ret = new ArrayList<>();
        ret.addAll(model.getEndingLinks());
        if (model instanceof GmCompositeNode) {
            for (GmNodeModel child : ((GmCompositeNode) model).getChildren()) {
                ret.addAll(this.getModelTargetConnections(child));
            }
        }
        return ret;
    }

    @objid ("7e4b0f32-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getAdapter(final Class adapter) {
        // cannot do anything with a ghost
        return null;
    }

    @objid ("7e4b0f39-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void activate() {
        super.activate();
        if (getModel() instanceof GmCompositeNode) {
            for (GmNodeModel child : ((GmCompositeNode) getModel()).getChildren()) {
                registerAsPropertyChangeListenerOf(child);
            }
        }
    }

    @objid ("7e4b0f3c-1dec-11e2-8cad-001ec947c8cc")
    private void registerAsPropertyChangeListenerOf(GmNodeModel model) {
        model.addPropertyChangeListener(this);
        if (model instanceof GmCompositeNode) {
            for (GmNodeModel child : ((GmCompositeNode) model).getChildren()) {
                registerAsPropertyChangeListenerOf(child);
            }
        }
    }

    @objid ("7e4b0f3f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        super.deactivate();
        if (getModel() instanceof GmCompositeNode) {
            for (GmNodeModel child : ((GmCompositeNode) getModel()).getChildren()) {
                unregisterAsPropertyChangeListenerOf(child);
            }
        }
    }

    @objid ("7e4b0f42-1dec-11e2-8cad-001ec947c8cc")
    private void unregisterAsPropertyChangeListenerOf(GmNodeModel model) {
        model.removePropertyChangeListener(this);
        if (model instanceof GmCompositeNode) {
            for (GmNodeModel child : ((GmCompositeNode) model).getChildren()) {
                unregisterAsPropertyChangeListenerOf(child);
            }
        }
    }

    /**
     * The representation mode needs to be switched only if the related element comes back.
     */
    @objid ("1b802f5d-38ae-4f3e-8dee-8411e8f192cf")
    @Override
    protected boolean needsRepresentationModeSwitch() {
        return (isRelatedElementValid());
    }

}
