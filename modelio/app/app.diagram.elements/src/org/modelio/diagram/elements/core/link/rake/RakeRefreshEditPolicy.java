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
@objid ("b21a91df-18e1-4a4e-a5da-0ad22d7d8926")
public class RakeRefreshEditPolicy extends GraphicalEditPolicy implements PropertyChangeListener {
    /**
     * The role to install this policy with.
     */
    @objid ("53e21ed4-323d-4777-9611-49adccc192e5")
    public static final String ROLE = "Refresh rake from model";

    @objid ("91879619-ef99-4cc4-875f-f3bcf1519d0e")
    private IGmLinkRake currentTargetRake;

    @objid ("8899b045-3523-4378-9acf-badba9b692e6")
    private IGmLinkRake currentSourceRake;

    @objid ("13d65b84-0046-40d5-b207-28adbda5d2a6")
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

    @objid ("36c9e87c-bacd-48d9-af3f-51732e8ce378")
    private void refreshVisuals() {
        PropertyChangeEvent evt = new PropertyChangeEvent(getModel(), IGmObject.PROPERTY_LAYOUTDATA, null, getModel().getLayoutData());
        PropertyChangeListener l = (PropertyChangeListener) getHost();
        l.propertyChange(evt);
        
    }

    @objid ("da577758-9a08-4095-ab19-6e6adfad0409")
    @Override
    public void activate() {
        super.activate();
        
        final IGmLinkObject gmLink = getModel();
        gmLink.addPropertyChangeListener(this);
        
    }

    @objid ("ed419069-bf1b-417b-b012-1410d0202052")
    @Override
    public void deactivate() {
        getModel().removePropertyChangeListener(this);
        removeRakeListeners();
        
        super.deactivate();
        
    }

    @objid ("0d47401d-3d85-47e7-b2a9-deff105c3567")
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

    @objid ("e2b280f3-3f4e-4f2c-9689-03042d1bab7c")
    private IGmLinkObject getModel() {
        return (IGmLinkObject) this.getHost().getModel();
    }

    @objid ("3a86bb35-9d8d-4d5f-b58e-c01f649b72f2")
    private void removeRakeFromPath(final IGmPath gmPath) {
        OrthoConnectionHelper connectionPath = new OrthoConnectionHelper(getConnectionFigure(), (ConnectionEditPart) getHost());
        Object pathData = connectionPath.getModelPathData();
        
        gmPath.setRouterKind(ConnectionRouterId.ORTHOGONAL);
        gmPath.setSourceRake(null);
        gmPath.setTargetRake(null);
        gmPath.setPathData(pathData);
        
    }

    @objid ("e80dad98-0a66-4283-8bba-9e082260f235")
    private Connection getConnectionFigure() {
        return (Connection) this.getHostFigure();
    }

    @objid ("bfbcf7f4-5b20-41a1-8a2f-620f0a282460")
    private void removeRakeListeners() {
        if (this.currentSourceRake != null)
            this.currentSourceRake.removeListener(this);
        if (this.currentTargetRake != null)
            this.currentTargetRake.removeListener(this);
        
    }

    @objid ("7c3bcc4d-e0cd-4dcc-accb-f1cfbaff1ea2")
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
