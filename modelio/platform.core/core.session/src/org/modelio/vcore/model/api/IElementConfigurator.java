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

package org.modelio.vcore.model.api;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service to configure newly created (or not) model elements
 * depending on their initialized state and configuration properties.
 * <p>
 * For example, can create analyst property tables for analyst elements.
 */
@objid ("20bf42d4-511f-4735-bc8b-6b7d2e89a8a4")
public interface IElementConfigurator {
    /**
     * Configure an element.
     * 
     * @param element the element to configure
     * @param properties properties that can be used to customize the configuration. These properties
     * are implementation dependent.
     */
    @objid ("ef57aea4-e26f-4ed6-aea6-534b24bc33dd")
    void configure(MObject element, Map<String, Object> properties);

}
