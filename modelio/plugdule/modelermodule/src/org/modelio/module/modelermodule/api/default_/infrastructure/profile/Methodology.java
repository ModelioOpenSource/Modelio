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
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.profile;

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
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Profile} with << Methodology >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d2cc83ce-07ab-4538-af69-8ed35787e831")
public class Methodology {
    @objid ("8c4651b5-012b-46cc-8312-e4ae39fcc5a9")
    public static final String STEREOTYPE_NAME = "Methodology";

    /**
     * The underlying {@link Profile} represented by this proxy, never null.
     */
    @objid ("eda3cb29-123a-4fed-bdcd-68c57f2ff9d5")
    protected final Profile elt;

    /**
     * Tells whether a {@link Methodology proxy} can be instantiated from a {@link MObject} checking it is a {@link Profile} stereotyped << Methodology >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d140e69a-d43a-483f-af7e-8a95ecd673b7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Profile) && ((Profile) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Methodology.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Profile} stereotyped << Methodology >> then instantiate a {@link Methodology} proxy.
     * 
     * @return a {@link Methodology} proxy on the created {@link Profile}.
     */
    @objid ("703b5c15-dc67-4f46-bd53-477041cfa555")
    public static Methodology create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Profile");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Methodology.STEREOTYPE_NAME);
        return Methodology.instantiate((Profile)e);
    }

    /**
     * Tries to instantiate a {@link Methodology} proxy from a {@link Profile} stereotyped << Methodology >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Profile
     * @return a {@link Methodology} proxy or <i>null</i>.
     */
    @objid ("607f465e-5c40-47ac-976d-b75da71728fa")
    public static Methodology instantiate(Profile obj) {
        return Methodology.canInstantiate(obj) ? new Methodology(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Methodology} proxy from a {@link Profile} stereotyped << Methodology >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Profile}
     * @return a {@link Methodology} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4e250206-ad9f-4e3e-b397-f406243ea39d")
    public static Methodology safeInstantiate(Profile obj) throws IllegalArgumentException {
        if (Methodology.canInstantiate(obj))
        	return new Methodology(obj);
        else
        	throw new IllegalArgumentException("Methodology: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1056a5c3-5418-4fc8-9040-232c066ba5ad")
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
        Methodology other = (Methodology) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Profile}. 
     * @return the Profile represented by this proxy, never null.
     */
    @objid ("d8d104e1-318b-4f5a-9365-4bf92289b8b3")
    public Profile getElement() {
        return this.elt;
    }

    @objid ("6ec46d83-fd37-4e5c-8f4d-22bc35dd03f8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("6de8b703-66c2-4d4e-b00d-1c562d00ea06")
    protected  Methodology(Profile elt) {
        this.elt = elt;
    }

    @objid ("9f7ace44-8e16-4801-b3e4-24f952f92343")
    public static final class MdaTypes {
        @objid ("4e65fbdc-398b-4c8e-ac7b-7a0ad00bcc5e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("21c82c0f-92b4-4ca6-a597-5186ddceb65b")
        private static Stereotype MDAASSOCDEP;

        @objid ("2cd46074-d034-4fdd-b854-7cbc20ce27bd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2e2e9733-1120-4a2e-beaa-3af6f52a1a33")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "769b4d1b-614e-4503-892c-b59d0de23158");
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
