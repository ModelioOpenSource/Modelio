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

package org.modelio.diagram.elements.core.model;

import java.beans.PropertyChangeListener;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Represents a reference toward a {@link IGmObject} in another diagram.
 * 
 * @param <T> the type of the referenced graphic model
 * @since 3.7
 */
@objid ("0f2a5978-bda1-44e2-86ee-17bc2207d56c")
public interface IGmReference<T extends IGmObject> extends IPersistent {
    /**
     * Change event telling the reference was resolved.
     */
    @objid ("312ab332-cda8-4abc-8029-f31edebf1624")
    public static final String PROP_REFERENCE_RESOLVED = "REFERENCE_RESOLVED";

    /**
     * Change event telling the reference is broken.
     */
    @objid ("7111812f-67d9-4990-adc4-86ee5699d3e3")
    public static final String PROP_REFERENCE_BROKEN = "REFERENCE_BROKEN";

    /**
     * Get the referenced graphic model.
     * <p>
     * Returns <i>null</i> if the reference is not valid: the target diagram does not exist or
     * the referenced graphic model does not exist anymore.
     * 
     * @return the referenced graphic model or <i>null</i>.
     */
    @objid ("6aa722cf-e095-4339-b7f2-4ca258875183")
    T getReferencedModel();

    /**
     * @return true only if the reference resolves to a valid graphic model.
     */
    @objid ("f43a84ec-10e6-4b3d-8df3-1e8d992c7154")
    default boolean isReferencedModelValid() {
        final T target = getReferencedModel();
        return target != null && target.getDiagram() != null;
    }

    /**
     * See {@link IGmObject#delete()}.
     * <p>
     * {@link IGmObject#delete() delete} the target object.
     * All implementations must call {@link #releaseReference()}.
     */
    @objid ("aa6bf6bb-b6df-4fd2-a1b0-f3d2b7693404")
    default void delete() {
        T t = getReferencedModel();
        if (t != null) {
            t.delete();
        }
        releaseGmReference();
    }

    /**
     * Shortcut for {@link IGmReference#getReferencedModel()} that avoids having to test <i>ref</i> for <i>null</i>.
     * 
     * @param ref a reference, may be null.
     * @return the referenced element or <i>null</i>.
     */
    @objid ("b8e182b6-aee5-40b1-a4a5-8c7d3eac6153")
    static <T extends IGmObject> T resolve(IGmReference<T> ref) {
        if (ref == null) {
            return null;
        } else {
            return ref.getReferencedModel();
        }
    }

    /**
     * Add a listener that is fired when the reference is resolved.
     * @see #PROP_REFERENCE_RESOLVED
     * @see #PROP_REFERENCE_BROKEN
     * 
     * @param listener a property change listener.
     */
    @objid ("96bcc603-659d-4908-9c9e-f3de1638bd1d")
    void addReferenceResolvedListener(PropertyChangeListener listener);

    /**
     * Remove a reference change listener.
     * @see #PROP_REFERENCE_RESOLVED
     * @see #PROP_REFERENCE_BROKEN
     * 
     * @param listener a property change listener.
     */
    @objid ("dcdfd540-0770-4052-89d1-871dcc3af605")
    void removeReferenceChangeListener(PropertyChangeListener listener);

    /**
     * Tells this reference is not used anymore.
     * <p>
     * Do nothing with the target object.
     */
    @objid ("395f22c9-57c5-4190-8cdd-1c9826db0717")
    void releaseGmReference();

    /**
     * Add a listener that is fired when the reference is broken.
     * @see #PROP_REFERENCE_RESOLVED
     * @see #PROP_REFERENCE_BROKEN
     * 
     * @param listener a property change listener.
     */
    @objid ("cd8c9d0e-5170-4620-a5ea-58884710a802")
    void addReferenceBrokenListener(PropertyChangeListener listener);

    /**
     * Refresh the reference.
     */
    @objid ("4022d480-076b-4758-8f33-276fcd65c729")
    void refresh();

}
