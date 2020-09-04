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

package org.modelio.xmi.gui.report;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("be2135c6-2dd9-4d77-93f8-4a4c2f570eb3")
public interface IReportWriter {
    @objid ("acd3e4ec-681a-4fc1-97b5-31277bfb36d8")
    void addWarning(String message, Element element, String description);

    @objid ("bafffd60-4704-4fb8-8cea-c4d74a10dc0d")
    void addError(String message, Element element, String description);

    @objid ("a2b5f55a-edc0-4030-af3d-80bf28319808")
    void addInfo(String message, Element element, String description);

    @objid ("f07076f6-82d9-4921-87cc-a5703aa84cc0")
    boolean isEmpty();

    @objid ("dae5963e-a937-4d84-95b6-99c084f9cd2a")
    boolean hasErrors();

    @objid ("6b68d7d0-d7e9-4fab-8d0d-6552839e017d")
    boolean hasWarnings();

    @objid ("8725f3ab-125d-4ffa-b181-3e4aaec4770e")
    boolean hasInfos();

}
