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
package org.modelio.diagram.elements.core.link;

import java.util.EnumMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.OrthogonalRectifierRouter;
import org.modelio.diagram.elements.core.figures.routers.OrthogonalRouter;
import org.modelio.diagram.elements.core.link.ortho.edit.AutoOrthoLinkPathEditorFactory;
import org.modelio.diagram.elements.core.link.path.AbstractLinkPathEditor;
import org.modelio.diagram.elements.core.link.path.AutoConnectionHelperFactory;
import org.modelio.diagram.elements.core.link.path.ConnectionHelperFactory;
import org.modelio.diagram.elements.core.link.path.IConnectionHelperFactory;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditorFactory;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Connection router services, to access:
 * <ul>
 * <li>supported connection routers</li>
 * <li>the connection helper factory</li>
 * </ul>
 * <h2>Instantiation:</h2> Use {@link #builder()}, {@link Builder#withAutoOrthogonalDefaults()} then {@link Builder#build()} for new diagrams.
 */
@objid ("7fe52397-1dec-11e2-8cad-001ec947c8cc")
public class ConnectionRoutingServices {
    @objid ("7fe52399-1dec-11e2-8cad-001ec947c8cc")
    private static final long serialVersionUID = 1L;

    /**
     * Identifier used to store the router registry in the root edit part properties.
     */
    @objid ("91b8228a-1e83-11e2-8cad-001ec947c8cc")
    public static final String ID = "ConnectionRoutingServices";

    @objid ("abc20291-ee0e-4e0b-9ed6-9f4065dda566")
    private final Map<ConnectionRouterId, ConnectionRouters> routers;

    
    @mdl.prop
    @objid ("a0fd6082-7439-4526-8de4-c64b750cb047")
    private final IConnectionHelperFactory connectionHelperFactory;

    @mdl.propgetter
    public IConnectionHelperFactory getConnectionHelperFactory() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.connectionHelperFactory;
    }

    /**
     * Get the Edit policy factory for edit policies that depend on the current connection routing mode.
     */
    
    @mdl.prop
    @objid ("676c8f2b-e12d-4e75-ae04-82fb9fab0b6e")
    private final IRouterDependentEditPolicyFactory editPoliciesFactory;

    @mdl.propgetter
    public IRouterDependentEditPolicyFactory getEditPoliciesFactory() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.editPoliciesFactory;
    }

    @objid ("b4a74ee8-6b6a-46f8-8b37-adbadcb8d874")
    private final Map<ConnectionRouterId, ILinkPathEditorFactory> linkPathEditors;

    /**
     * @return a builder to create new {@link ConnectionRoutingServices}.
     * @since 5.1.0
     */
    @objid ("f181ee1b-a0b4-4591-9fca-626a2ef9f35e")
    public static Builder builder() {
        return new Builder();
    }

    @objid ("9ac8e5b4-e064-414f-9d75-eb46b169c049")
    public ILinkPathEditorFactory getLinkPathEditor(ConnectionRouterId id) {
        return this.linkPathEditors.getOrDefault(id, AbstractLinkPathEditor.DUMMY);
    }

    @objid ("4f75c865-123e-4afc-aa07-d980054838a7")
    private  ConnectionRoutingServices(Map<ConnectionRouterId, ConnectionRouters> routers, IConnectionHelperFactory connectionHelperFactory, IRouterDependentEditPolicyFactory policyFactory, Map<ConnectionRouterId, ILinkPathEditorFactory> linkPathEditors) {
        this.routers = routers;
        this.connectionHelperFactory = connectionHelperFactory;
        this.editPoliciesFactory = policyFactory;
        this.linkPathEditors = linkPathEditors;
        
    }

    /**
     * Get the edition connection router.
     * @param id the routing mode
     * @return the edition router
     * @deprecated Use {@link #getEditionRouter(ConnectionRouterId)}, {@link #getDisplayRouter(ConnectionRouterId)} or {@link #getCreationRouter(ConnectionRouterId)}.
     */
    @objid ("7fe5239e-1dec-11e2-8cad-001ec947c8cc")
    @Deprecated
    public ConnectionRouter getRouter(ConnectionRouterId id) {
        return getEditionRouter(id);
    }

    @objid ("51de57fb-5ba8-4c41-9a01-6119baf5f76b")
    private ConnectionRouters getRouters(ConnectionRouterId id) {
        final ConnectionRouters ret = this.routers.get(id);
        if (ret != null) {
            return ret;
        }
        
        throw new IllegalStateException(id + " routers are not registered");
        
    }

    /**
     * Get the display connection router.
     * @param id the routing mode
     * @return the router to use to display the Connection.
     */
    @objid ("b489398f-d35f-4080-b131-cd24157ff2e6")
    public ConnectionRouter getDisplayRouter(ConnectionRouterId id) {
        final ConnectionRouter ret = getRouters(id).display;
        if (ret != null) {
            return ret;
        }
        
        throw new IllegalStateException(id + " display router is not registered");
        
    }

    /**
     * Get the edition connection router.
     * @param id the routing mode
     * @return the router to use by link editors
     */
    @objid ("1ce3de65-e0da-43e4-b6b9-ae66059e2687")
    public ConnectionRouter getEditionRouter(ConnectionRouterId id) {
        final ConnectionRouter ret = getRouters(id).edition;
        if (ret != null) {
            return ret;
        }
        
        throw new IllegalStateException(id + " edition router is not registered");
        
    }

    /**
     * Get the link creation connection router.
     * @param id the routing mode
     * @return the router to use in creation mode
     */
    @objid ("1868c370-8307-42f3-b1a4-241da17bc4f6")
    public ConnectionRouter getCreationRouter(ConnectionRouterId id) {
        final ConnectionRouter ret = getRouters(id).creation;
        if (ret != null) {
            return ret;
        }
        
        throw new IllegalStateException(id + " creation router is not registered");
        
    }

    /**
     * Edit policy factory for edit policies that depend on the current connection routing mode.
     */
    @objid ("8c7801e6-535b-472e-b440-704528c0ab30")
    public interface IRouterDependentEditPolicyFactory {
        /**
         * @param mode the connection routing mode
         * @return the edit policy for bend points.
         */
        @objid ("1e35e0b0-5b41-4f36-a52b-6b78fc183955")
        EditPolicy createBendPointsPolicy(RoutingMode mode);

        /**
         * @param mode the connection routing mode
         * @return the edit policy for the anchors.
         */
        @objid ("a46c8c4e-f235-468e-b573-c6296adcbb65")
        EditPolicy createEndPointsPolicy(RoutingMode mode);
}
    

    /**
     * Connection routers for a single routing mode.
     * 
     * @since 5.1.0
     */
    @objid ("7239c281-11a8-45d2-ac76-64c6176ba4f2")
    public static class ConnectionRouters {
        @objid ("6b631511-069e-4729-bb8d-b18749dbe9df")
        ConnectionRouter display;

        @objid ("24590964-867c-4ab4-bb51-5e24e10cd947")
        ConnectionRouter edition;

        @objid ("92a53d95-0c5f-4be8-9c9c-36939f4a1848")
        ConnectionRouter creation;

        @objid ("b5ae35e2-1041-4926-b917-89c3c80353f0")
        public  ConnectionRouters(ConnectionRouter display, ConnectionRouter edition, ConnectionRouter creation) {
            super();
            this.display = display;
            this.edition = edition;
            this.creation = creation;
            
        }

        @objid ("68c4ad4d-26a9-4da5-875a-63e792f7dd56")
        public  ConnectionRouters(ConnectionRouter router) {
            super();
            this.display = router;
            this.edition = router;
            this.creation = router;
            
        }

        @objid ("d2965dcc-bf88-49f7-b159-ca172f9450d5")
        public ConnectionRouters withDisplay(ConnectionRouter router) {
            this.display = router;
            return this;
        }

        @objid ("5cfc2c9b-45cc-4d2d-96d6-f13f9fe9caaf")
        public ConnectionRouters withEdition(ConnectionRouter router) {
            this.edition = router;
            return this;
        }

        @objid ("54ff9e8b-3816-4dec-99ac-00aca53019de")
        public ConnectionRouters withCreation(ConnectionRouter router) {
            this.creation = router;
            return this;
        }

        @objid ("db8840c1-5fef-4656-aa4b-cc27e335749e")
        public ConnectionRouters withEditionCreation(ConnectionRouter router) {
            this.edition = router;
            this.creation = router;
            return this;
        }

    }

    /**
     * Builder of {@link ConnectionRoutingServices}.
     * 
     * @since 5.1.0
     */
    @objid ("a66afb87-be78-4a3c-b4b3-87b3445a9d9f")
    public static class Builder {
        @objid ("bb1df452-0550-4264-a63a-8d32ec215e3d")
        private IConnectionHelperFactory connectionHelperFactory;

        @objid ("80121c39-91a4-4db1-a00e-f1fea07c0b9c")
        private IRouterDependentEditPolicyFactory editPoliciesFactory;

        @objid ("f12dcd3f-fc42-484b-957e-2b63e963d708")
        private final Map<org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId, ConnectionRouters> routers = new EnumMap<>(StyleKey.ConnectionRouterId.class);

        @objid ("dc0a3d1d-89f5-4004-a9aa-11c42954a001")
        private final Map<ConnectionRouterId, ILinkPathEditorFactory> linkPathEditors = new EnumMap<>(StyleKey.ConnectionRouterId.class);

        /**
         * initialize with Modelio <= 5.0 diagrams defaults.
         * @return this instance
         */
        @objid ("a180502c-077c-4f0c-8f4a-4801675609b7")
        public Builder withLegacyDefaults() {
            this.routers.put(StyleKey.ConnectionRouterId.DIRECT, new ConnectionRouters(ConnectionRouter.NULL));
            this.routers.put(StyleKey.ConnectionRouterId.BENDPOINT, new ConnectionRouters(new BendpointConnectionRouter()));
            this.routers.put(StyleKey.ConnectionRouterId.ORTHOGONAL, new ConnectionRouters(new OrthogonalRouter()));
            this.connectionHelperFactory = new ConnectionHelperFactory();
            this.editPoliciesFactory = new SelectionHandlesEditPolicyFactory();
            return this;
        }

        /**
         * Initialize with defaults for diagrams that support new auto orthogonal routers.
         * @return this instance.
         */
        @objid ("61a549b2-3b41-41ec-b032-bef54f59269c")
        public Builder withAutoOrthogonalDefaults() {
            this.routers.put(StyleKey.ConnectionRouterId.DIRECT, new ConnectionRouters(ConnectionRouter.NULL));
            this.routers.put(StyleKey.ConnectionRouterId.BENDPOINT, new ConnectionRouters(new BendpointConnectionRouter()));
            this.routers.put(StyleKey.ConnectionRouterId.ORTHOGONAL, new ConnectionRouters(new AutoOrthogonalRouter())
                    .withDisplay(new OrthogonalRectifierRouter()));
            
            this.connectionHelperFactory = new AutoConnectionHelperFactory();
            this.editPoliciesFactory = new AutoOrthogonalRouterSelectionHandlesEditPolicyFactory();
            this.linkPathEditors.put(StyleKey.ConnectionRouterId.ORTHOGONAL, AutoOrthoLinkPathEditorFactory.get());
            return this;
        }

        /**
         * @return a new {@link ConnectionRoutingServices}.
         */
        @objid ("550c4a3a-dd96-456a-a2f3-7acc449b4739")
        public ConnectionRoutingServices build() {
            return new ConnectionRoutingServices(
                    this.routers,
                    this.connectionHelperFactory,
                    this.editPoliciesFactory,
                    this.linkPathEditors);
            
        }

    }

}
