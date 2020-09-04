/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.core.link.anchors;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;

/**
 * Maps GmAnchors to ConnectionAnchor and reversely.
 * <p>
 * Used to return the same ConnectionAnchor for a given anchor model and reversely. Implemented using a weak hash map so
 * that entries are removed when an anchor model is discarded by the GC.
 */
@objid ("7fdb9a20-1dec-11e2-8cad-001ec947c8cc")
public class AnchorRegistry {
    @objid ("7fdb9a22-1dec-11e2-8cad-001ec947c8cc")
    private static Map<Object, WeakReference<ConnectionAnchor>> anchorRegistry = new WeakHashMap<>();

    @objid ("7fdb9a28-1dec-11e2-8cad-001ec947c8cc")
    private static Map<ConnectionAnchor, WeakReference<Object>> anchorModelRegistry = new WeakHashMap<>();

    /**
     * Register an anchor model and figure.
     * 
     * @param gmAnchor the anchor model.
     * @param figAnchor the draw2d anchor.
     */
    @objid ("7fdb9a30-1dec-11e2-8cad-001ec947c8cc")
    public static void put(final Object gmAnchor, final ConnectionAnchor figAnchor) {
        anchorRegistry.put(gmAnchor, new WeakReference<>(figAnchor));
        anchorModelRegistry.put(figAnchor, new WeakReference<>(gmAnchor));
    }

    /**
     * Get the model of a given draw2d anchor.
     * 
     * @param figAnchor a draw2d anchor.
     * @return the anchor model, or <code>null</code> if the anchor is not registered.
     */
    @objid ("7fdb9a39-1dec-11e2-8cad-001ec947c8cc")
    public static Object getGmAnchor(final ConnectionAnchor figAnchor) {
        WeakReference<Object> wret = anchorModelRegistry.get(figAnchor);
        if (wret != null)
            return wret.get();
        else
            return null;
    }

    /**
     * Get the draw2d anchor for a given model anchor.
     * 
     * @param gmAnchor an anchor model.
     * @return the draw2d anchor, or <code>null</code> if the model is not registered.
     */
    @objid ("7fdb9a41-1dec-11e2-8cad-001ec947c8cc")
    public static ConnectionAnchor getFigAnchor(final Object gmAnchor) {
        WeakReference<ConnectionAnchor> wret = anchorRegistry.get(gmAnchor);
        if (wret != null)
            return wret.get();
        else
            return null;
    }

}
