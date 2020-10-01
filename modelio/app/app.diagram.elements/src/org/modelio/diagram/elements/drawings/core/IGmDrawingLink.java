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

package org.modelio.diagram.elements.drawings.core;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;

/**
 * This interface means that the diagram graphic is a Connection.
 */
@objid ("8c8a523e-71af-4060-ad8e-4d5c67ecbec1")
public interface IGmDrawingLink extends IGmDrawingLinkable, IGmLinkObject {
    /**
     * Tells the source node changed.
     */
    @objid ("add9f195-b186-4c33-b5fb-351500450297")
    public static final String PROP_SOURCE_GM = "Source graphic model changed";

    /**
     * Tells the target node changed.
     */
    @objid ("dd6ab9f5-4853-4b16-b142-4226aa5dba6c")
    public static final String PROP_TARGET_GM = "Target graphic model changed";

    /**
     * @return The link source
     */
    @objid ("34e43653-5607-4929-840e-f94161baf871")
    IGmDrawingLinkable getFrom();

    /**
     * Update the link origin.
     * <p>
     * This method is intended to be called only by {@link IGmDrawingLinkable#addEndingDrawingLink(IGmDrawingLink)}. It does not fire change
     * event.
     * 
     * @param from The new link origin
     */
    @objid ("a957d273-b074-410e-b541-b4ec879a4a47")
    void setFrom(IGmDrawingLinkable from);

    /**
     * @return the link destination
     */
    @objid ("5ebe9452-0353-4422-b42c-098eec88267a")
    IGmDrawingLinkable getTo();

    /**
     * Update the link destination.
     * <p>
     * This method is intended to be called only by {@link IGmDrawingLinkable#addEndingDrawingLink(IGmDrawingLink)}. It does not fire change
     * event.
     * 
     * @param to The new destination
     */
    @objid ("6c0b2865-f77f-4104-b884-e164616ed32b")
    void setTo(IGmDrawingLinkable to);

    /**
     * Tells listeners the path changed.
     * 
     * @param path the new path
     */
    @objid ("4e0dc836-97fe-431d-aef7-492983de5629")
    void firePathChanged(final IGmPath path);

    /**
     * Get the locator model used to layout the given extension.
     * 
     * @param extension A link extension.
     * @return The locator model.
     */
    @objid ("fe22e9e5-907c-4779-8264-bad8ca1c1187")
    IGmLocator getLayoutContraint(IGmObject extension);

    /**
     * <p>
     * Get the extensions nodes currently visible.
     * </p>
     * <p>
     * The returned list is a copy and may be freely modified.
     * </p>
     * 
     * @return The visible link extension nodes.
     */
    @objid ("00a4b62c-7079-4079-8410-339983a833b0")
    Collection<IGmObject> getVisibleExtensions();

}
