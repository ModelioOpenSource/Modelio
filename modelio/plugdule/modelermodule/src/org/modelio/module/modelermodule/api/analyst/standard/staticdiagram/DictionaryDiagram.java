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
 * Proxy class to handle a {@link StaticDiagram} with << dictionary_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3ccfaba8-70d5-49d8-a70a-4cc435f8822a")
public class DictionaryDiagram {
    @objid ("1316282c-f15a-4eb2-a06f-987a35d45b29")
    public static final String STEREOTYPE_NAME = "dictionary_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("8d2ea86c-db79-4c54-9b95-53de9ee869d2")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link DictionaryDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << dictionary_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("44ed8fec-bd1e-4cf5-911a-3d029fb1c5ff")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DictionaryDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << dictionary_diagram >> then instantiate a {@link DictionaryDiagram} proxy.
     * 
     * @return a {@link DictionaryDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("4b5b0205-d0b5-410a-82b2-196a29f89839")
    public static DictionaryDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DictionaryDiagram.STEREOTYPE_NAME);
        return DictionaryDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link DictionaryDiagram} proxy from a {@link StaticDiagram} stereotyped << dictionary_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link DictionaryDiagram} proxy or <i>null</i>.
     */
    @objid ("e172a908-87e5-4070-8799-e25d1591b8b2")
    public static DictionaryDiagram instantiate(StaticDiagram obj) {
        return DictionaryDiagram.canInstantiate(obj) ? new DictionaryDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link DictionaryDiagram} proxy from a {@link StaticDiagram} stereotyped << dictionary_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link DictionaryDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9d29cb94-adb0-4307-bc99-9c28c0e5dfbc")
    public static DictionaryDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (DictionaryDiagram.canInstantiate(obj))
        	return new DictionaryDiagram(obj);
        else
        	throw new IllegalArgumentException("DictionaryDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a0abf59c-4086-48f7-ac28-8825d97146c2")
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
        DictionaryDiagram other = (DictionaryDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("62585cce-155e-4ae7-b426-436b86b5488d")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("1e268fd5-983f-43b9-8234-910c50d77897")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("51f2bd3a-202b-403e-88b5-16bdb53154ab")
    protected  DictionaryDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("e3bf2946-f53e-4175-8a51-d544c543a4b6")
    public static final class MdaTypes {
        @objid ("cdfbc876-c73c-43d1-a011-e89663086a45")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e90de83c-eed6-4469-bae5-15f920fff390")
        private static Stereotype MDAASSOCDEP;

        @objid ("94095fd2-ba5f-4ee6-83e9-de4b2521edd8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3f1b7476-c8cb-4cff-9f1a-3b21b719737d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0c02-0000-000000000000");
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
