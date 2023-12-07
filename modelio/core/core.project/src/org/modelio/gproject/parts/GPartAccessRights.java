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
package org.modelio.gproject.parts;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGAccessRights;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.GPropertyConstants;

/**
 * {@link IGAccessRights} implementation that read the part {@link GProperties properties} once on construction.
 * <p>
 * The instance is then immutable.
 * 
 * @author cmarin
 * @since 5.5 - 29/08/2023
 */
@objid ("3b555a4d-7808-4c2a-a759-c4be3bc4eb22")
public class GPartAccessRights implements IGAccessRights {
    @objid ("463bc937-fb8c-4eb9-823b-529da56304db")
    private final Boolean visible;

    @objid ("569932d7-aeb1-4c21-8f58-8f4acdb49872")
    private final boolean editable;

    @objid ("f46cd8ec-b91d-4aad-8d22-c1cb239286f1")
    public  GPartAccessRights(GProperties properties) {
        this.visible = properties.getBooleanValue(GPropertyConstants.Access.VISIBLE, true);
        this.editable = computeEditable(properties);
        
    }

    @objid ("2e03dbfe-dd6b-46e9-8f19-faa8ffd1a116")
    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @objid ("38d9cfd9-1221-4323-87de-5ec000ababe4")
    @Override
    public boolean isEditable() {
        return this.editable;
    }

    @objid ("4ae0aa8a-cfd2-491d-8277-0d0207d0fa36")
    @SuppressWarnings ("deprecation")
    public static boolean computeEditable(GProperties properties) {
        // Compatible ascendant property, Modelio < 5.5
        if (properties.getBooleanValue(GPropertyConstants.PROP_READ_ONLY, false))
            return false;
        
        // Since Modelio 5.5 and Server 4.3
        return properties.getBooleanValue(GPropertyConstants.Access.WRITE, true);
    }

}
