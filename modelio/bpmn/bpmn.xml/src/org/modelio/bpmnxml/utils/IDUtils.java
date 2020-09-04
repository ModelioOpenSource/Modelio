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

package org.modelio.bpmnxml.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.Diagram;
import org.modelio.bpmnxml.model.DiagramElement;
import org.modelio.bpmnxml.model.TBaseElement;
import org.modelio.bpmnxml.model.TCollaboration;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("5f2ae481-58c6-42f8-9b17-2b208554802a")
public class IDUtils {
    @objid ("5c19f623-9340-43d4-b59a-2516ee3b44bd")
    public static String formatJaxbID(MObject element) {
        if (element.getUuid().substring(0, 1).matches("[0-9]")) {
            return "modelio-" + element.getUuid();
        }
        return element.getUuid();
    }

    @objid ("8bee20c4-9cad-4fc5-9b85-4a1de66ea2c5")
    public static String getJaxbId(Object context, Object jaxbElement) {
        String id = null;
        if (jaxbElement instanceof TBaseElement) {
            id = ((TBaseElement) jaxbElement).getId();
        } else if (jaxbElement instanceof TDefinitions) {
            id = ((TDefinitions) jaxbElement).getId();
        } else if (jaxbElement instanceof Diagram) {
            id = ((Diagram) jaxbElement).getId();
        } else if (jaxbElement instanceof DiagramElement) {
            id = ((DiagramElement) jaxbElement).getId();
        } else if (jaxbElement instanceof TCollaboration) {
            id = ((TCollaboration) jaxbElement).getId();
        }
        
        if (id == null || "".equals(id)) {
            if (context instanceof MObject) {
                id = ((MObject) context).getUuid();
            } else {
                return null;
            }
        
            id = id + "SUB-";
        }
        return id;
    }

    @objid ("86ee0687-6c71-4a93-afe0-c890a3415055")
    public static String formatModelioId(String id) {
        if (id.startsWith("modelio-")) {
            return id.replace("modelio-", "");
        }
        return id;
    }

}
