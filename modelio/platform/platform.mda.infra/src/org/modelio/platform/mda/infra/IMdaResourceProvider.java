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
package org.modelio.platform.mda.infra;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;

/**
 * Defines the contribution contract a MDA resource provider such as a module, a dynamic feature and so on.<br/>
 * The IMdaResourceProvider provides icons, images, translated labels and description for stereotypes and MDA extensions. The returned icons (or images) only represents the raw images (no additional decorations) and may be null.
 * 
 * 
 * @since 5.2
 */
@objid ("6d682937-f781-433a-8e11-5acf8ead1fea")
public interface IMdaResourceProvider {
    @objid ("524c0801-8fd6-4be0-8c1c-c30afae27c77")
    String getDescription(PropertyDefinition element);

    @objid ("794bc3be-6e48-4c63-8100-4ccef80d132b")
    String getDescription(Profile element);

    @objid ("59352258-5f88-4a64-9b91-966acea1b046")
    String getDescription(Stereotype element);

    @objid ("3a6d2738-ade2-47ac-bec5-c389ed095abf")
    String getDescription(NoteType element);

    @objid ("c103304b-0895-4bb8-93ac-7e7c8fe32cdd")
    String getDescription(TagType element);

    @objid ("c03f815b-6a90-468e-893f-b36210f0a744")
    String getDescription(ResourceType element);

    /**
     * Get the icon provided by the module for a given stereotype. The life cycle of the returned image is handled by the module and the image should not be disposed.
     * @param stereotype a stereotype
     * @return the stereotype image, or <code>null</code> if the module provides none.
     */
    @objid ("597eaed2-af07-4603-a34e-1fe369d2af48")
    Image getIcon(Stereotype stereotype);

    /**
     * Get the image provided by the module for a given stereotype.The life cycle of the returned image is handled by the module and the image should not be disposed.
     * @param stereotype a stereotype
     * @return the stereotype image, or <code>null</code> if the module provides none.
     */
    @objid ("41cc6b7a-2971-4a3b-a90c-a9fa284e0776")
    Image getImage(Stereotype stereotype);

    @objid ("67e081c2-e013-4ef5-b503-1e76a344d391")
    String getLabel(Stereotype stereotype);

    @objid ("8ec5900a-754b-4477-b87e-716a647fefec")
    String getLabel(TagType tagType);

    @objid ("7dd895f8-b83b-4c25-af4f-67b3e3c6a23c")
    String getLabel(NoteType noteType);

    @objid ("62f4436c-080f-4625-91bb-291797faf0d8")
    String getLabel(ResourceType resourceType);

    @objid ("618b842b-fded-48dc-a6a7-2278a574dcd6")
    String getLabel(ModuleComponent module);

    @objid ("e9405b08-41d7-4634-8a57-27c4208406f4")
    String getLabel(PropertyDefinition pdef);

    @objid ("d97331bb-3a52-40c7-a426-37a4ef5ca66a")
    String getLabel(Profile element);

    /**
     * Returns an Image for a module. The image life cycle is handled by the module.
     * @param moduleComponent the module to get the image from.
     * @return an Image for a module. Might be <code>null</code>.
     */
    @objid ("c634bdb8-5d50-4727-808c-b78339012672")
    Image getModuleImage(ModuleComponent moduleComponent);

    /**
     * Get the icon provided by the module for a given profile. The life cycle of the returned image is handled by the module and the image should not be disposed.
     * @param profile a profile
     * @return the profile image, or <code>null</code> if the module provides none.
     */
    @objid ("bd557301-f968-404e-942b-e1a316e035aa")
    Image getIcon(Profile profile);

    @objid ("e68395b2-bbe1-4a11-b82a-cd0b857d1d1d")
    Image getImage(Profile profile);

    @objid ("61ce771f-fc16-4de6-bb83-396b4ebbea9c")
    Image getModuleIcon(ModuleComponent moduleComponent);
}

