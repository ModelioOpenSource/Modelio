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
    @objid ("18175d8f-1e93-4a78-be21-f828da292745")
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

    @objid ("e51c7c40-1dbe-4c86-8f5c-7bb684300ba9")
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
    @objid ("6efeadc9-55e9-472b-b467-4df3d8dc4092")
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
    @objid ("59420690-14c5-40f3-88cb-9ea10f72522a")
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
    @objid ("37e97922-b62f-41f0-9d7e-20ba51d5ac05")
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
    @objid ("9c1820fe-f386-4870-ac6a-e4b7acd381b0")
    public static class ConnectionRouters {
        @objid ("21859ff1-09a3-4140-ba00-86e471ae0333")
        ConnectionRouter display;

        @objid ("0b749a0a-9600-4d85-b7ae-770c41034b57")
        ConnectionRouter edition;

        @objid ("90707a71-ce0c-4c57-9e18-efcfe2ce88a2")
        ConnectionRouter creation;

        @objid ("73430830-a13d-40dd-a60e-2d645abbe56d")
        public  ConnectionRouters(ConnectionRouter display, ConnectionRouter edition, ConnectionRouter creation) {
            super();
            this.display = display;
            this.edition = edition;
            this.creation = creation;
            
        }

        @objid ("e34be5ed-a1f6-4520-bd9a-b13a210a8680")
        public  ConnectionRouters(ConnectionRouter router) {
            super();
            this.display = router;
            this.edition = router;
            this.creation = router;
            
        }

        @objid ("c0ae3007-1abf-4b14-aa13-979f260ab44e")
        public ConnectionRouters withDisplay(ConnectionRouter router) {
            this.display = router;
            return this;
        }

        @objid ("b36e14e9-875a-47e6-a694-f6a50b64c9a9")
        public ConnectionRouters withEdition(ConnectionRouter router) {
            this.edition = router;
            return this;
        }

        @objid ("80d93e3c-642c-4d9b-800f-c18630df8154")
        public ConnectionRouters withCreation(ConnectionRouter router) {
            this.creation = router;
            return this;
        }

        @objid ("0e954fcb-e68a-4406-8baa-22675227fc35")
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
    @objid ("46fab740-60e5-41c1-8df3-d457b23ce89a")
    public static class Builder {
        @objid ("c1c08ec3-5389-4b09-bbf3-44c1d4c1137f")
        private IConnectionHelperFactory connectionHelperFactory;

        @objid ("b8539703-dd18-4cfc-80db-bf061eaf4c5d")
        private IRouterDependentEditPolicyFactory editPoliciesFactory;

        @objid ("8600c6b5-38ce-46a8-b9e4-d52e8d6d37c6")
        private final Map<org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId, ConnectionRouters> routers = new EnumMap<>(StyleKey.ConnectionRouterId.class);

        @objid ("2a687212-4f51-4d19-88bf-539299c87efb")
        private final Map<ConnectionRouterId, ILinkPathEditorFactory> linkPathEditors = new EnumMap<>(StyleKey.ConnectionRouterId.class);

        /**
         * initialize with Modelio <= 5.0 diagrams defaults.
         * @return this instance
         */
        @objid ("5c9c31ea-a83f-42ac-80d5-e7ade4444925")
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
        @objid ("687ff74b-4755-4f0e-84f7-ecff53748463")
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
        @objid ("dcd86423-a899-40be-a1b8-26d6de7f3537")
        public ConnectionRoutingServices build() {
            return new ConnectionRoutingServices(
                    this.routers,
                    this.connectionHelperFactory,
                    this.editPoliciesFactory,
                    this.linkPathEditors);
            
        }

    }

}
