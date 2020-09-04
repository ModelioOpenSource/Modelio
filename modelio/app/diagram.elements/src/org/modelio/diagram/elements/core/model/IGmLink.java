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

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This interface means that the diagram graphic is a Connection.
 */
@objid ("80801c75-1dec-11e2-8cad-001ec947c8cc")
public interface IGmLink extends IGmLinkable, IGmLinkObject {
    /**
     * Extension role name for the link main label.
     */
    @objid ("a3ee7a7a-4708-4b5c-b37c-ec34f9d318d3")
    public static final String ROLE_MAIN_LABEL = "main_label";

    /**
     * @return The link source
     */
    @objid ("80801c77-1dec-11e2-8cad-001ec947c8cc")
    IGmLinkable getFrom();

    /**
     * Update the link origin.
     * <p>
     * This method is intended to be called only by {@link IGmLinkable#addEndingLink(IGmLink)}. It does not fire change event.
     * @param from The new link origin
     */
    @objid ("80801c7a-1dec-11e2-8cad-001ec947c8cc")
    void setFrom(IGmLinkable from);

    /**
     * @return the link source element
     */
    @objid ("80801c7d-1dec-11e2-8cad-001ec947c8cc")
    MObject getFromElement();

    /**
     * @return the link destination
     */
    @objid ("80801c7f-1dec-11e2-8cad-001ec947c8cc")
    IGmLinkable getTo();

    /**
     * Update the link destination.
     * <p>
     * This method is intended to be called only by {@link IGmLinkable#addEndingLink(IGmLink)}. It does not fire change event.
     * @param to The new destination
     */
    @objid ("80801c82-1dec-11e2-8cad-001ec947c8cc")
    void setTo(IGmLinkable to);

    /**
     * @return the link destination element
     */
    @objid ("80801c85-1dec-11e2-8cad-001ec947c8cc")
    MObject getToElement();

    @objid ("80801c89-1dec-11e2-8cad-001ec947c8cc")
    void firePathChanged(final IGmPath path);

    /**
     * Get the locator model used to layout the given extension.
     * @param extension A link extension.
     * @return The locator model.
     */
    @objid ("6f7d10c3-0c8d-4be2-9e40-8fb85ea88517")
    IGmLocator getLayoutContraint(IGmObject extension);

    /**
     * Change the given extension location.
     * @param extension The link extension.
     * @param layoutData The extension layout constraint.
     */
    @objid ("61dcef26-2a62-46b7-88e3-508c0bf3f74c")
    void setLayoutConstraint(IGmObject extension, IGmLocator layoutData);

    /**
     * Get the first link extension with the given role name.
     * @param role the role of the node to find.
     * @return the found node or null.
     */
    @objid ("2ca19396-253f-4740-99bc-5071a33e2082")
    IGmNode getFirstExtension(String role);

    /**
     * Get the link extension with the given role name.
     * @param role the role of the node to find.
     * @return the found nodes.
     */
    @objid ("fd738894-46af-4521-b5d9-49f159eb2f0f")
    Collection<IGmNode> getExtensions(String role);

}
