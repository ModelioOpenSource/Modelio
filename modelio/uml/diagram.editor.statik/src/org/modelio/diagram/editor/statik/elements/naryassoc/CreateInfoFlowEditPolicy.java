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

package org.modelio.diagram.editor.statik.elements.naryassoc;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.DefaultCreateInfoFlowOnLinkEditPolicy;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInfoFlowsGroup;

/**
 * Allow creation of information flow on the link.
 * <p>
 * Defers the creation to the {@link GmInfoFlowsGroup}.
 * 
 * @author cmarin
 */
@objid ("35bedac7-55b7-11e2-877f-002564c97630")
public class CreateInfoFlowEditPolicy extends DefaultCreateInfoFlowOnLinkEditPolicy {

// Nothing to do
}
