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
package org.modelio.diagram.elements.core.link.rake;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.path.OrthoConnectionHelper;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmLinkRake;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Service that refresh the rake state of a link edit part from the {@link IGmLinkObject model} {@link IGmPath path} .
 * <p>
 * It is implemented as an edit policies and must be installed as such with {@link #ROLE} role.
 * It expect :<ul>
 * <li> the edit part to implement {@link PropertyChangeListener} : it will be fired with {@link IGmObject#PROPERTY_LAYOUTDATA} events
 * <li> the edit part to implement{@link ConnectionEditPart}
 * <li> the model to implement {@link IGmLinkObject}
 * </ul>
 * @author cma
 * @since 5.1.0
 */
@objid ("6d888a06-8be6-414d-b89f-00d62b673557")
public class RakeRefreshEditPolicy extends GraphicalEditPolicy implements PropertyChangeListener {
    /**
     * The role to install this policy with.
     */
    @objid ("88a8a44c-8bd1-4bca-b197-f03db284bd76")
    public static final String ROLE = "Refresh rake from model";

    @objid ("e3e83fb4-cf25-4e4d-bd65-024e32f46b5d")
    private IGmLinkRake currentTargetRake;

    @objid ("6bf04c34-2432-47a4-b912-9d93b4ec8b27")
    private IGmLinkRake currentSourceRake;

    @objid ("bbce46f9-4197-4594-8670-2641734c549f")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String propName = evt.getPropertyName();
        
        switch (propName) {
        case IGmObject.PROPERTY_LAYOUTDATA:
            // Link layout (bendpoints) update
            refreshRakes();
            refreshAnchorsFromRakes();
            //refreshVisuals();
            break;
        case IGmLinkRake.PROP:
        case GmLink.PROP_SOURCE_GM:
        case GmLink.PROP_TARGET_GM:
            boolean r1 = refreshRakes();
            boolean r2 = refreshAnchorsFromRakes();
            if (r1 || r2) {
                refreshVisuals();
            }
            break;
        default:
            // nothing
        }
        
    }

    @objid ("f6edaed6-5f60-4d09-98b2-e8c176a9658e")
    private void refreshVisuals() {
        PropertyChangeEvent evt = new PropertyChangeEvent(getModel(), IGmObject.PROPERTY_LAYOUTDATA, null, getModel().getLayoutData());
        PropertyChangeListener l = (PropertyChangeListener) getHost();
        l.propertyChange(evt);
        
    }

    @objid ("fc77a678-d58c-4f61-95c1-b233f4e55a90")
    @Override
    public void activate() {
        super.activate();
        
        final IGmLinkObject gmLink = getModel();
        gmLink.addPropertyChangeListener(this);
        
    }

    @objid ("ad71161a-3ef0-4f9e-add6-e03a30448a56")
    @Override
    public void deactivate() {
        getModel().removePropertyChangeListener(this);
        removeRakeListeners();
        
        super.deactivate();
        
    }

    @objid ("f294e5c7-5016-4313-8799-242594766569")
    private boolean refreshAnchorsFromRakes() {
        final IGmLinkObject gmLink = getModel();
        final IGmPath gmPath = gmLink.getPath();
        boolean changed = false;
        
        if (this.currentSourceRake != null) {
            Object sourceSharedAnchor = this.currentSourceRake.getSharedAnchor();
            if (! Objects.equals(gmPath.getSourceAnchor(), sourceSharedAnchor)) {
                gmPath.setSourceAnchor(sourceSharedAnchor);
                changed = true;
            }
        }
        
        if (this.currentTargetRake != null) {
            Object targetSharedAnchor = this.currentTargetRake.getSharedAnchor();
            if (! Objects.equals(gmPath.getTargetAnchor(), targetSharedAnchor)) {
                gmPath.setTargetAnchor(targetSharedAnchor);
                changed = true;
            }
        }
        return changed;
    }

    @objid ("431f19fa-7bad-4423-bf40-ade4b1eabbea")
    private IGmLinkObject getModel() {
        return (IGmLinkObject) this.getHost().getModel();
    }

    @objid ("da6b0e88-73e8-4cad-b492-910d7d56b945")
    private void removeRakeFromPath(final IGmPath gmPath) {
        OrthoConnectionHelper connectionPath = new OrthoConnectionHelper(getConnectionFigure(), (ConnectionEditPart) getHost());
        Object pathData = connectionPath.getModelPathData();
        
        gmPath.setRouterKind(ConnectionRouterId.ORTHOGONAL);
        gmPath.setSourceRake(null);
        gmPath.setTargetRake(null);
        gmPath.setPathData(pathData);
        
    }

    @objid ("471072f6-6b45-4d1c-a36e-c80b2e9e67b6")
    private Connection getConnectionFigure() {
        return (Connection) this.getHostFigure();
    }

    @objid ("b3dbbd5b-bf14-4fce-9551-a8508dec55f8")
    private void removeRakeListeners() {
        if (this.currentSourceRake != null)
            this.currentSourceRake.removeListener(this);
        if (this.currentTargetRake != null)
            this.currentTargetRake.removeListener(this);
        
    }

    @objid ("aa024670-8ca4-4598-ac5d-00a7aeb6235f")
    private boolean refreshRakes() {
        final IGmLinkObject gmLink = getModel();
        final IGmPath gmPath = gmLink.getPath();
        final IGmLinkRake newSourceRake = gmPath.getSourceRake();
        final IGmLinkRake newTargetRake = gmPath.getTargetRake();
        boolean refreshNeeded = false;
        
        if (this.currentSourceRake != newSourceRake) {
            if (this.currentSourceRake != null) {
                this.currentSourceRake.removeListener(this);
            }
            if (newSourceRake != null) {
                newSourceRake.addListener(this);
                gmPath.setSourceAnchor(newSourceRake.getSharedAnchor());
            } else {
                removeRakeFromPath(gmPath);
            }
            this.currentSourceRake = newSourceRake;
            refreshNeeded = true;
        }
        
        if (this.currentTargetRake != newTargetRake) {
            if (this.currentTargetRake != null) {
                this.currentTargetRake.removeListener(this);
            }
            if (newTargetRake != null) {
                newTargetRake.addListener(this);
                gmPath.setTargetAnchor(newTargetRake.getSharedAnchor());
            } else {
                removeRakeFromPath(gmPath);
            }
            this.currentTargetRake = newTargetRake;
            refreshNeeded = true;
        }
        return refreshNeeded;
    }

}
