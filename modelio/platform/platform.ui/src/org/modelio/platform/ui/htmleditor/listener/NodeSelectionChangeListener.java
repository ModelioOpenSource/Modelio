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

package org.modelio.platform.ui.htmleditor.listener;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.ui.htmleditor.events.NodeSelectionEvent;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
@objid ("19372dc1-f798-4f63-8910-b90160f78eab")
public interface NodeSelectionChangeListener {
    @objid ("b5f63a17-fb80-4c1b-bbc3-9571849caec6")
    void selectedNodeChanged(NodeSelectionEvent event);

}
