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
package org.modelio.propertyview.vtabfolder;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Widget;

@objid ("fbbe2997-41c6-46fe-bc73-0db556997c59")
public class VTabFolderEvent extends TypedEvent {
    /**
     * A flag indicating whether the operation should be allowed.
     * Setting this field to <code>false</code> will cancel the operation.
     * Applies to the close and showList events.
     */
    @objid ("540e4510-9848-4e1f-99d5-b110f7111207")
    public boolean doit;

    /**
     * The widget-relative, x coordinate of the chevron button
     * at the time of the event.  Applies to the showList event.
     * 
     * @since 3.0
     */
    @objid ("0d66509d-579b-4ee4-b081-0b0371ce402b")
    public int x;

    /**
     * The widget-relative, y coordinate of the chevron button
     * at the time of the event.  Applies to the showList event.
     * 
     * @since 3.0
     */
    @objid ("84147bf1-ac76-4cab-9593-c381e11a44f2")
    public int y;

    /**
     * The width of the chevron button at the time of the event.
     * Applies to the showList event.
     * 
     * @since 3.0
     */
    @objid ("fb78f6d7-4287-4154-a52e-2735d3e1ca5f")
    public int width;

    /**
     * The height of the chevron button at the time of the event.
     * Applies to the showList event.
     * 
     * @since 3.0
     */
    @objid ("c0c78afc-00c1-49c5-b651-25ad0344e338")
    public int height;

    @objid ("f3d0a553-dd04-416e-9b40-2b2339ff431a")
    static final long serialVersionUID = 3760566386225066807L;

    /**
     * The tab item for the operation.
     */
    @objid ("efa0ef11-b03a-43c8-9635-d56451880185")
    public Widget item;

    /**
     * Constructs a new instance of this class.
     * @param w the widget that fired the event
     */
    @objid ("d162b4c0-8bfa-42ab-83ce-ce16ffde5f61")
     VTabFolderEvent(Widget w) {
        super(w);
    }

    /**
     * Returns a string containing a concise, human-readable
     * description of the receiver.
     * @return a string representation of the event
     */
    @objid ("add093f8-05a9-4f5d-9805-5f1038bbc199")
    @Override
    public String toString() {
        String string = super.toString ();
        return string.substring (0, string.length() - 1) // remove trailing '}'
                                + " item=" + this.item
                                + " doit=" + this.doit
                                + " x=" + this.x
                                + " y=" + this.y
                                + " width=" + this.width
                                + " height=" + this.height
                                + "}";
    }

}
