/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.diagram.styles.core.IStyleProvider;

/**
 * Interface that all graphic model displayed in a diagram must implement.
 * <p>
 * All graphic elements
 * 
 * @author cmarin
 */
@objid ("80827eed-1dec-11e2-8cad-001ec947c8cc")
public interface IGmObject extends IStyleProvider, IPersistent {
    /**
     * Property change event name, indicates the layout data changed.
     */
    @objid ("92093273-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_LAYOUTDATA = "LayoutData";

    /**
     * Property change event name, indicates the displayed label changed.
     */
    @objid ("92093279-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_LABEL = "Name";

    /**
     * Property change event name, indicates that links were added or removed to a node, or that source or destination
     * were modified on a link.
     */
    @objid ("920b94d0-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_LINK_SOURCE = "Link source";

    /**
     * Property change event name, indicates that links were added or removed to a node, or that source or destination
     * were modified on a link.
     */
    @objid ("920df72a-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_LINK_TARGET = "Link target";

    /**
     * Property change event name, indicates that children nodes have been added or removed.
     */
    @objid ("920df730-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_CHILDREN = "Children";

    /**
     * Property change event name, indicates that the element style changed.
     */
    @objid ("920df736-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_STYLE = "Style";

    /**
     * Property change event name, indicates that the gm has been deleted.
     */
    @objid ("92105984-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROPERTY_DELETE = "Delete";

    /**
     * Add a listener that is fired when a graphic model property change.
     * @param listener a property change listener.
     */
    @objid ("8084e11a-1dec-11e2-8cad-001ec947c8cc")
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Get the diagram containing this element.
     * <p>
     * May return <i>null</i> if the graphic model is not valid anymore.
     * @return the diagram.
     */
    @objid ("8084e11d-1dec-11e2-8cad-001ec947c8cc")
    IGmDiagram getDiagram();

    /**
     * Get the data used by the parent node layout manager to set this element position and size.
     * <p>
     * Usually only the parent node layout manager has an idea of the expected type of the layout data.
     * @return The element layout data. May be <tt>null</tt>
     */
    @objid ("8084e120-1dec-11e2-8cad-001ec947c8cc")
    Object getLayoutData();

    /**
     * Remove a model change listener.
     * @param listener a property change listener.
     */
    @objid ("8084e123-1dec-11e2-8cad-001ec947c8cc")
    void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * Set the data used by the parent node layout manager to set this element position and size.
     * @param layoutData The element layout data. May be <tt>null</tt>
     */
    @objid ("8084e126-1dec-11e2-8cad-001ec947c8cc")
    void setLayoutData(Object layoutData);

    /**
     * Delete the graphic model from the diagram.
     * <p>
     * <h2>Implementation constraint:</h2>
     * <ul>
     * <li>Each implementation must call the inherited implementation.
     * <li>The implementation must call {@link #removedFromDiagram()} directly or through the inherited implementation.
     * <li>The implementation for composite nodes should recurse on their owned nodes.
     * </ul>
     */
    @objid ("8084e129-1dec-11e2-8cad-001ec947c8cc")
    void delete();

    /**
     * Ensure the result of {@link #getDiagram()} is coherent with the graphic model owner and move
     * the graphic model to another diagram if needed.
     * <p>
     * This method must be called when the graphic model is moved into another graphic object,
     * potentially owned by a different diagram.
     * <p>
     * If the graphic model has to move to another diagram the implementation must:
     * <ul>
     * <li>Call the {@link GmAbstractObject#moveToDiagram(IGmDiagram)} on this graphic model with the new diagram as parameter.
     * <li>also call {@link #updateDiagram()} on all graphic models it owns so that they update
     * the diagram.
     * </ul>
     * @return true if the diagram has changed, else false.
     * @since 3.7
     */
    @objid ("5eed8a6e-4a65-4872-9f4f-078139b74239")
    boolean updateDiagram();

    /**
     * Called when this graphic model is removed from the diagram.
     * <p>
     * The implementation must make {@link #getDiagram()} return <i>null</i>.
     */
    @objid ("5e23293c-69ea-4b9f-9c60-ad05e067db6d")
    void removedFromDiagram();

    /**
     * Return true if the graphic model can be edited by the user.
     * <p>
     * Usually, the result is equivalent to the user editable status of the owner diagram.
     * <p>
     * A non user editable graphic model might still be modified by automatic refresh.
     * @return true if the graphic model can be edited by the user.
     */
    @objid ("9096a68a-de99-48dc-adc5-7039607cddfd")
    boolean isUserEditable();

}
