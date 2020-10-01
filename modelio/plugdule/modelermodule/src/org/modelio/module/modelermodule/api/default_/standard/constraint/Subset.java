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
 * Proxy class to handle a {@link Constraint} with << subset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("64070ac9-b416-414f-8cd6-63da9a9124aa")
public class Subset {
    @objid ("cdecd6f4-1d53-433d-abd6-4e3914364824")
    public static final String STEREOTYPE_NAME = "subset";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("e5f89cb3-488e-4f97-a088-49bcec1635df")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Subset proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << subset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d8e400de-642f-4628-b710-5f443c939fe7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Subset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << subset >> then instantiate a {@link Subset} proxy.
     * 
     * @return a {@link Subset} proxy on the created {@link Constraint}.
     */
    @objid ("875df2c0-887e-48a8-ad9e-189f6594778a")
    public static Subset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Subset.STEREOTYPE_NAME);
        return Subset.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Subset} proxy from a {@link Constraint} stereotyped << subset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Subset} proxy or <i>null</i>.
     */
    @objid ("2473d721-937a-4aac-8bff-0cd99c082db6")
    public static Subset instantiate(Constraint obj) {
        return Subset.canInstantiate(obj) ? new Subset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Subset} proxy from a {@link Constraint} stereotyped << subset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Subset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8e4c2ef9-5696-491d-8586-5db8f4038be4")
    public static Subset safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Subset.canInstantiate(obj))
        	return new Subset(obj);
        else
        	throw new IllegalArgumentException("Subset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("38744a99-bb40-4c93-8e80-097c76ddf2e8")
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
        Subset other = (Subset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("d676ed9f-fc6c-4985-b047-b5acf032e69e")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("b762f33e-94fd-417a-961a-7ae7a72cf0c2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("da969b47-a6d5-4350-a79c-0d0baf31e691")
    protected Subset(Constraint elt) {
        this.elt = elt;
    }

    @objid ("b023fad9-6eb0-417d-83a0-1258b9a55847")
    public static final class MdaTypes {
        @objid ("49fe10d8-517a-47eb-9cec-99c61efc35cf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0c8113b4-8f51-4910-a560-d4b0081a884e")
        private static Stereotype MDAASSOCDEP;

        @objid ("a6196276-409b-47e0-be6b-f195b551af4c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7629d5e7-4ca9-42fa-bef3-3d8caa04cb22")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00540a84-0000-0005-0000-000000000000");
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
