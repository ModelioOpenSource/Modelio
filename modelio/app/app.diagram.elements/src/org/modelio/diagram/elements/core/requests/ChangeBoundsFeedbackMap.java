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
package org.modelio.diagram.elements.core.requests;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;

/**
 * Move/Resize node feedback map stored in the request.
 * <p>
 * Use static methods to get an instance.:
 * <ul>
 * <li> {@link #getOrCreate(Request)} if you intend to put elements in the map
 * <li> {@link #getOrDummy(Request)} if you only need to read it
 * </ul>
 * 
 * @author cma
 * @since 5.0.2
 */
@objid ("ce5bc71e-d443-4400-b412-ea169472d54b")
public class ChangeBoundsFeedbackMap {
    @objid ("ec62c7ff-fba8-4117-878b-b82d3d910a41")
    private static final ChangeBoundsFeedbackMap EMPTY = new ChangeBoundsFeedbackMap();

    @objid ("e0c877ec-18fe-40f9-b870-d8e6a6adbef8")
    private final Map<EditPart, IFigure> feedbacks = new HashMap<>();

    @objid ("72a0df9f-23cb-4a2c-9b51-c6fb99e160c4")
    private  ChangeBoundsFeedbackMap() {
        
    }

    /**
     * Get or create a {@link ChangeBoundsFeedbackMap} from the request.
     * <p>
     * Creates and store a new instance if missing.
     * <p>
     * Use {@link #getOrDummy(Request)} method if you only need to read the map, to avoid creating useless instances.
     * @param from the request
     * @return the {@link ChangeBoundsFeedbackMap}
     */
    @objid ("0d7e9e82-b651-46db-b389-f6d9cd004f3f")
    public static ChangeBoundsFeedbackMap getOrCreate(Request from) {
        ChangeBoundsFeedbackMap m = (ChangeBoundsFeedbackMap) from.getExtendedData().get(ChangeBoundsFeedbackMap.class);
        if (m == null) {
            m = new ChangeBoundsFeedbackMap();
            from.getExtendedData().put(ChangeBoundsFeedbackMap.class, m);
        }
        return m;
    }

    /**
     * Find a {@link ChangeBoundsFeedbackMap} from the request.
     * <p>
     * Returns a non modifiable dummy instance if no instance is in the request.
     * <p>
     * Use this method if you only need to read the map, to avoid creating useless instances.
     * @param from the request
     * @return the {@link ChangeBoundsFeedbackMap}
     */
    @objid ("69036a82-e542-44cb-812f-ec785ba2d118")
    public static ChangeBoundsFeedbackMap getOrDummy(Request from) {
        return (ChangeBoundsFeedbackMap) from.getExtendedData().getOrDefault(ChangeBoundsFeedbackMap.class, EMPTY);
    }

    /**
     * @param ep an edit part
     * @return the edit part feedback or null.
     */
    @objid ("c41961ff-feed-4b88-975c-2cb6d5228547")
    public IFigure get(EditPart ep) {
        return this.feedbacks.get(ep);
    }

    /**
     * @return the internal map
     */
    @objid ("5d5a8882-6b92-4291-ae84-9631c582e60d")
    public Map<EditPart, IFigure> getMap() {
        return this.feedbacks;
    }

    /**
     * @param key an edit part
     * @param value its feedback figure
     */
    @objid ("841ef33e-8363-442d-88f2-b8fa820fa159")
    public void put(EditPart key, IFigure value) {
        if (this == EMPTY)
            throw new UnsupportedOperationException("The empty ChangeBoundsFeedbackMap is not modifiable.");
        
        this.feedbacks.put(key, value);
        
    }

    /**
     * Returns the value to which the specified key is mapped, or
     * {@code defaultValue} if this map contains no mapping for the key.
     * @param editpart the key whose associated value is to be returned
     * @param defaultFigure the default mapping of the key
     * @return the value to which the specified key is mapped, or
     * {@code defaultValue} if this map contains no mapping for the key
     */
    @objid ("e6075881-86eb-48d4-8b20-52843e7759ed")
    public IFigure getOrDefault(EditPart editpart, IFigure defaultFigure) {
        return this.feedbacks.getOrDefault(editpart, defaultFigure);
    }

}
