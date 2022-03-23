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
package org.modelio.diagram.elements.core.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.GroupRequest;

/**
 * Utilities related to request building.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("dc75adfb-6734-402b-8cdf-de9f7e93c0a4")
public class RequestHelper {
    /**
     * Request extended parameter key to reference a parent {@link Request}.
     */
    @objid ("620bd04a-1d27-44f7-93d7-0f1a30141dbc")
    private static final Object SHARED_EDIT_PARTS = "shared edit parts";

    /**
     * Create a shallow copy of the given request.
     * <p>
     * The copy and the original requests share the same extended data map. Move delta and resize delta are not shared.
     * @param orig the original request
     * @return the request copy.
     */
    @objid ("3088f295-648c-4ab9-81b1-0417521fc8c6")
    public static ChangeBoundsRequest shallowCopy(ChangeBoundsRequest orig) {
        ChangeBoundsRequest copy = new ChangeBoundsRequest(orig.getType());
        copy.setEditParts(orig.getEditParts());
        copy.setCenteredResize(orig.isCenteredResize());
        copy.setConstrainedMove(orig.isConstrainedMove());
        copy.setConstrainedResize(orig.isConstrainedResize());
        copy.setExtendedData(orig.getExtendedData());
        copy.setLocation(orig.getLocation());
        copy.setResizeDirection(orig.getResizeDirection());
        copy.setMoveDelta(new PrecisionPoint(orig.getMoveDelta()));
        copy.setSizeDelta(new PrecisionDimension(orig.getSizeDelta()));
        return copy;
    }

    /**
     * Create a deep copy of the given request.
     * <p>
     * The copy has a copy of the original extended data map, move delta and size delta.
     * @param orig the original request
     * @return the request copy.
     */
    @objid ("9bf0484f-92b7-4d1d-a47b-0f964eb9de3e")
    public static ChangeBoundsRequest deepCopy(ChangeBoundsRequest orig) {
        ChangeBoundsRequest copy = new ChangeBoundsRequest(orig.getType());
        copy.setEditParts(orig.getEditParts());
        copy.setCenteredResize(orig.isCenteredResize());
        copy.setConstrainedMove(orig.isConstrainedMove());
        copy.setConstrainedResize(orig.isConstrainedResize());
        copy.getExtendedData().putAll(orig.getExtendedData());
        copy.setLocation(orig.getLocation());
        copy.getMoveDelta().setLocation(orig.getMoveDelta());
        copy.setResizeDirection(orig.getResizeDirection());
        copy.getSizeDelta().setSize(orig.getSizeDelta());
        return copy;
    }

    /**
     * Get a copy of the figure bounds after applying the given request.
     * <p>
     * The returned rectangle is in the figure coordinates.
     * @param fig a figure
     * @param request a move/resize request
     * @return the transformed figure bounds.
     */
    @objid ("0ae581bb-a3f9-4792-93de-d994f8f1c65d")
    public static Rectangle getTransformedBounds(IFigure fig, ChangeBoundsRequest request) {
        Rectangle ret = new PrecisionRectangle(fig.getBounds());
        fig.translateToAbsolute(ret);
        ret.translate(request.getMoveDelta()).resize(request.getSizeDelta());
        fig.translateToRelative(ret);
        return ret;
    }

    /**
     * Set the request move and size delta to ask the given figure to move/resize to the given bounds.
     * @param req the request to modify
     * @param fig the figure to move/resize
     * @param requestedBounds the requested figure bounds in the figure coordinates.
     */
    @objid ("361b1d41-1522-4785-8df3-f2cd848838ca")
    public static void setDeltas(ChangeBoundsRequest req, IFigure fig, Rectangle requestedBounds) {
        Rectangle curAbsBounds = new PrecisionRectangle(fig.getBounds());
        fig.translateToAbsolute(curAbsBounds);
        
        Rectangle reqAbsBounds = new PrecisionRectangle(requestedBounds);
        fig.translateToAbsolute(reqAbsBounds);
        
        req.setMoveDelta(reqAbsBounds.getTopLeft().translate(-curAbsBounds.preciseX(), -curAbsBounds.preciseY()));
        req.setSizeDelta(reqAbsBounds.getSize().shrink(curAbsBounds.preciseWidth(), curAbsBounds.preciseHeight()));
        
        // req.getMoveDelta().setLocation(reqAbsBounds.x() - curAbsBounds.x(), reqAbsBounds.y() - curAbsBounds.y());
        // req.getSizeDelta().setSize(reqAbsBounds.width() - curAbsBounds.width(), reqAbsBounds.height() - curAbsBounds.height());
        
    }

    /**
     * Dump the request to a string for debugging.
     * @param req a request
     * @return a string representation
     */
    @objid ("8059da56-b8e2-4842-92f8-f7c8079a0aa5")
    public static String toString(Request req) {
        if (req instanceof ChangeBoundsRequest) {
            return toString((ChangeBoundsRequest) req);
        } else if (req instanceof GroupRequest) {
            GroupRequest gr = (GroupRequest) req;
            return req.getClass().getSimpleName() + " { type=" + req.getType() + gr.getEditParts() + "}";
        } else {
            return req.getClass().getSimpleName() + " { type=" + req.getType() + "}";
        }
        
    }

    /**
     * Dump the request to a string for debugging.
     * @param req a request
     * @return a string representation
     */
    @objid ("37331748-4cd0-47e5-a424-345a3c64b69e")
    public static String toString(ChangeBoundsRequest req) {
        return req.getClass().getSimpleName() + " {move=" + req.getMoveDelta() + ", resize=" + req.getSizeDelta() + ", parts=" + req.getEditParts() + "}";
    }

    /**
     * Add the value to the request extended data entry.
     * <p>
     * The data entry is handled as a Collection.
     * @param req the request
     * @param key the extended data entry key
     * @param value the value to add to the data entry
     */
    @objid ("3213315b-d389-45cf-bde3-e4210d2f81d7")
    public static void addParamValue(Request req, Object key, Object value) {
        @SuppressWarnings ("unchecked")
        Collection<Object> vals = (Collection<Object>) req.getExtendedData().get(key);
        if (vals == null) {
            vals = new ArrayList<>(1);
            req.getExtendedData().put(key, vals);
        }
        
        if (!vals.contains(value)) {
            vals.add(value);
        }
        
    }

    @objid ("56475dbe-40c1-4572-b135-36084878632a")
    public static boolean containsParamValue(Request req, Object key, Object value) {
        @SuppressWarnings ("unchecked")
        Collection<Object> vals = (Collection<Object>) req.getExtendedData().get(key);
        if (vals == null) {
            return false;
        }
        return vals.contains(value);
    }

    /**
     * Get the edit parts handled by the parent requests hierarchy.
     * @param req a Request.
     * @return a bunch of edit parts.
     */
    @objid ("e67244fa-8f24-4628-be37-a71800d4067f")
    public static Collection<GraphicalEditPart> getSharedEditParts(GroupRequest req) {
        return (Collection<GraphicalEditPart>) req.getExtendedData().getOrDefault(SHARED_EDIT_PARTS, Collections.emptyList());
    }

    /**
     * Add the edit parts handled by the parent request to the shared edit parts.
     * @param req a Request.
     * @param parentRequest req's parent request.
     */
    @objid ("ac381a53-1ad2-4140-9417-c6928e804576")
    public static void addSharedEditParts(GroupRequest req, GroupRequest parentRequest) {
        List<GraphicalEditPart> editParts = parentRequest.getEditParts();
        
        Set<GraphicalEditPart> vals = (Set<GraphicalEditPart>) req.getExtendedData().get(SHARED_EDIT_PARTS);
        if (vals == null) {
            vals = new HashSet<>(editParts.size());
            req.getExtendedData().put(SHARED_EDIT_PARTS, vals);
        }
        
        vals.addAll(editParts);
        
    }

}
