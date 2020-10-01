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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
 * Proxy class to handle a {@link Constraint} with << overlapping >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("56431f9b-f67b-4509-9a09-2227ea5aef29")
public class Overlapping {
    @objid ("19c536e3-2397-40cb-8c3b-c064fd775b6f")
    public static final String STEREOTYPE_NAME = "overlapping";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("8c155856-7f75-438c-8cba-011fea749a80")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Overlapping proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << overlapping >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("087d34ce-d3cd-4ab2-98fb-95d2c3235fef")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Overlapping.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << overlapping >> then instantiate a {@link Overlapping} proxy.
     * 
     * @return a {@link Overlapping} proxy on the created {@link Constraint}.
     */
    @objid ("601d831a-9a85-4b74-89b9-91fbb3c832a5")
    public static Overlapping create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Overlapping.STEREOTYPE_NAME);
        return Overlapping.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Overlapping} proxy from a {@link Constraint} stereotyped << overlapping >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Overlapping} proxy or <i>null</i>.
     */
    @objid ("be178442-2ebd-4b6b-9d5a-5e254a14d060")
    public static Overlapping instantiate(Constraint obj) {
        return Overlapping.canInstantiate(obj) ? new Overlapping(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Overlapping} proxy from a {@link Constraint} stereotyped << overlapping >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Overlapping} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4146a70a-e725-4de9-8a0f-a122ae41aae1")
    public static Overlapping safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Overlapping.canInstantiate(obj))
        	return new Overlapping(obj);
        else
        	throw new IllegalArgumentException("Overlapping: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6afe9784-4308-4771-adda-9634a40c1c83")
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
        Overlapping other = (Overlapping) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("f00fd170-08c1-4086-a436-920b1f1c2381")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("bdbc09ab-3749-4b01-b33a-d7edf4c8bf20")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a2b092ab-b937-4f7b-96f2-31cbba357b3d")
    protected Overlapping(Constraint elt) {
        this.elt = elt;
    }

    @objid ("ae8f7c08-3dd0-4e2e-9abe-678cf39942a8")
    public static final class MdaTypes {
        @objid ("8d2eb1b0-840c-4610-ad71-b2ae14fa7df8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("71923a2f-e026-45ec-a1dc-af316a840176")
        private static Stereotype MDAASSOCDEP;

        @objid ("c6970190-c174-4d88-8988-41d6bbe468bb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7aa3e94e-e727-4adb-b756-1d8417faa393")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01fb-0000-000000000000");
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
