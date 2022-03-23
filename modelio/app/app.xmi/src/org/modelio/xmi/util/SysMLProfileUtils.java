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
package org.modelio.xmi.util;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.generation.TotalExportMap;
import org.modelio.xmi.plugin.Xmi;

@objid ("1097c04f-ef13-4c43-a92d-4d0665fe9d0a")
public class SysMLProfileUtils {
    @objid ("c84f4c8d-5f01-4632-8f09-390a73297114")
    private static String sysmlId = "00bc42a4-0000-1968-0000-000000000000";

    @objid ("907b96bf-a0ad-4203-927f-1a558e918f3a")
    public static boolean isSysML(final MObject profile) {
        return profile.getUuid().toString().equals(sysmlId);
    }

    @objid ("49a11f65-73a1-4ed2-b388-0b963228603c")
    public static void completeSysMLprofile(final org.eclipse.uml2.uml.Profile sysMLProfile, MMetamodel metamodel) {
        addRequirementStereotype(sysMLProfile);
        addDeriveStereotype(sysMLProfile, metamodel);
        addSatisfyStereotype(sysMLProfile, metamodel);
        addVerifyStereotype(sysMLProfile, metamodel);
        addSysMLNoteStereotypes(sysMLProfile);
        
    }

    @objid ("c81d0be5-a72f-4393-90b5-aa54dcd242dd")
    private static void addVerifyStereotype(final org.eclipse.uml2.uml.Profile sysMLProfile, MMetamodel metamodel) {
        org.eclipse.uml2.uml.Stereotype stereotype = sysMLProfile.createOwnedStereotype("Verify", false);
        ProfileUtils.addReference(stereotype, "Abstraction");
        
        try {
            Stereotype obStereotype = GenerationProperties.getInstance().getMModelServices()
                    .getStereotype(IModelerModulePeerModule.MODULE_NAME, "verify", metamodel.getMClass(Dependency.class));
        
            TotalExportMap.getInstance().put(obStereotype.getUuid().toString(), stereotype);
        } catch (IllegalArgumentException | ElementNotUniqueException e) {
            Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
        }
        
    }

    @objid ("98254b76-9fd1-44d2-95eb-7c2ca8a74b86")
    private static void addSatisfyStereotype(final org.eclipse.uml2.uml.Profile sysMLProfile, MMetamodel metamodel) {
        org.eclipse.uml2.uml.Stereotype stereotype = sysMLProfile.createOwnedStereotype("Satisfy", false);
        ProfileUtils.addReference(stereotype, "Abstraction");
        try {
            Stereotype obStereotype = GenerationProperties.getInstance().getMModelServices()
                    .getStereotype(IModelerModulePeerModule.MODULE_NAME, "satisfy",  metamodel.getMClass(Dependency.class));
            TotalExportMap.getInstance().put(obStereotype.getUuid().toString(), stereotype);
        } catch (IllegalArgumentException | ElementNotUniqueException e) {
            Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
        }
        
    }

    @objid ("71ceb6f3-2c17-4bc7-9660-27b11b42d7c1")
    private static void addDeriveStereotype(final org.eclipse.uml2.uml.Profile sysMLProfile, MMetamodel metamodel) {
        org.eclipse.uml2.uml.Stereotype stereotype = sysMLProfile.createOwnedStereotype("DeriveReqt", false);
        ProfileUtils.addReference(stereotype, "Abstraction");
        try {
            Stereotype obStereotype = GenerationProperties.getInstance().getMModelServices()
                    .getStereotype(IModelerModulePeerModule.MODULE_NAME, "derive",  metamodel.getMClass(Dependency.class));
            TotalExportMap.getInstance().put(obStereotype.getUuid().toString(), stereotype);
        } catch (IllegalArgumentException | ElementNotUniqueException e) {
            Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
        }
        
    }

    @objid ("736e3a5f-4bd5-4e01-a5cd-7cadb9af53b0")
    private static void addRequirementStereotype(final org.eclipse.uml2.uml.Profile sysMLProfile) {
        org.eclipse.uml2.uml.Stereotype stereotype = sysMLProfile.createOwnedStereotype("Requirement", false);
        
        EcoreUMLTypes ecoreUMLTypes = GenerationProperties.getInstance().getEcoreUMLTypes();
        
        ProfileUtils.addReference(stereotype, "Class");
        
        Property text = stereotype.createOwnedAttribute("Text", ecoreUMLTypes.getString());
        text.setLower(1);
        text.setUpper(1);
        
        Property id = stereotype.createOwnedAttribute("Id",  ecoreUMLTypes.getString());
        id.setLower(1);
        id.setUpper(1);
        
    }

    @objid ("5f1291c0-679d-4170-8fef-393db70cb7c2")
    private static void addSysMLNoteStereotypes(final org.eclipse.uml2.uml.Profile sysMLProfile) {
        org.eclipse.uml2.uml.Stereotype rationaleStereotype = sysMLProfile.createOwnedStereotype("Rationale", false);
        ProfileUtils.addReference(rationaleStereotype, "Comment");
        
        org.eclipse.uml2.uml.Stereotype problemStereotype = sysMLProfile.createOwnedStereotype("Problem", false);
        ProfileUtils.addReference(problemStereotype, "Comment");
        
        org.eclipse.uml2.uml.Stereotype relatedStereotype = sysMLProfile.createOwnedStereotype("RequirementRelated", false);
        ProfileUtils.addReference(relatedStereotype, "NamedElement");
        
    }

}
