/* 
 * Copyright 2013-2019 Modeliosoft
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

/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.package_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << frameWork >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("8c7e5fca-e81e-401f-92b7-8980e98e3dd1")
public class FrameWork {
    @objid ("b30b4a57-aad6-44ed-84cd-564b8e8a6343")
    public static final String STEREOTYPE_NAME = "frameWork";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("adc2bb7c-0448-418f-bc24-aebe308fb64e")
    protected final Package elt;

    /**
     * Tells whether a {@link FrameWork proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << frameWork >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8a17a629-1e5a-4e5d-9d87-c34a91f1657c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, FrameWork.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << frameWork >> then instantiate a {@link FrameWork} proxy.
     * 
     * @return a {@link FrameWork} proxy on the created {@link Package}.
     */
    @objid ("e8f38529-a446-48db-8b82-7ad89fbae4ce")
    public static FrameWork create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, FrameWork.STEREOTYPE_NAME);
        return FrameWork.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link FrameWork} proxy from a {@link Package} stereotyped << frameWork >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link FrameWork} proxy or <i>null</i>.
     */
    @objid ("d40f4a25-dde8-4322-90ef-45fae3f033ff")
    public static FrameWork instantiate(Package obj) {
        return FrameWork.canInstantiate(obj) ? new FrameWork(obj) : null;
    }

    /**
     * Tries to instantiate a {@link FrameWork} proxy from a {@link Package} stereotyped << frameWork >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link FrameWork} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ddf991f9-e59c-436e-8324-144b726ad935")
    public static FrameWork safeInstantiate(Package obj) throws IllegalArgumentException {
        if (FrameWork.canInstantiate(obj))
        	return new FrameWork(obj);
        else
        	throw new IllegalArgumentException("FrameWork: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("de811c76-0a15-427f-bc90-9ad3ce401b7f")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FrameWork other = (FrameWork) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("e60ac77e-ff69-4411-8a50-a7aa045c7a76")
    public Package getElement() {
        return this.elt;
    }

    @objid ("7c2522dc-dfd9-4536-8ab9-0bee8701de8c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2f5776a6-da2f-49c8-91d0-2c9077ceec45")
    protected FrameWork(Package elt) {
        this.elt = elt;
    }

    @objid ("c823ec2f-0b36-488c-9bc7-287eaac9b7cf")
    public static final class MdaTypes {
        @objid ("dd4408b7-73be-4433-9f3f-3106bd5f973d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dde8492d-31ea-403d-bbb2-af22372ba626")
        private static Stereotype MDAASSOCDEP;

        @objid ("378fca0c-67c7-499f-b32e-cfe1d6178585")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("02efb742-8de1-4d13-803b-5ff753b2e7e0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01db-0000-000000000000");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
