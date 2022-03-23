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
package org.modelio.diagram.elements.core.link.ortho.edit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.MPrecisionPoint;
import org.modelio.diagram.elements.core.link.ortho.edit.AxisAccessor.OrientedAccessors;
import org.modelio.diagram.elements.core.link.path.AbstractLinkPathEditor;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditor;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditorFactory;

/**
 * Experimental orthogonal editor that uses intermediate {@link MoveBpReq requests} and {@link MoveBpCommand commands}.
 * 
 * @deprecated experimental, not finished, works badly. Use {@link AutoOrthoLinkPathEditor1}
 * @since 5.0.2
 */
@objid ("42635f21-78df-4caa-b7ed-1a5588a1e4bc")
@Deprecated
class AutoOrthoLinkPathEditor2 extends AbstractLinkPathEditor implements ILinkPathEditorFactory {
    @objid ("d0f8dc03-b1ef-405e-bc3d-a84148f96437")
    private final ConnectionEditor editor = new ConnectionEditor();

    @objid ("0ec271b8-b028-427b-abdd-b84a5f6398d6")
    private final ConnectionBpEditor bpEditor;

    @objid ("c0617061-691d-449b-9f62-963c488757f3")
    public static final AutoOrthoLinkPathEditor2 INSTANCE = new AutoOrthoLinkPathEditor2();

    @objid ("c7988a9d-1f6f-42c5-a4a3-961dc45a6692")
    private static final MPrecisionPoint TMP1 = new MPrecisionPoint();

    @objid ("e5c4fb1c-e34b-4479-bafc-1b117594d980")
    private static final MPrecisionPoint TMP2 = new MPrecisionPoint();

    @objid ("97629a23-722d-46cd-b93c-9151814ddfbc")
    private static final MPrecisionPoint TMP3 = new MPrecisionPoint();

    @objid ("c5d734a6-ea00-49c7-8253-72ff1751e499")
    private static final MPrecisionPoint TMP4 = new MPrecisionPoint();

    @objid ("c46da3f0-cf16-4e65-8804-3f3df78f0907")
    private static final MoveBpCommand CMD1 = new MoveBpCommand();

    @objid ("137b8c42-0cbb-46e8-afd6-6631ddb97cd3")
    private static final MoveBpCommand CMD2 = new MoveBpCommand();

    @objid ("c853d835-3d47-489f-8bc3-c05a33a2460f")
    private static final MoveBpCommand CMD3 = new MoveBpCommand();

    @objid ("7baf381b-422d-42b2-b053-b8d7ce353388")
    private static final MoveBpCommand CMD4 = new MoveBpCommand();

    @objid ("1a9b80f5-c0db-41a3-9a03-0d74787339f4")
    private static final MoveBpReq REQ1 = new MoveBpReq();

    @objid ("568ed836-d8c6-484d-ac46-530cf8543198")
    private static final MoveBpReq REQ2 = new MoveBpReq();

    @objid ("ed6ea6f7-f4ee-4b58-81ae-edef3cc70da9")
    private static final MoveBpReq REQ3 = new MoveBpReq();

    @objid ("710cd50d-8fa5-4754-8289-d0302eb756e4")
    private static final MoveBpReq REQ4 = new MoveBpReq();

    @objid ("9ff1a09c-c0ae-43a5-97d7-ad0bc07f539e")
    public  AutoOrthoLinkPathEditor2() {
        this.bpEditor = new ConnectionBpEditor().init(this.editor);
    }

    @objid ("38c2f957-51d6-4535-a7f1-271c98dcdb1c")
    public AutoOrthoLinkPathEditor2 forConnection(ConnectionEditPart editPart) {
        this.editor.init(editPart);
        return this;
    }

    @objid ("b5eae88d-b336-44e3-b087-39376764948f")
    @Override
    public ILinkPathEditor from(ConnectionEditPart connectionEditPart) {
        this.editor.init(connectionEditPart);
        return this;
    }

    @objid ("173c19fe-0982-4ead-abe8-9ef7661393fc")
    @Override
    public ILinkPathEditor from(ConnectionEditPart connectionEditPart, ConnectionState initState) {
        this.editor.init(connectionEditPart, initState);
        return this;
    }

    @objid ("2202496b-617f-442a-aa61-f5fa1865ec0d")
    @Override
    public ILinkPathEditor createFrozenStateCopy() {
        AutoOrthoLinkPathEditor2 ret = new AutoOrthoLinkPathEditor2();
        ConnectionState frozenState = this.editor.createFrozenState();
        ret.from(getConnectionEditPart(), frozenState);
        return ret;
    }

