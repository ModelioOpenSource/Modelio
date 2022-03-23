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
package org.modelio.platform.ui.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

/**
 * Basic interface for reusable panels.
 */
@objid ("beb37bdb-c677-11e1-8f21-002564c97630")
public interface IPanelProvider {
    /**
     * Give a hint about the fact that the provider can provide some useful contents for the object.
     * @param input a potential input for the panel
     * @return <code>true</code> if the given input can be used by the panel.
     */
    @objid ("433d2d3c-5c5f-47ac-a4ab-02cea00bc21e")
    boolean isRelevantFor(Object input);

    /**
     * Instantiate the panel graphical elements.
     * @param parent the composite to create the new graphical elements into.
     * @return the created panel.
     */
    @objid ("a0ad69d2-c677-11e1-8f21-002564c97630")
    Object createPanel(Composite parent);

    /**
     * Get the panel graphical elements, usually a {@link Composite} or a {@link Viewer}.
     * @return the created panel. <code>null</code> until IPanelProvided#create is called.
     */
    @objid ("a0ad69d6-c677-11e1-8f21-002564c97630")
    Object getPanel();

    /**
     * @return the help topic identifier for the panel. Returning <i>null</i< is allowed.
     */
    @objid ("f6b37759-e024-4e39-ac43-e77d6e9dfbd6")
    String getHelpTopic();

    /**
     * Get the current input of the panel.
     * @return the panel's input.
     */
    @objid ("a0ad69dc-c677-11e1-8f21-002564c97630")
    Object getInput();

    /**
     * Set a new input for the panel.
     * @param input the new input for the panel.
     */
    @objid ("a0ad69d9-c677-11e1-8f21-002564c97630")
    void setInput(Object input);

    /**
     * This method is called when the panel is to be disposed.
     * Implementors should redefine this method to dispose or close active resources that they have instantiated/run.
     */
    @objid ("cd48774a-28cd-4aa9-8dab-153b57c19c7c")
    void dispose();

    /**
     * Register a listener on the panel, to be triggered when the data model changes.
     * <p>
     * Implementers of {@link IPanelProvider} should provide an implementation for this method
     * as the default code only throws an {@link UnsupportedOperationException}.
     * </p>
     * @param l an instance of panel listener.
     */
    @objid ("4a35ded0-1621-4c7c-bce6-80fdfb713f53")
    default void addListener(IPanelListener l) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unregister a listener from the panel.
     * <p>
     * Implementers of {@link IPanelProvider} should provide an implementation for this method
     * as the default code only throws an {@link UnsupportedOperationException}.
     * </p>
     * @param l an instance of panel listener.
     */
    @objid ("3555a49d-e26d-452f-bddb-d9efce53fa81")
    default void removeListener(IPanelListener l) {
        throw new UnsupportedOperationException();
    }

}
