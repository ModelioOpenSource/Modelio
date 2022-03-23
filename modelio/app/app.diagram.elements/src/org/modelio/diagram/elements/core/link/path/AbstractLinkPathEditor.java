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
package org.modelio.diagram.elements.core.link.path;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Abstract implementation of {@link ILinkPathEditor} that does nothing.
 */
@objid ("dc047ebc-d508-4f9c-92bd-192f24791c2e")
public abstract class AbstractLinkPathEditor implements ILinkPathEditor {
    /**
     * Dummy implementation of {@link ILinkPathEditorFactory} that does nothing.
     */
    @objid ("aee2414f-4191-4cf9-b2bf-c0eaa04dbd78")
    public static final ILinkPathEditorFactory DUMMY = new DummyLinkPathEditor();

    @objid ("7673b20e-3f70-442f-afd1-311e2fbbc692")
    @Override
    public ILinkPathEditor moveSegment(int pointIndex, Point newLocation) {
        return this;
    }

    @objid ("9f3aad79-1ff8-4638-8715-23e620c6da25")
    @Override
    public ILinkPathEditor moveBendPoint(int pointIndex, Point newLocation) {
        return this;
    }

    @objid ("aa4a7aa4-039b-4906-aef5-39bc3ba71b59")
    @Override
    public ILinkPathEditor setSourceAnchor(ConnectionAnchor newAnchor) {
        getState().setSourceAnchor(newAnchor);
        return this;
    }

    /**
     * @return the edited {@link ConnectionEditPart} .
     */
    @objid ("aa2b1d25-956b-4088-b1e6-466e59d9c206")
    protected abstract ConnectionEditPart getConnectionEditPart();

    @objid ("5c8b2264-82b9-4a94-bf04-c6fbeec475f6")
    @Override
    public ILinkPathEditor setTargetAnchor(ConnectionAnchor newAnchor) {
        getState().setTargetAnchor(newAnchor);
        return this;
    }

    @objid ("7068e3a2-ba19-4338-a7b8-0705aa7cd322")
    @Override
    public ConnectionState backupConnection() {
        Connection connectionFig = (Connection) getConnectionEditPart().getFigure();
        return new ConnectionState().init(connectionFig);
    }

    @objid ("46ff6b46-2acd-46d7-b84d-186c6163bd18")
    @Override
    public void restoreConnection(ConnectionState backup) {
        Connection connectionFig = (Connection) getConnectionEditPart().getFigure();
        backup.applyTo(connectionFig);
        
    }

    @objid ("c3ca2c68-9e6a-4b60-8650-f1c3d6d2ed9b")
    @Override
    public ILinkPathEditor applyChangeBoundsRequest(ChangeBoundsRequest request, boolean isSimulation) {
        return this;
    }

    @objid ("b86f248b-f22b-4298-8946-405137cf12c8")
    private static class DummyLinkPathEditor extends AbstractLinkPathEditor implements ILinkPathEditorFactory {
        @objid ("7cf0c775-837a-4b94-b5b0-19a08715b180")
        private ConnectionEditPart ep;

        @objid ("18a51789-481e-4b9d-af96-ed38fd995b5c")
        private ConnectionState state;

        @objid ("284bb5a7-f787-4e36-a573-71492024034d")
        @Override
        public ConnectionState getState() {
            return this.state;
        }

        @objid ("e1ee8446-4dbb-49a8-b993-726feb8bee24")
        @Override
        public ILinkPathEditor from(ConnectionEditPart connectionEditPart) {
            this.ep  = connectionEditPart;
            this.state = backupConnection();
            return this;
        }

        @objid ("f068a106-87e5-45b8-9239-2c66281e778a")
        @Override
        public ILinkPathEditor from(ConnectionEditPart connectionEditPart, ConnectionState state) {
            this.ep  = connectionEditPart;
            this.state = state;
            return this;
        }

        @objid ("1b9d37f1-c7bd-4059-aedd-aa98efe4e3e6")
        @Override
        protected ConnectionEditPart getConnectionEditPart() {
            return this.ep;
        }

        /**
         * Make no copy, return itself.
         */
        @objid ("c494ee3f-103f-46e3-bf19-506d43ff199a")
        @Override
        public ILinkPathEditor createFrozenStateCopy() {
            DiagramElements.LOG.warning(new UnsupportedOperationException());
            return this;
        }

    }

}
