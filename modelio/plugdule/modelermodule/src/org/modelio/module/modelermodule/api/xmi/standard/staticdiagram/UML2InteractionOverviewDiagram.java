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
package org.modelio.module.modelermodule.api.xmi.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
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
 * Proxy class to handle a {@link StaticDiagram} with << UML2InteractionOverviewDiagram  >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("025fc4e6-2df9-46ba-9efe-c6ce06c9b716")
public class UML2InteractionOverviewDiagram {
    @objid ("497d006e-29da-4570-b263-01e428fcefa3")
    public static final String STEREOTYPE_NAME = "UML2InteractionOverviewDiagram ";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("43209705-39ea-428b-8af8-d271728a6b9e")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link UML2InteractionOverviewDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("00500564-abc3-4c90-835a-96374a98080c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InteractionOverviewDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> then instantiate a {@link UML2InteractionOverviewDiagram} proxy.
     * 
     * @return a {@link UML2InteractionOverviewDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("85dc7c37-1735-47e1-b32c-1cb2f0f4b12a")
    public static UML2InteractionOverviewDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InteractionOverviewDiagram.STEREOTYPE_NAME);
        return UML2InteractionOverviewDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link UML2InteractionOverviewDiagram} proxy from a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link UML2InteractionOverviewDiagram} proxy or <i>null</i>.
     */
    @objid ("fe06e18f-91d4-49fa-a112-8360c25a0df9")
    public static UML2InteractionOverviewDiagram instantiate(StaticDiagram obj) {
        return UML2InteractionOverviewDiagram.canInstantiate(obj) ? new UML2InteractionOverviewDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InteractionOverviewDiagram} proxy from a {@link StaticDiagram} stereotyped << UML2InteractionOverviewDiagram  >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link UML2InteractionOverviewDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a11ea26d-0401-4ec2-bc8d-1665c5990cfe")
    public static UML2InteractionOverviewDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (UML2InteractionOverviewDiagram.canInstantiate(obj))
        	return new UML2InteractionOverviewDiagram(obj);
        else
        	throw new IllegalArgumentException("UML2InteractionOverviewDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a97ed03e-7c58-4af2-8f3d-50a6bc4ee217")
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
        UML2InteractionOverviewDiagram other = (UML2InteractionOverviewDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("3ea8d59f-956e-4a81-88df-42b05c0796b6")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("8837e009-3a4d-41ef-a950-e0ee4fc9c811")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("874de514-7f53-4521-a7e6-d0ad9799d584")
    protected  UML2InteractionOverviewDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("be457845-c207-4134-ac6d-84fc582830cd")
    public static final class MdaTypes {
        @objid ("2cfb9230-a6bd-44da-9f07-3c7a54a85e1e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("be6f406c-165f-4035-b6ad-7c7c4c431bd5")
        private static Stereotype MDAASSOCDEP;

        @objid ("50cb362a-18f9-4d22-b096-dc3bc2cdfa2c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("afbdded3-82f1-4652-9689-4a0202ce94a5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e4358003-f3da-11df-8ada-0027103f347c");
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
