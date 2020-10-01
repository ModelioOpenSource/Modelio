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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << +influence >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42cd298d-9d7d-4787-9645-c8747b86583f")
public class PlusInfluence {
    @objid ("85311206-ad37-4434-aa65-9dea165502b4")
    public static final String STEREOTYPE_NAME = "+influence";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("2c58402e-a94b-47c4-b4a2-5fec71a7eaa6")
    protected final Dependency elt;

    /**
     * Tells whether a {@link PlusInfluence proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << +influence >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("66286c56-f6b6-4fb7-ad84-33c54235a9bd")
    public static boolean canInstantiate(MObject elt) {
        return elt instanceof Dependency && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PlusInfluence.STEREOTYPE_NAME);
    }

    /**
     * Create a new {@link Dependency} stereotyped << +influence >> then instantiate a {@link PlusInfluence} proxy.
     * 
     * @return a {@link PlusInfluence} proxy on the created {@link Dependency}.
     */
    @objid ("2756c353-1936-465d-9e0d-e98ba92cfb89")
    public static PlusInfluence create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PlusInfluence.STEREOTYPE_NAME);
        return PlusInfluence.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link PlusInfluence} proxy from a {@link Dependency} stereotyped << +influence >> checking its metaclass and its stereotype.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * 
     * @param obj a Dependency
     * @return a {@link PlusInfluence} proxy or <i>null</i>.
     */
    @objid ("8e52da2a-ef02-43fd-bab2-70981c931e21")
    public static PlusInfluence instantiate(Dependency obj) {
        return PlusInfluence.canInstantiate(obj) ? new PlusInfluence(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PlusInfluence} proxy from a {@link Dependency} stereotyped << +influence >> checking its metaclass and its stereotype.
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * 
     * @param obj a {@link Dependency}
     * @return a {@link PlusInfluence} proxy.
     * @throws java.lang.IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("31ac437d-5cad-4a86-a078-1e5726360d9d")
    public static PlusInfluence safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (PlusInfluence.canInstantiate(obj)) {
            return new PlusInfluence(obj);
        } else {
            throw new IllegalArgumentException("PlusInfluence: Cannot instantiate "+obj+": wrong element type or stereotype");
        }
    }

    @objid ("2d164b0c-fc05-4ec7-939d-7cf05ab0891c")
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
        PlusInfluence other = (PlusInfluence) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}.
     * 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("e1eb3280-03ea-4571-b88f-2fdeaf849fbf")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("82703f1d-1d59-444b-a823-e168e20deec5")
    @Override
    public int hashCode() {
        return 23 + (this.elt == null ? 0 : this.elt.hashCode());
    }

    @objid ("820407fa-cd57-40ed-9688-866f8f387483")
    protected PlusInfluence(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5e2e10fd-0acf-4a71-9302-363201fdbdef")
    public static final class MdaTypes {
        @objid ("a1ce38af-8932-4827-b8fb-b32b9cf172b3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("81c8803b-fb4e-41b0-9a21-e67159793717")
        private static Stereotype MDAASSOCDEP;

        @objid ("da8e444a-3f66-48ad-a955-afb4f3bfa8db")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a1f1ee45-38d1-4c75-9b18-fe951caf394c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0247-0000-000000000000");
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
