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
import java.util.function.IntFunction;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.handles.ConnectionHandle;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.swt.graphics.Cursor;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Helper class to build {@link AbstractHandle selection handles}.
 * <p>
 * To be used in {@link org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#createSelectionHandles() SelectionHandlesEditPolicy.createSelectionHandles()}
 * implementations.
 * <p>
 * <h2>Sample usage:</h2>
 * <pre><code>
 * protected List<?> createSelectionHandles() {
 * return new SelectionHandlesBuilder((GraphicalEditPart) getHost())
 * .withResizeDirections(getResizeDirections())
 * .withDragAllowed(isDragAllowed())
 * .addResizeableHandles()
 * .getHandles();
 * }
 * </code></pre>
 * @author cma
 * @since 3.7
 */
@objid ("f405a776-7459-4eb1-b948-2a3e9c516373")
public class SelectionHandlesBuilder {
    @objid ("327cd603-05ee-4550-a02e-22e6cb9b3881")
    private boolean dragAllowed;

    @objid ("43bdda65-2fee-4cbf-a92c-9b221a3abb0f")
    private boolean isUserEditable;

    @objid ("df11924a-8e86-41e0-bd73-83b34bf71fd3")
    private int resizeDirections;

    @objid ("8facd0dd-7705-4f08-ad90-9c9a33cdac25")
    private final List<AbstractHandle> handles = new ArrayList<>();

    @objid ("b18d2203-8aaa-4e05-abb7-a8e5762fe89c")
    private final GraphicalEditPart host;

    @objid ("faf636af-e612-45f3-8049-04ffa66304b1")
    private DragTracker moveDragTracker;

    @objid ("31cd4680-f8e7-4067-921d-8ffbfb3c0c51")
    private IntFunction<DragTracker> resizeDragTrackerProvider;

    @objid ("78f91530-15e1-46ef-b7e3-883b5828e66f")
    private DragTracker selectDragTracker;

    @objid ("b8031782-7279-4f02-b6c6-cef4737ffea3")
    public SelectionHandlesBuilder(GraphicalEditPart host) {
        this.host = host;
        this.selectDragTracker = new SelectEditPartTracker(host);
        this.isUserEditable = isModelUserEditable(host);
        this.resizeDragTrackerProvider = direction -> new DefaultResizeTracker(host, direction);
        this.moveDragTracker = getHost().getDragTracker(null);
    }

    /**
     * Fills the given List with handles at each corner of a figure.
     * @param part
     * the handles' GraphicalEditPart
     * @param handles
     * the List to add the four corner handles to
     * 
     * @param tracker the handles' DragTracker
     * @param cursor the handles' Cursor
     */
    @objid ("a990d3d7-2509-4b71-b667-af2e88db96ca")
    public SelectionHandlesBuilder addCornerHandles(DragTracker tracker, Cursor cursor) {
        this.handles.add(createHandle( PositionConstants.SOUTH_EAST, tracker, cursor));
        this.handles.add(createHandle( PositionConstants.SOUTH_WEST, tracker, cursor));
        this.handles.add(createHandle(PositionConstants.NORTH_WEST, tracker, cursor));
        this.handles.add(createHandle(PositionConstants.NORTH_EAST, tracker, cursor));
        return this;
    }

    /**
     * Add handles for a non resizeable edit part.
     * 
     * @return this instance
     */
    @objid ("446b0295-8a08-44b6-a84f-3db8b83e9976")
    public SelectionHandlesBuilder addNonResizeableHandles() {
        DragTracker dragTracker = isUserEditable() ? this.moveDragTracker : getSelectTracker();
        Cursor      cursor      = this.dragAllowed  ? getHost().getFigure().getCursor() : Cursors.ARROW;
        
        addCornerHandles(dragTracker, cursor);
        return this;
    }

    /**
     * Add a resize handle for one direction.
     * 
     * @param dir a direction in {@link PositionConstants} constants.
     * @return this instance
     * @since 3.7.1
     */
    @objid ("430d359a-284d-4adf-af95-6832a2342a68")
    public SelectionHandlesBuilder addResizeHandle(int dir) {
        this.handles.add(createResizeHandle(dir));
        return this;
    }

    /**
     * Add the handles for a resizeable edit part.
     * 
     * @return this instance.
     */
    @objid ("fdd04b8a-4f6b-45fe-a4c4-eefe21bb47e8")
    public SelectionHandlesBuilder addResizeableHandles() {
        boolean modelUserEditable = isUserEditable();
        
        DragTracker          dragTracker = modelUserEditable ? this.moveDragTracker : getSelectTracker();
        Cursor               figureCursor    = getHost().getFigure().getCursor();
        
        if (this.resizeDirections == 0 || ! modelUserEditable) {
            //NonResizableHandleKit.addMoveHandle(host, list, hostDragTracker, figureCursor);
            addCornerHandles(dragTracker, figureCursor);
        
        } else {
            //ResizableHandleKit.addMoveHandle(host, list, hostDragTracker, figureCursor);
            
            addConditionalHandle(PositionConstants.EAST, dragTracker, figureCursor);
            addConditionalHandle(PositionConstants.SOUTH_EAST, dragTracker, figureCursor);
            addConditionalHandle(PositionConstants.SOUTH, dragTracker, figureCursor);
            addConditionalHandle(PositionConstants.SOUTH_WEST, dragTracker, figureCursor);
            addConditionalHandle(PositionConstants.WEST, dragTracker, figureCursor);
            addConditionalHandle(PositionConstants.NORTH_WEST, dragTracker, figureCursor);
            addConditionalHandle(PositionConstants.NORTH, dragTracker, figureCursor);
            addConditionalHandle(PositionConstants.NORTH_EAST, dragTracker, figureCursor);
        }
        return this;
    }

    /**
     * Disable the given handles if the edit part represents a non user editable graphic model.
     * 
     * @param host an edit part
     * @param handles the handle to process
     * @return the same handle list for convenience.
     */
    @objid ("453daa34-fc97-4c07-991d-3abbbac770b9")
    public static <T> List<T> disableHandlesIfReadOnly(EditPart host, List<T> handles) {
        boolean userEditable = ((IGmObject)host.getModel()).isUserEditable();
        if (!userEditable) {
            FireNavigateEditPartTracker dt = new FireNavigateEditPartTracker(host);
            for (Object h : handles) {
                if (h instanceof ConnectionHandle) {
                    ConnectionHandle connHandle = (ConnectionHandle) h;
                    connHandle.setFixed(true);
                }
                if (h instanceof AbstractHandle) {
                    ((AbstractHandle) h).setDragTracker(dt);
                }
            }
        }
        return handles;
    }

    /**
     * @return the built handles.
     */
    @objid ("51522d81-0518-4a56-a485-c8f13838c511")
    public List<AbstractHandle> getHandles() {
        return this.handles;
    }

    /**
     * Set whether dragging is allowed.
     * 
     * @param allowed whether dragging is allowed
     * @return this instance
     */
    @objid ("94a29743-e2ff-4f76-ac7b-dc7b444f208b")
    public SelectionHandlesBuilder withDragAllowed(boolean allowed) {
        this.dragAllowed = allowed;
        return this;
    }

    /**
     * @param dragTracker a custom move drag tracker
     * @return this instance
     */
    @objid ("875d682d-1533-49a4-9764-703ea290c96e")
    public SelectionHandlesBuilder withMoveDragTracker(DragTracker dragTracker) {
        this.moveDragTracker = dragTracker;
        return this;
    }

    /**
     * Set resize directions.
     * @see PositionConstants PositionConstants for possible values
     * 
     * @param dir the directions
     * @return this instance
     */
    @objid ("1295ac2e-5d32-4d1a-8edc-ef5883186809")
    public SelectionHandlesBuilder withResizeDirections(int dir) {
        this.resizeDirections = dir;
        return this;
    }

    /**
     * Set a custom resize drag tracker provider.
     * <p>
     * The function will be provided resize direction.
     * 
     * @param provider a resize drag tracker provider.
     * @return this instance
     */
    @objid ("562a585b-8ae5-410e-bbf0-cf12d9ae0b3b")
    public SelectionHandlesBuilder withRezizeTracker(IntFunction<DragTracker> provider) {
        this.resizeDragTrackerProvider = provider;
        return this;
    }

    /**
     * Add a resize handle if the figure is resizeable for the given direction, a move handle in the other case.
     * 
     * @param dir relative direction from the center of the owner figure
     * @param moveTracker the move drag tracker to use for non resizeable handle
     * @param moveCursor the move cursor to use for non resizeable handle
     */
    @objid ("d641b951-2322-439d-8abf-cf342f6b12c7")
    protected void addConditionalHandle(int dir, DragTracker moveTracker, Cursor moveCursor) {
        if ((this.resizeDirections & dir) != 0) {
            this.handles.add(createResizeHandle(dir));
        } else {
            this.handles.add(createHandle(dir, moveTracker, moveCursor));
        }
    }

    /**
     * Create a handle for the given direction with the givven drag rtacker and cursor.
     * 
     * @param direction relative direction from the center of the owner figure
     * @param tracker the drag tracker to use
     * @param cursor the mouse cursor
     * @return the created handle.
     */
    @objid ("f8dfce44-6adf-4b36-afb3-7979802d54df")
    protected AbstractHandle createHandle(int direction, DragTracker tracker, Cursor cursor) {
        ResizeHandle handle = new ResizeHandle(this.host, direction);
        handle.setCursor(cursor);
        handle.setDragTracker(tracker);
        
        handle.setSize(10, 10);
        handle.setPreferredSize(9, 9);
        handle.setMaximumSize(new Dimension(9, 9));
        handle.setMinimumSize(new Dimension(9, 9));
        return handle;
    }

    @objid ("90c795b4-4c82-44ce-9377-446ca21b44fb")
    protected AbstractHandle createResizeHandle(int direction) {
        ResizeHandle handle = new ResizeHandle(this.host, direction);
        handle.setDragTracker(getResizeTracker(direction));
        
        handle.setSize(10, 10);
        handle.setPreferredSize(9, 9);
        handle.setMaximumSize(new Dimension(9, 9));
        handle.setMinimumSize(new Dimension(9, 9));
        return handle;
    }

    @objid ("619dde8e-bd1c-4324-b86f-99c90c87c3d4")
    protected GraphicalEditPart getHost() {
        return this.host;
    }

    @objid ("cf70dde6-c57d-4118-8314-a7c24488d864")
    protected final DragTracker getResizeTracker(int direction) {
        return this.resizeDragTrackerProvider.apply(direction);
    }

    @objid ("4eda4906-1301-45e2-9de7-2b88fc19ee9d")
    protected DragTracker getSelectTracker() {
        return this.selectDragTracker;
    }

    /**
     * Computes whether the user can edit the graphic model.
     * 
     * @param host an edit part
     * @return whether the user can edit the model.
     */
    @objid ("33e50264-271d-49d8-ba3e-a1f6372f33b8")
    protected static boolean isModelUserEditable(GraphicalEditPart host) {
        IGmObject m = (IGmObject) host.getModel();
        return m.isUserEditable();
    }

    @objid ("c95d6c36-eab4-4044-a05d-1afbdbb93d4d")
    protected boolean isUserEditable() {
        return this.isUserEditable;
    }

}
