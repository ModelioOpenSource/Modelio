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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.standard.staticdiagram;

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
 * Proxy class to handle a {@link StaticDiagram} with << kpi_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ca735868-68ed-46f2-b748-17695c64b56f")
public class KpiDiagram {
    @objid ("473984ec-0558-43a1-9763-c68832442a7e")
    public static final String STEREOTYPE_NAME = "kpi_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("79d5c4a5-1950-49dd-892a-2ded68073d2c")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link KpiDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << kpi_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("ef5526bd-2a0d-4974-b1f3-a6b6a3a0760c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KpiDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << kpi_diagram >> then instantiate a {@link KpiDiagram} proxy.
     * 
     * @return a {@link KpiDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("13c6ad53-bcdc-48b9-864c-12022b57f10a")
    public static KpiDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KpiDiagram.STEREOTYPE_NAME);
        return KpiDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link KpiDiagram} proxy from a {@link StaticDiagram} stereotyped << kpi_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link KpiDiagram} proxy or <i>null</i>.
     */
    @objid ("63900bec-ec92-43b6-a2d3-38406f69927a")
    public static KpiDiagram instantiate(StaticDiagram obj) {
        return KpiDiagram.canInstantiate(obj) ? new KpiDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link KpiDiagram} proxy from a {@link StaticDiagram} stereotyped << kpi_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link KpiDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d18b5997-e049-456a-a2ad-5261aca5a50f")
    public static KpiDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (KpiDiagram.canInstantiate(obj))
        	return new KpiDiagram(obj);
        else
        	throw new IllegalArgumentException("KpiDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("43dd7027-07f9-4b2e-b129-5e2c19af6146")
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
        KpiDiagram other = (KpiDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("7b25255f-44ef-4f2d-8726-a2c3a5b4afb8")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("1288d15d-0235-4d48-b188-eaca26bc6348")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d2255abc-4a4f-4dd4-8863-b9189c591797")
    protected KpiDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("14383e01-4c20-4ad8-b7c9-1c85c97c4c2e")
    public static final class MdaTypes {
        @objid ("21b611bc-4a4a-4f3e-929f-6d8ca259669e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c7a188af-b03a-4d18-8253-dd83dbcfa14b")
        private static Stereotype MDAASSOCDEP;

        @objid ("34ee1f1f-c1e6-4a53-8478-fead18643e1d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5b4671f0-00e8-4dee-b725-32b9544c5a61")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "19d2deba-a7b7-4818-8d19-aa8b2d63dba1");
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
