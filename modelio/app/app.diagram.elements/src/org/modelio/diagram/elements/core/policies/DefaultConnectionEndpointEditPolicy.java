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
package org.modelio.diagram.elements.core.policies;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.handles.ConnectionEndpointHandle;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.anchors.AnchorFigureFactory;

/**
 * Modelio implementation for {@link ConnectionEndpointEditPolicy}.
 * <p>
 * Handles read only connections.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("ed2e66d1-8922-408b-b814-8e07b0dc69ad")
public class DefaultConnectionEndpointEditPolicy extends ConnectionEndpointEditPolicy {
    @objid ("35e370a9-8603-4691-b75b-b80e944dd9e1")
    @Override
    protected List createSelectionHandles() {
        List<ConnectionEndpointHandle> list = new ArrayList<>(2);
        ConnectionEditPart host = (ConnectionEditPart) getHost();
        list.add(new BigSquareHandle(host, ConnectionLocator.SOURCE));
        list.add(new BigSquareHandle(host, ConnectionLocator.TARGET));
        
        // Handles read only connections.
        SelectionHandlesBuilder.disableHandlesIfReadOnly(host, list);
        return list;
    }

    @objid ("e16dc2ae-9c43-4ac6-a943-0b0af0c28de9")
    @Deprecated
    private static class __CircleHandle extends ConnectionEndpointHandle {
        @objid ("06d9a83d-dbdb-44d4-812f-0542549f2f30")
        private final IFigure circle;

        @objid ("9f3c5639-26cd-476d-8713-f1f49214e46b")
        public  __CircleHandle(ConnectionEditPart owner, int endPoint) {
            super(owner, endPoint);
            this.circle = AnchorFigureFactory.createHandleFigure(owner);
            setPreferredSize(this.circle.getPreferredSize());
            
        }

        @objid ("ede1e5da-35f5-4491-8b65-ba0fec18045e")
        @Override
        protected void init() {
            
        }

        @objid ("41cf2f35-29c7-482a-9737-0e122afc1026")
        @Override
        public void paintFigure(Graphics g) {
            this.circle.setBounds(getBounds());
            this.circle.paint(g);
            
        }

    }

    @objid ("309444ca-6420-4637-83c6-d7d2926b86f5")
    private static class BigSquareHandle extends ConnectionEndpointHandle {
        @objid ("ed67b056-b948-491e-872c-26df9d3eb5f4")
        public  BigSquareHandle(ConnectionEditPart owner, int endPoint) {
            super(owner, endPoint);
            setPreferredSize(11,11);
            
        }

        @objid ("a3c82fac-cb33-4886-9797-89d79132f0ff")
        @Override
        public void paintFigure(Graphics g) {
            Rectangle r = getBounds().getCopy().shrink(1, 1);
            
            Color b = getFillColor();
            Color a = getBorderColor();
            
            g.setBackgroundColor(a);
            g.fillRectangle(r);
            
            g.setForegroundColor(b);
            g.drawRectangle(r);
            
            g.setBackgroundColor(b);
            r.shrink(2, 2).resize(1, 1);
            g.fillRectangle(r);
            
        }

    }

}
