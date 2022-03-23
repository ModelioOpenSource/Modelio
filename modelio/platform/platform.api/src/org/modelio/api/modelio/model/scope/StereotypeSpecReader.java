/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.api.modelio.model.scope;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Helper to find the stereotype from a stereotype specification as it is written in module.xml.
 * <p>
 * The stereotype specification may have one of the following formats:<ul>
 * <li> <i>stereotype name</i>
 * <li> <i>module name<b>#</b>stereotype name</i>
 * <li> <i>module regex<b>#</b>stereotype name</i>
 * <li> <i>module regex<b>#</b>stereotype regex</i>
 * </ul>
 */
@objid ("9768e29f-4fbf-47ea-9a72-621cbae4ec15")
public class StereotypeSpecReader {
    /**
     * Find the stereotype from a stereotype specification.
     * <p>
     * The stereotype specification may have one of the following formats:<ul>
     * <li> <i>stereotype name</i>
     * <li> <i>module name<b>#</b>stereotype name</i>
     * <li> <i>module regex<b>#</b>stereotype name</i>
     * <li> <i>module regex<b>#</b>stereotype regex</i>
     * </ul>
     * Returns <i>null</i> if the specification is <i>null</i> or the stereotype is not found.
     * @param session the modeling session
     * @param metaclass the metaclass to look from
     * @param stereotypeSpec the stereotype specification
     * @return the found stereotype or <i>null</i>.
     */
    @objid ("53295016-ed4e-459f-a2a7-a6efe66aa71c")
    public static Stereotype findStereotypeFromSpec(IModelingSession session, MClass metaclass, String stereotypeSpec) {
        if (stereotypeSpec == null) {
            return null;
        }
        
        if (stereotypeSpec.contains("#")) {
            String moduleName = stereotypeSpec.substring(0, stereotypeSpec.indexOf("#"));
            String stereotypeName = stereotypeSpec.substring(stereotypeSpec.indexOf("#") + 1, stereotypeSpec.length());
        
            return session.getMetamodelExtensions().getStereotype(moduleName, stereotypeName, metaclass);
        } else {
            return session.getMetamodelExtensions().getStereotype(stereotypeSpec, metaclass);
        }
        
    }

}