    @objid ("65a4f2d6-7351-4859-bf55-4d057bd0614f")
    @Override
    protected ConnectionEditPart getConnectionEditPart() {
        return this.editor.getConnectionEditPart();
    }

    @objid ("9d3ac545-8b96-426c-88c2-ff1bba5ae093")
    @Override
    public ConnectionState getState() {
        return this.editor.getView().getState();
    }

    @objid ("91cf3fa9-0ef3-4011-a3dd-49b9f4661e54")
    @Override
    public ILinkPathEditor moveBendPoint(int pointIndex, Point newLocation) {
        MPrecisionPoint previousPoint = this.editor.getView().getPoint(TMP1,  pointIndex - 1, true);
        MPrecisionPoint curPoint = this.editor.getView().getPoint(TMP2,  pointIndex, true);
        MPrecisionPoint nextPoint = this.editor.getView().getPoint(TMP3, pointIndex + 1, true);
        
        OrientedAccessors access1 = AxisAccessor.forSegment(previousPoint, curPoint);
        OrientedAccessors access2 = AxisAccessor.forSegment(curPoint, nextPoint);
        
        OrientedAccessors prevAccess = AxisAccessor.forOrientation(this.editor.getView().getOrientationFromPrevious(pointIndex-1));
        OrientedAccessors nextAccess = AxisAccessor.forOrientation(this.editor.getView().getOrientationToNext(pointIndex+1));
        
        // Previous point
        if (!previousPoint.isFixed()) {
            MoveBpReq req1 = REQ1
                    .setCheckPrevious(prevAccess.along)
                    .setCheckNext(null)
                    .setPointIndex(pointIndex-1)
                    .setLocation(previousPoint);
            access1.across.setCoord(req1.getLocation(), newLocation);
            MoveBpCommand command1 = this.bpEditor.getMovePointCommand(CMD1, req1);
            this.bpEditor.apply(command1);
        
            previousPoint = this.editor.getView().getPoint(TMP1,  pointIndex - 1, true);
        }
        
        // Next point
        if (!nextPoint.isFixed() ) {
            MoveBpReq req2 = REQ2
                    .setCheckPrevious(null)
                    .setCheckNext(nextAccess.along)
                    .setPointIndex(pointIndex+1)
                    .setLocation(nextPoint);
            access2.across.setCoord(req2.getLocation(), newLocation);
            MoveBpCommand command2 = this.bpEditor.getMovePointCommand(CMD2, req2);
            this.bpEditor.apply(command2);
        
            nextPoint = this.editor.getView().getPoint(TMP3, pointIndex + 1, true);
        }
        
        // Moved point.
        MoveBpReq req3 = REQ3
                .setCheckPrevious(access1.along)
                .setCheckNext(access2.along)
                .setPointIndex(pointIndex)
                .setLocation(newLocation);
        
        MoveBpCommand command3 = this.bpEditor.getMovePointCommand(CMD3, req3);
        this.bpEditor.apply(command3);
        
        this.bpEditor.insertOrthogonalBendPoints(pointIndex - 1, 2);
        return this;
    }

    @objid ("9733a1d5-32fa-45b7-b68f-7cdf2d3eba0f")
    @Override
    public ILinkPathEditor moveSegment(int pointIndex, Point newLocation) {
        ConnectionView view = this.editor.getView();
        
        MPrecisionPoint curPoint = view.getPoint(TMP1,  pointIndex, true);
        MPrecisionPoint nextPoint = view.getPoint(TMP2, pointIndex + 1, true);
        
        AxisAccessor segAxis = AxisAccessor.forSegment(curPoint, nextPoint).across;
        OrientedAccessors prevAccess = AxisAccessor.forOrientation(view.getOrientationFromPrevious(pointIndex));
        OrientedAccessors nextAccess = AxisAccessor.forOrientation(view.getOrientationToNext(pointIndex+1));
        
        MoveBpReq req1 = REQ1
                .setCheckPrevious(prevAccess.along)
                .setCheckNext(null)
                .setPointIndex(pointIndex)
                .setLocation(curPoint)
                .setManual(null);
        MoveBpReq req2 = REQ2
                .setCheckPrevious(null)
                .setCheckNext(nextAccess.along)
                .setPointIndex(pointIndex+1)
                .setLocation(nextPoint)
                .setManual(null);
        
        segAxis.setCoord(req1.getLocation(), newLocation);
        segAxis.setCoord(req2.getLocation(), newLocation);
        
        MoveBpCommand command1 = this.bpEditor.getMovePointCommand(CMD1, req1);
        MoveBpCommand command2 = this.bpEditor.getMovePointCommand(CMD2, req2);
        
        if (req1.isSatisfiedBy(command1) && req2.isSatisfiedBy(command2))  {
            this.bpEditor.apply(command1);
            this.bpEditor.apply(command2);
            return this;
        }
        
        
        MoveBpReq req3 = REQ3
                .setCheckPrevious(prevAccess.along)
                .setCheckNext(null)
                .setPointIndex(pointIndex)
                .setLocation(curPoint)
                .setManual(null);
        MoveBpReq req4 = REQ4
                .setCheckPrevious(null)
                .setCheckNext(nextAccess.along)
                .setPointIndex(pointIndex+1)
                .setLocation(nextPoint)
                .setManual(null);
        segAxis.setCoord(req3.getLocation(), command2.getNewLocation());
        segAxis.setCoord(req4.getLocation(), command1.getNewLocation());
        
        MoveBpCommand command3 = this.bpEditor.getMovePointCommand(CMD3, req3);
        MoveBpCommand command4 = this.bpEditor.getMovePointCommand(CMD4, req4);
        
        if (req3.isSatisfiedBy(command3)) {
            this.bpEditor.apply(command3);
            this.bpEditor.apply(command2);
        } else if (req4.isSatisfiedBy(command4)) {
            this.bpEditor.apply(command1);
            this.bpEditor.apply(command4);
        } else {
            this.bpEditor.apply(command1);
            this.bpEditor.apply(command2);
        }
        
        this.bpEditor.insertOrthogonalBendPoints(pointIndex, 2);
        return this;
    }

