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

package org.modelio.diagram.elements.core.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.linknode.AbstractCreateLinkChooseNodeEditPolicy;
import org.modelio.diagram.elements.core.link.linknode.CreateLinkAndNodeCommand;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This interface is intended to be implemented by commands that create nodes.
 * A node creation command implementing <code>ILinkAndNodeCreationSupport</code> shall provide the GmNodeModel of the node it creates.
 * Such commands can therefore be used as action provider for "create link and node" behavior.<p/>
 * See {@link AbstractCreateLinkChooseNodeEditPolicy} and {@link CreateLinkAndNodeCommand} for further explanations.<br/>
 * See {@link DefaultCreateElementCommand} for an implementation example.
 */
@objid ("45886c3a-f3f2-4ea7-add7-d84287cef78f")
public interface ILinkAndNodeCreationSupport {
    @objid ("fb3a3c8e-fbdc-4f4f-8d4a-a2631788e251")
    GmNodeModel getMainLinkable();

}
