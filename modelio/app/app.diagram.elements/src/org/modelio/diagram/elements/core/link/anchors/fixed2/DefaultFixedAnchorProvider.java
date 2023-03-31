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
package org.modelio.diagram.elements.core.link.anchors.fixed2;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.adefault.DefaultAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.ellipse.EllipseAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed.ConfigurableFixedAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed.FixedNodeAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.FixedNodeAnchorProvider2;

@objid ("0420f82a-b2fc-4c67-9d46-094c850fc422")
public class DefaultFixedAnchorProvider {
    @objid ("35818cfc-533a-4c66-b549-f6ed84888538")
    private static final FixedNodeAnchorProvider2 SHARED = createDefault();

    @objid ("821e1db6-691a-45b2-9cb7-1a3fcebd5a39")
    private static final FixedNodeAnchorProvider2 ONE_EACH_SIDE = createOnePerSide();

    @objid ("09a6c29a-18c1-4597-90b2-e7c18682a0d6")
    private static final FixedNodeAnchorProvider2 ELLIPSE = createEllipse();

    /**
     * No instance
     */
    @objid ("778fa208-eb62-4602-aa3c-dc796e4cb6d2")
    private  DefaultFixedAnchorProvider() {
        
    }

    /**
     * @return a new anchor provider for ellipse figures.
     */
    @objid ("7544aa03-b3b7-489f-999c-9583bd792c7e")
    protected static FixedNodeAnchorProvider2 createEllipse() {
        return new FixedNodeAnchorProvider2(new EllipseAnchorFactory("ellipse"));
    }

    /**
     * @param ep an ellipse figure edit part.
     * @return a suitable anchor provider for ellipse figures.
     */
    @objid ("a2f46380-1196-4b51-945d-089d21e3e1c2")
    public static FixedNodeAnchorProvider2 ellipseFor(GraphicalEditPart ep) {
        return ELLIPSE;
    }

    /**
     * Get a default fixed anchor provider for the given node figure.
     * <p>
     * This method may create a new anchor provider or reuse an existing provider.
     * @param ep a node figure.
     * @return a suitable anchor provider.
     */
    @objid ("1b0820df-50b1-44a0-82af-2f06ed8e0366")
    public static FixedNodeAnchorProvider2 defaultFor(GraphicalEditPart ep) {
        return SHARED;
    }

    /**
     * Get a fixed anchor provider that generates one anchor at the middle of each face.
     * @param ep a node figure.
     * @return a suitable anchor provider.
     */
    @objid ("d74b4e98-ce17-45e7-afd1-312db369ae4f")
    public static FixedNodeAnchorProvider2 onePerSideFor(GraphicalEditPart ep) {
        return ONE_EACH_SIDE;
    }

    /**
     * Create a default fixed anchor provider.
     * @return a new anchor provider.
     */
    @objid ("f3345228-15f6-4df0-981b-a3b7b5ec46eb")
    public static FixedNodeAnchorProvider2 createDefault() {
        return new FixedNodeAnchorProvider2(new DefaultAnchorFactory());
    }

    @objid ("4ae908e9-ce83-44ac-b4e9-6058100b177f")
    private static FixedNodeAnchorProvider2 createOnePerSide() {
        return new FixedNodeAnchorProvider2(
                new ConfigurableFixedAnchorFactory("one", new FixedNodeAnchorLocator("one"))
                .setAnchorCount(1, 1));
        
    }

}