    @objid ("ee27b787-4eec-4802-8c78-4ffa5b72e514")
    @Override
    public ILinkPathEditor setSourceAnchor(ConnectionAnchor newAnchor) {
        ConnectionView view = this.editor.getView();
        Point startPoint = view.getPoint(TMP1,  0, true);
        Point endPoint = view.getPoint(TMP1,  1, true);
        
        AxisAccessor access = AxisAccessor.forSegment(startPoint, endPoint).across;
        
        view.getState().setSourceAnchor(newAnchor);
        
        // Move end point
        if (false) {
            access.setCoord(endPoint, newAnchor.getReferencePoint());
        
            if (!view.getState().getMPoints().isEmpty()) {
                // move first bend point
                view.getConnection().translateToRelative(endPoint);
                view.getState().getMPoints().get(0).setLocation(endPoint);
                this.editor.applyStateToConnection();
        
            } else {
                // straight line connection
                //editor.getView().getState().setTargetAnchor(requestTargetAnchor(connectionEditPart, endPoint, true));
                MoveBpReq req1 = REQ1
                        .setCheckPrevious(null)
                        .setCheckNext(null)
                        .setPointIndex(1)
                        .setLocation(endPoint);
        
                MoveBpCommand command1 = this.bpEditor.getMovePointCommand(CMD1, req1);
                this.bpEditor.apply(command1);
        
            }
        }
        
        this.bpEditor.insertOrthogonalBendPoints(0, 1);
        this.editor.fixWithRouter();
        return this;
    }

    @objid ("1009b25e-357c-496e-8b7f-c97d1fd162eb")
    @Override
    public ILinkPathEditor setTargetAnchor(ConnectionAnchor newAnchor) {
        ConnectionView view = this.editor.getView();
        int targetIndex = view.getTargetAnchorIndex();
        MPrecisionPoint endPoint = view.getPoint(TMP2,  targetIndex-1, true);
        
        view.getState().setTargetAnchor(newAnchor);
        
        if (false) {
            if (!view.getState().getMPoints().isEmpty()) {
                // move last bend point
                view.setPoint(targetIndex-1, endPoint);
            } else {
                // straight line connection : move source anchor to align with target
                //editor.getView().getState().setSourceAnchor(requestSourceAnchor(connectionEditPart, endPoint, true));
                MoveBpReq req1 = REQ1
                        .setCheckPrevious(null)
                        .setCheckNext(null)
                        .setPointIndex(0)
                        .setLocation(endPoint);
        
                MoveBpCommand command1 = this.bpEditor.getMovePointCommand(CMD1, req1);
                this.bpEditor.apply(command1);
            }
        }
        this.bpEditor.insertOrthogonalBendPoints(targetIndex-1, 1);
        this.editor.fixWithRouter();
        return this;
    }

    @objid ("e6764262-cc54-41f9-8f79-6ffcaffc1f8d")
    @Override
    public ILinkPathEditor applyChangeBoundsRequest(ChangeBoundsRequest request, boolean isSimulation) {
        // delegate to AutoOrthoLinkPathEditor1
        AutoOrthoLinkPathEditor1.INSTANCE.from(getConnectionEditPart(), getState()).applyChangeBoundsRequest(request, isSimulation);
        this.getState().init(AutoOrthoLinkPathEditor1.INSTANCE.getState());
        return this;
    }

}
