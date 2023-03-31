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
    @objid ("71db8270-156e-450c-a80a-ed301679f00d")
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

    @objid ("1590797d-eaa0-423f-90c6-7150507fee48")
    @Override
    public ILinkPathEditor applyChangeBoundsRequest(ChangeBoundsRequest request, boolean isSimulation) {
        return this;
    }

    @objid ("027a2fce-8343-47f0-b927-d65cabb89f8d")
    private static class DummyLinkPathEditor extends AbstractLinkPathEditor implements ILinkPathEditorFactory {
        @objid ("7b67da9e-7d7c-493c-a776-3f4d1afaaf60")
        private ConnectionEditPart ep;

        @objid ("e25b6e83-dafc-4508-ab00-2610fa44e3a9")
        private ConnectionState state;

        @objid ("e8019df9-b15e-46d9-a6f8-a0119c6bc139")
        @Override
        public ConnectionState getState() {
            return this.state;
        }

        @objid ("206ecb77-629e-486b-82c3-f65b84210714")
        @Override
        public ILinkPathEditor from(ConnectionEditPart connectionEditPart) {
            this.ep  = connectionEditPart;
            this.state = backupConnection();
            return this;
        }

        @objid ("47b68f01-9418-4e7c-9b0e-40972751af3f")
        @Override
        public ILinkPathEditor from(ConnectionEditPart connectionEditPart, ConnectionState state) {
            this.ep  = connectionEditPart;
            this.state = state;
            return this;
        }

        @objid ("159f1289-9059-42cb-94e0-a3a4ec5475cf")
        @Override
        protected ConnectionEditPart getConnectionEditPart() {
            return this.ep;
        }

        /**
         * Make no copy, return itself.
         */
        @objid ("f9cbc2ca-5f7a-4f2e-8dfa-9660a429077e")
        @Override
        public ILinkPathEditor createFrozenStateCopy() {
            DiagramElements.LOG.warning(new UnsupportedOperationException());
            return this;
        }

    }

}
