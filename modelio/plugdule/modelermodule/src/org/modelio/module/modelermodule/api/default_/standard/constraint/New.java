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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Constraint} with << new >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0b720f13-4154-4751-a90f-aadaef5fa9dd")
public class New {
    @objid ("e5207fc7-ac8c-4a68-babc-05e14285ba47")
    public static final String STEREOTYPE_NAME = "new";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("ae894ca7-6b9d-4c3a-ade5-d11ba0c5c240")
    protected final Constraint elt;

    /**
     * Tells whether a {@link New proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << new >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("6f002f70-85b0-4d99-8331-33699e59f217")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, New.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << new >> then instantiate a {@link New} proxy.
     * 
     * @return a {@link New} proxy on the created {@link Constraint}.
     */
    @objid ("3008d46c-c528-4ce8-adc1-727a4bb50773")
    public static New create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, New.STEREOTYPE_NAME);
        return New.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link New} proxy from a {@link Constraint} stereotyped << new >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link New} proxy or <i>null</i>.
     */
    @objid ("4acae35e-e1be-462f-931e-5ee9ca9ca98b")
    public static New instantiate(Constraint obj) {
        return New.canInstantiate(obj) ? new New(obj) : null;
    }

    /**
     * Tries to instantiate a {@link New} proxy from a {@link Constraint} stereotyped << new >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link New} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a0d8d785-15a8-430d-9f3f-e8df3260d346")
    public static New safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (New.canInstantiate(obj))
        	return new New(obj);
        else
        	throw new IllegalArgumentException("New: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0469e6bb-f97e-493a-b12e-5d39def5f56e")
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
        New other = (New) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("140be649-bfc1-4b49-ba62-eb0647a5e9a9")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("14224040-6c08-4ec9-af7c-8b34b04fa6a7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("9414c51e-3c63-4ac8-97fd-e9f223301ddf")
    protected  New(Constraint elt) {
        this.elt = elt;
    }

    @objid ("2f9de4ba-2666-4917-a258-dbdafc9f0143")
    public static final class MdaTypes {
        @objid ("6cad9e8d-6c69-4760-b5ec-1c554d849f3e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("648eff19-5cb4-4905-a26e-595ace837a48")
        private static Stereotype MDAASSOCDEP;

        @objid ("2d34fb35-d00e-4769-9cef-3ef987698d63")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("18fc7413-e761-4760-a65d-48cddd903605")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f9-0000-000000000000");
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
